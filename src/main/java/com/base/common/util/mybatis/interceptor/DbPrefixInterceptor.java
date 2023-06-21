package com.base.common.util.mybatis.interceptor;

import com.base.common.cache.DbPreCache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DbPrefixInterceptor implements Interceptor {
    Logger logger = LoggerFactory.getLogger(DbPrefixInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        // 获取原始 SQL
        String origSql = boundSql.getSql();
        /**
         * 拼接数据库库前缀
         */
        String newSql = this.replaceDbPr(origSql);

        /**
         * 通过反射获取属性并设置新属性
         */
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, newSql);
        return invocation.proceed();
    }

    /**
     * 替换 SQL 表前缀数据库
     * @param origSql
     * @return
     */
    private String replaceDbPr(String origSql){
        Map<String, String> prepare = DbPreCache.getPrepare();
        return origSql;
    }
}
