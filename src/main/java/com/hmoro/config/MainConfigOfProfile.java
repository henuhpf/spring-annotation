package com.hmoro.config;

import com.hmoro.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Profile :
 *          Spring提供的可以根据当前环境，动态的激活和切换一系列bean的功能
 *
 * 开发环境、测试环境、生产环境
 * @Profile : 指定组件在哪个环境的情况下才能被注册到容器中，不指定则在任何环境下都能注册这个组件
 *      1、加了 环境标识的bean，只有在这个环境被激活的时候才能被注册到容器中 ,默认是 default 环境
 *      2、写在配置类上，只有是指定的环境时，整个配置类的所有配置才能开始生效
 *      3、没有标注环境标识的bean，在任何环境下都是加载的
 *
 * 切换环境 :
 *      1、使用命令行动态参数，在虚拟机参数位置加载 -Dspring.profiles.active=test
 *      2、代码方式激活环境, IOCTest_Profile 测试类中的test02()
 *
 */

//@Profile("test")
@PropertySource("classpath:dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    @Value("${db.user}")
    private String user;

    private StringValueResolver valueResolver;
    private String driverClass;
    @Value("${db.url}")
    private String url;


    @Bean
    public Yellow yellow(){
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(pwd);

        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true");
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true");
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true");
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
