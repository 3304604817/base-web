package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ApiModel("菜单管理")
@Table(name = "db_menu")
public class Menu extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PARENT_ID = "parentId";
    public static final String FIELD_MENU_CODE = "menuCode";
    public static final String FIELD_MENU_PATH = "menuPath";
    public static final String FIELD_MENU_TYPE = "menuType";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_HREF = "href";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_ICON = "icon";
    public static final String FIELD_TARGET = "target";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";
    public static final String FIELD_TENANT_ID = "tenantId";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "父级菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "菜单类型：homeInfo（首页）logoInfo（首页Logo）menuInfo（菜单）")
    private String menuType;

    @ApiModelProperty(value = "菜单名")
    private String title;

    @ApiModelProperty(value = "菜单URL地址")
    private String href;

    @ApiModelProperty(value = "首页Logo图片路径")
    private String image;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "")
    private String target;

    @ApiModelProperty(value = "是否启用。1启用，0未启用")
    private Boolean enabledFlag;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "菜单层级")
    @Transient
    private Long pathLevel;

    @ApiModelProperty(value = "父级菜单Code")
    @Transient
    private String parentMenuCode;

    @ApiModelProperty(value = "父级菜单名")
    @Transient
    private String parentTitle;

    public Menu(){}
    public Menu(String menuType, Boolean enabledFlag){
        this.menuType = menuType;
        this.enabledFlag = enabledFlag;
    }
    public Menu(Long parentId, String menuType, Boolean enabledFlag){
        this.parentId = parentId;
        this.menuType = menuType;
        this.enabledFlag = enabledFlag;
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

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Boolean getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Long getPathLevel() {
        return pathLevel;
    }

    public void setPathLevel(Long pathLevel) {
        this.pathLevel = pathLevel;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }
}
