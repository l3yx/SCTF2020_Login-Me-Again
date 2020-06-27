package org.syclover.srpingbootshiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.UUID;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (!UUID.randomUUID().toString().replaceAll("-","").equals(username)){
            throw new UnknownAccountException("unkown user");
        }
        return new SimpleAuthenticationInfo(username, UUID.randomUUID().toString().replaceAll("-",""), getName());
    }
}
