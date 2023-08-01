package com.base.basic.app.service.impl;

import com.base.basic.app.service.MenuService;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.entity.v1.UserRole;
import com.base.basic.domain.vo.v0.*;
import com.base.basic.infra.constant.BaseConstants;
import com.base.basic.infra.mapper.MenuMapper;
import com.base.basic.infra.mapper.RoleMapper;
import com.base.basic.infra.mapper.UserRoleMapper;
import com.base.common.current.CurrentUserHelper;
import com.base.common.exception.BaseException;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    @SuppressWarnings("all")
    private MenuMapper menuMapper;
    @Autowired
    @SuppressWarnings("all")
    private RoleMapper roleMapper;
    @Autowired
    @SuppressWarnings("all")
    private UserRoleMapper userRoleMapper;

    @Override
    public MenuVO initAllMenu(){
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
        // 所有菜单先剔除 ParentId 不存在的，根据 ParentId 分组
        Map<Long, List<Menu>> menuMapByParentId = menuInfoList.stream().filter(menuInfo-> Objects.nonNull(menuInfo.getParentId())).collect(Collectors.groupingBy(Menu::getParentId));
        // 过滤出一级菜单
        List<Menu> oneLevelMenuList = menuInfoList.stream().filter(menuInfo-> Objects.isNull(menuInfo.getParentId())).collect(Collectors.toList());
        for(Menu oneLevelMenu:oneLevelMenuList){
            MenuInfoVO oneLevelMenuVO = new MenuInfoVO();
            BeanUtils.copyProperties(oneLevelMenu, oneLevelMenuVO);

            /**
             * 递归查子级菜单
             */
            oneLevelMenuVO.setChild(
                    initMenuInfo(10, 0, menuMapByParentId, oneLevelMenuVO)
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
    public MenuVO initCurrentMenu(){
        /**
         * 查菜单
         */
        //　当前用户的权限
        List<UserRole> userRoleList = userRoleMapper.userRole(CurrentUserHelper.userDetail().getUsername());
        // 取当前用户所有菜单ID Set
        Set<Long> menuIdSet = new HashSet<>(32);
        List<String> menuIdList = userRoleList.stream().map(UserRole::getMenuIds).collect(Collectors.toList());
        for(String menuIds:menuIdList){
            for(String menuId:menuIds.split(",")){
                menuIdSet.add(Long.valueOf(menuId));
            }
        }
        // 查当前用户可以访问的所有菜单
        List<Menu> menuInfoList = menuMapper.list(new Menu(menuIdSet, BaseConstants.menuType.MENU_INFO, Boolean.TRUE));
        // 所有菜单根据 Id 分组
        Map<Long, List<Menu>> menuMapById = menuInfoList.stream().collect(Collectors.groupingBy(Menu::getId));
        // 所有菜单先剔除 ParentId 不存在的，根据 ParentId 分组
        Map<Long, List<Menu>> menuMapByParentId = menuInfoList.stream().filter(menuInfo-> Objects.nonNull(menuInfo.getParentId())).collect(Collectors.groupingBy(Menu::getParentId));

        // 过滤出一级菜单
        List<Menu> oneLevelMenuList = menuInfoList.stream().filter(menuInfo-> Objects.isNull(menuInfo.getParentId())).collect(Collectors.toList());
        // 存一级菜单Json最终返回用
        List<MenuInfoVO> oneLevelMenuVOList = new ArrayList<>();
        for(Long menuId:menuIdSet){
            Menu menu = menuMapById.get(Long.valueOf(menuId)).get(0);
            MenuInfoVO menuVO = new MenuInfoVO();
            BeanUtils.copyProperties(menu, menuVO);
            menuVO.setChild(
                    initMenuInfo(10, 0, menuMapByParentId, menuVO)
            );
            oneLevelMenuVOList.add(menuVO);
        }


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
        Map<Long, List<Menu>> menuMapById = allMenu.stream().collect(Collectors.groupingBy(Menu::getId));
        for(Menu menu:allMenu){
            if(Objects.isNull(menu.getParentId())){
                menu.setParentId(-1L);
            }else {
                Menu parentMenu = menuMapById.get(menu.getParentId()).get(0);
                menu.setParentMenuCode(parentMenu.getMenuCode());
                menu.setParentTitle(parentMenu.getTitle());
            }
        }
        return allMenu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu add(Menu menu){
        if(null != menu.getParentId()){
            Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            menu.setParentId(menu.getParentId());
            menu.setMenuPath(parentMenu.getMenuPath() + '|' + menu.getMenuCode());
        }else {
            menu.setMenuPath(menu.getMenuCode());
        }
        menu.setEnabledFlag(Boolean.TRUE);
        menu.setMenuType(BaseConstants.menuType.MENU_INFO);
        menu.setTarget("_self");
        menuMapper.insertSelective(menu);
        return menu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu update(Menu menu){
        Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
        if(null != parentMenu && StringUtils.isNotEmpty(parentMenu.getMenuPath())){
            menu.setMenuPath(parentMenu.getMenuPath() + '|' + menu.getMenuCode());
        }else {
            menu.setParentId(null);
            menu.setMenuPath(menu.getMenuCode());
        }
        menu.setParentId(menu.getParentId());
        menuMapper.updateByIdSelective(menu);
        return menu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Long id){
        Menu menu = new Menu();
        menu.setId(id);
        menu.setEnabledFlag(Boolean.TRUE);
        menuMapper.updateOptional(menu,
                Menu.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disabled(Long id){
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if(!StringUtils.equals(BaseConstants.menuType.MENU_INFO, menu.getMenuType())){
            throw new BaseException("当前菜单不允许禁用!");
        }
        menu.setEnabledFlag(Boolean.FALSE);
        menuMapper.updateOptional(menu,
                Menu.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    /**
     * 递归查子级菜单，返回的是树状结构
     * @param maxCount 最大递归次数
     * @param count 当前递归次数
     * @param menuMapByParentId
     * @param parentMenuInfoVO
     * @return
     */
    private List<MenuInfoVO> initMenuInfo(int maxCount, int count, Map<Long, List<Menu>> menuMapByParentId, MenuInfoVO parentMenuInfoVO){
        List<MenuInfoVO> menuInfoVOList = new ArrayList<>(8);
        List<Menu> childMenuList = menuMapByParentId.get(parentMenuInfoVO.getId());
        if(Objects.isNull(childMenuList) || 0 == childMenuList.size() || count > maxCount){
            return null;
        }
        for(Menu childMenu:childMenuList){
            MenuInfoVO childMenuVO = new MenuInfoVO();
            BeanUtils.copyProperties(childMenu, childMenuVO);
            childMenuVO.setChild(this.initMenuInfo(maxCount, ++count, menuMapByParentId, childMenuVO));

            menuInfoVOList.add(childMenuVO);
        }
        return menuInfoVOList;
    }
}
