package com.base.common.util.mybatis.provider;

import com.base.common.util.mybatis.helper.SqlUnitHelper;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.version.VersionException;

import javax.persistence.Id;
import java.util.Set;

/**
 * @author gaoyang
 * 基于 tk.mybatis 进行增强
 */
public class UpdateExpandProvider extends MapperTemplate {

    public UpdateExpandProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 无锁更新(无版本控制)
     * @param ms
     * @return
     */
    public String updateOptional(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlUnitHelper.OPTIONAL_BIND);
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append("<set>");

        EntityColumn primaryColumn = null;
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        for(EntityColumn column:columnSet){
            if(column.getEntityField().isAnnotationPresent(Id.class)){
                if (primaryColumn != null) {
                    throw new VersionException(entityClass.getCanonicalName() + " 中包含多个带有 @Id 注解的字段，一个类中只能存在一个带有 @Id 注解的字段!");
                }
                primaryColumn = column;
            }else {
                sql.append(SqlUnitHelper.getIfContains("optional", column, column.getColumnHolder(null, null, ",")));
            }
        }
        sql.append("</set><where>");
        sql.append("AND ").append(primaryColumn.getColumn()).append(" = #{").append(primaryColumn.getProperty()).append("}");
        sql.append("</where>");
        return sql.toString();
    }

    /**
     * Optimistic Lock 基于乐观锁更新
     * @param ms
     * @return
     */
    public String updateOptionalOpl(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlUnitHelper.OPTIONAL_BIND);
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append("<set>");

        EntityColumn primaryColumn = null;
        EntityColumn versionColumn = null;
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        for(EntityColumn column:columnSet){
            if (column.getEntityField().isAnnotationPresent(Version.class)) {
                if (versionColumn != null) {
                    throw new VersionException(entityClass.getCanonicalName() + " 中包含多个带有 @Version 注解的字段，一个类中只能存在一个带有 @Version 注解的字段!");
                }
                versionColumn = column;
            }else if(column.getEntityField().isAnnotationPresent(Id.class)){
                if (primaryColumn != null) {
                    throw new VersionException(entityClass.getCanonicalName() + " 中包含多个带有 @Id 注解的字段，一个类中只能存在一个带有 @Id 注解的字段!");
                }
                primaryColumn = column;
            }else {
                sql.append(SqlUnitHelper.getIfContains("optional", column, column.getColumnHolder(null, null, ",")));
            }
        }

        if(null != versionColumn){
            Version version = (Version)versionColumn.getEntityField().getAnnotation(Version.class);
            String versionClass = version.nextVersion().getCanonicalName();
            sql.append("<bind name=\"nextVersion\"").append(" value=\"");
            sql.append("@tk.mybatis.mapper.version.VersionUtil@nextVersion(").append("@").append(versionClass).append("@class, ");
            sql.append(versionColumn.getProperty()).append(")\"/>");
            sql.append(versionColumn.getColumn()).append(" = #{nextVersion},");
        }
        sql.append("</set><where>");
        sql.append("AND ").append(primaryColumn.getColumn()).append(" = #{").append(primaryColumn.getProperty()).append("} ");
        if(null != versionColumn){
            sql.append("AND ").append(versionColumn.getColumn()).append(" = #{").append(versionColumn.getProperty()).append("}");
        }
        sql.append("</where>");
        return sql.toString();
    }

    /**
     * 根据ID更新，忽略@Version版本锁
     * @param ms
     * @return
     */
    public String updateById(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumnsIgnoreVersion(entityClass, (String)null, false, false));
        sql.append(SqlHelper.wherePKColumns(entityClass, false));
        return sql.toString();
    }

    /**
     * 根据ID更新，有@Version版本锁
     * @param ms
     * @return
     */
    public String updateByIdOpl(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, (String)null, false, false));
        sql.append(SqlHelper.wherePKColumns(entityClass, true));
        return sql.toString();
    }

    /**
     * 根据ID更新，忽略@Version版本锁
     * @param ms
     * @return
     */
    public String updateByIdSelective(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumnsIgnoreVersion(entityClass, (String)null, true, this.isNotEmpty()));
        sql.append(SqlHelper.wherePKColumns(entityClass, false));
        return sql.toString();
    }

    /**
     * 根据ID更新，有@Version版本锁
     * @param ms
     * @return
     */
    public String updateByIdSelectiveOpl(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, (String)null, true, this.isNotEmpty()));
        sql.append(SqlHelper.wherePKColumns(entityClass, true));
        return sql.toString();
    }
}
