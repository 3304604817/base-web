package com.base.basic.domain.vo.v0;

import java.util.List;

public class MenuInfoVO {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父级菜单ID
     */
    private Long parentId;

    private String title;

    private String icon;

    private String target;

    /**
     * 前端页面地址
     */
    private String href;

    /**
     * 二级菜单
     */
    private List<MenuInfoVO> child;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<MenuInfoVO> getChild() {
        return child;
    }

    public void setChild(List<MenuInfoVO> child) {
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
