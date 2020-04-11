package com.itcast.englis_news.service.security;

import com.itcast.englis_news.dao.RoleMapper;
import com.itcast.englis_news.dao.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 自定义一个获取用户密码以及权限的方法
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 找不到用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 根据用户名，查找到对应的密码，与权限
        com.itcast.englis_news.common.User user = userMapper.selectUserByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SimpleGrantedAuthority> roles =
                roleMapper.selectByUserID(user.getUid()).stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRname())).collect(Collectors.toList());

        // TODO 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        //两个构造方法,可以选用多参数的那个设置更为详细的信息
        return new User(username,user.getPassword(), roles);
    }
}
