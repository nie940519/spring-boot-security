package com.micnie.springbootsecurity.config;/*
 *
 * @autor 聂振
 * @ 2019/3/18  10:20
 */

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        //定制请求的授权规则
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动登录功能
        http.formLogin()
            .loginPage("/userlogin");   //来到自己的登录页面
        //1.login请求来到登录页
        //2，重定向到/login?error表示登录失败
        //3，。。。。
        //效果：如果没有权限，就会来到登录页面
        //默认post形式的请求/login代表处理登录
        //一旦定制loginpage；那么 loginpage的post请求就是登录

        //开启自动配置注销功能
        http.logout().logoutSuccessUrl("/");//注销成功来到首页
        //1,访问/logout 表示用户注销并退出
        //2，注销成功会返回/login?logout页面

        //开启记住我功能
        http.rememberMe();
        //登录成功以后 将cookie发给浏览器保存，以后访问页面会带上这个cookie
        //只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP3");



    }

//    public static PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
}












