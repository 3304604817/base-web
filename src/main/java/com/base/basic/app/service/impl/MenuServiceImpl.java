package com.base.basic.app.service.impl;

import com.base.basic.app.service.MenuService;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.vo.v0.MenuHomeVO;
import com.base.basic.domain.vo.v0.MenuInfoVO;
import com.base.basic.domain.vo.v0.MenuLogoVO;
import com.base.basic.domain.vo.v0.MenuVO;
import com.base.basic.infra.constant.BaseConstants;
import com.base.basic.infra.mapper.MenuMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    @SuppressWarnings("all")
    private MenuMapper menuMapper;

    @Override
    public MenuVO initMenu(){
        /**
         * 查首页
         */
        Menu homeInfo = menuMapper.selectOne(new Menu(BaseConstants.menuType.HOME_INFO, Boolean.TRUE));
        MenuHomeVO menuHomeVO = new MenuHomeVO();
        BeanUtils.copyProperties(homeInfo, menuHomeVO);

        /**
         * 查首页 Logo
         */
        Menu logoInfo = menuMapper.selectOne(new Menu(BaseConstants.menuType.LOGO_INFO, Boolean.TRUE));
        MenuLogoVO menuLogoVO = new MenuLogoVO();
        BeanUtils.copyProperties(logoInfo, menuLogoVO);

        /**
         * 查菜单
         */
        // 存一级菜单最终返回用
        List<MenuInfoVO> oneLevelMenuVOList = new ArrayList<>();
        // 查所有菜单
        List<Menu> menuInfoList = menuMapper.select(new Menu(BaseConstants.menuType.MENU_INFO, Boolean.TRUE));
        // 过滤出一级菜单
        List<Menu> oneLevelMenuList = menuInfoList.stream().filter(menuInfo-> Objects.isNull(menuInfo.getParentId())).collect(Collectors.toList());
        for(Menu oneLevelMenu:oneLevelMenuList){
            MenuInfoVO oneLevelMenuVO = new MenuInfoVO();
            BeanUtils.copyProperties(oneLevelMenu, oneLevelMenuVO);

            /**
             * 递归查子级菜单
             */
            oneLevelMenuVO.setChild(
                    initMenuInfo(oneLevelMenuVO)
            );
            oneLevelMenuVOList.add(oneLevelMenuVO);
        }


        // 最终返回的菜单
        MenuVO menuVO = new MenuVO();
        menuVO.setHomeInfo(menuHomeVO);
        menuVO.setLogoInfo(menuLogoVO);
        menuVO.setMenuInfo(oneLevelMenuVOList);
        return menuVO;
    }

    @Override
    public PageInfo<Menu> pageList(PageParmaters pageParmaters, Menu searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> menuMapper.list(searchBody));
    }

    @Override
    public List<Menu> treeList(){
        List<Menu> allMenu = menuMapper.selectAll();
        for(Menu menu:allMenu){
            if(Objects.isNull(menu.getParentId())){
                menu.setParentId(-1L);
            }
        }
        return allMenu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu add(Menu menu){
        menuMapper.insertSelective(menu);
        return menu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu update(Menu menu){
        menuMapper.updateByIdSelective(menu);
        return menu;
    }

    private List<MenuInfoVO> initMenuInfo(MenuInfoVO parentMenuInfoVO){
        List<MenuInfoVO> menuInfoVOList = new ArrayList<>(8);
        List<Menu> childMenuList = menuMapper.select(new Menu(parentMenuInfoVO.getId(), BaseConstants.menuType.MENU_INFO, Boolean.TRUE));
        if(0 == childMenuList.size()){
            return null;
        }
        for(Menu childMenu:childMenuList){
            MenuInfoVO childMenuVO = new MenuInfoVO();
            BeanUtils.copyProperties(childMenu, childMenuVO);
            childMenuVO.setChild(this.initMenuInfo(childMenuVO));

            menuInfoVOList.add(childMenuVO);
        }
        return menuInfoVOList;
    }
}
