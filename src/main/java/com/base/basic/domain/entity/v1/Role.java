package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ApiModel("角色管理")
@Table(name = "db_role")
public class Role extends BaseEntity {
    public static final String FIELD_ROLE_ID = "roleId";
    public static final String FIELD_ROLE_CODE = "roleCode";
    public static final String FIELD_ROLE_NAME = "roleName";
    public static final String FIELD_MENU_IDS = "menuIds";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long roleId;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色对应菜单ID,逗号分割")
    private String menuIds;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabledFlag;

    @ApiModelProperty(value = "角色对应菜单Title,逗号分割")
    @Transient
    private String menuTitles;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public String getMenuTitles() {
        return menuTitles;
    }

    public void setMenuTitles(String menuTitles) {
        this.menuTitles = menuTitles;
    }
}
