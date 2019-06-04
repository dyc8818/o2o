package com.dy.o2o.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

//根据不同请求，拦截，用于不同的数据库
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    //u0020表示空格
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    //遇到什么情况做拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //拦截的是不是事务的
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];

        String lookupKey = DynamicDataSourceHolder.DB_MASTER;
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        if (synchronizationActive != true) {

            //读方法
            //如果是select语句
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                //selectKey为自增id查询主键（Select last_insert_id）方法，使用主库（在插入数据后会用select返回主键
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replace("[\\t\\n\\r]", " ");
                    //正则匹配，如果是增删改
                    if (sql.matches(REGEX)) {
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    }
                    //是查询的话
                    else {
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            //如果是事务的，一般涉及到写（增删该），那直接用主库
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.debug("设置方法[{}] use [{}] Strategy,SqlCommandType[{}]..", ms.getId(), lookupKey, ms.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    //返回封装好的对象，返回本体还是代理,如果是Executor类型的就拦截，做增删改查
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor)
            return Plugin.wrap(target, this);
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
