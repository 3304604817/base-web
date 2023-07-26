package com.base.basic.domain.vo.v0;

import java.util.List;

/**
 * 菜单
 */
public class MenuVO {
    private MenuHomeVO homeInfo;

    private MenuLogoVO logoInfo;

    private List<MenuInfoVO> menuInfo;

    public MenuHomeVO getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(MenuHomeVO homeInfo) {
        this.homeInfo = homeInfo;
    }

    public MenuLogoVO getLogoInfo() {
        return logoInfo;
    }

    public void setLogoInfo(MenuLogoVO logoInfo) {
        this.logoInfo = logoInfo;
    }

    public List<MenuInfoVO> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<MenuInfoVO> menuInfo) {
        this.menuInfo = menuInfo;
    }
}
