package com.geek.life.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义的CustomRealm继承AuthorizingRealm。
 *               并且重写父类中的doGetAuthorizationInfo（权限相关）、doGetAuthenticationInfo（身份认证）这两个方法。
 * @Author: 屈艳锋
 * @Date: 2019/7/3 19:30
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        stringSet.add("user:list");
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        String password = "2415b95d3203ac901e287b76fcef640b";
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(userPwd)) {
            throw new AccountException("密码不正确");
        }
//        String md5Pwd = new SimpleHash("MD5", pwd,
//                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(userName + "salt"), getName());

    }
//    /**
//     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
//     * private UserService userService;
//     * 获取即将需要认证的信息
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("-------身份认证方法--------");
//        String userName = (String) authenticationToken.getPrincipal();
//        String userPwd = new String((char[]) authenticationToken.getCredentials());
//        //根据用户名从数据库获取密码
//        String password = "123";
//        if (userName == null) {
//            throw new AccountException("用户名不正确");
//        } else if (!userPwd.equals(password )) {
//            throw new AccountException("密码不正确");
//        }
//        return new SimpleAuthenticationInfo(userName, password,getName());
//    }
}

