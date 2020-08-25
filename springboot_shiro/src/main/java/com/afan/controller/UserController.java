package com.afan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create By User on 2020/8/24
 */
@Controller
public class UserController {

    /**
     * 测试方法
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("UserController.hello()");
        return "ok";
    }

    /**
     * 测试thymeleaf
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name", "阿饭先生");
        //返回test.html
        return "test";
    }

    /**
     * 用户添加
     */
    @RequestMapping("/add")
    public String add(Model model){
        return "/user/add";
    }
    /**
     * 用户更新
     */
    @RequestMapping("/update")
    public String update(Model model){
        return "/user/update";
    }

    /**
     * 登陆页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "/login";
    }

    /**
     * 登陆页面
     */
    @RequestMapping("/unAuth")
    public String unAuth(Model model){
        return "/unAuth";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        System.out.println("name: "+name);
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3.执行登录方法，有异常则说明登录失败，没有则登录成功
        try{
            //验证是否合法
            subject.login(token);  //执行后会进入UserRealm的认证方法进行认证，然后通过抛出异常，判断是否验证成功
            //登录成功，shiro登录验证成功后，会对后面的资源进行放行
            //跳转到test.html
            return "redirect:/testThymeleaf";
        }catch (UnknownAccountException e){
            //e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //e.printStackTrace();
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
