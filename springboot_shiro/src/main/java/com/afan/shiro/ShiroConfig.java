package com.afan.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Create By User on 2020/8/25
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactory(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        //用于存储（需要过滤的路径，过滤器类型）
        Map<String,String> filterMap = new LinkedHashMap<>();

        //放行某些资源，必须放在过滤所有路径之前
        //filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");

        //授权过滤器
        //perms[授权的字符串，可自己定义]
        //注意：当前授权拦截后，shiro会自动跳到未授权页面
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        //设置要过滤的路径
        //过滤指定路径
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");
        //过滤某路径下的子路径
        filterMap.put("/*","authc");

        //拦截后会默认跳到login.jsp的页面，然后我们可以修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        //设置过滤Map集合：filterMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}