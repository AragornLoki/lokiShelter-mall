package tk.lokiShelter.manage.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tk.lokiShelter.db.domain.LsPermission;
import tk.lokiShelter.db.domain.LsAdmin;
import tk.lokiShelter.server.service.AdminService;
import tk.lokiShelter.server.service.PermissionService;


import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new UsernameNotFoundException("管理员用户不能为空");
        }
        LsAdmin user = adminService.selectByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("数据库中无此用户！");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            //获取该用户所拥有的权限
            List<LsPermission> lsPermissions=permissionService.selectByAdminId(user.getId());
            // 声明用户授权
            lsPermissions.forEach(lsPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(lsPermission.getValue());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
