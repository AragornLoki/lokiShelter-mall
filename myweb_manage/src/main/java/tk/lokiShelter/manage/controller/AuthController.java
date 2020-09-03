package tk.lokiShelter.manage.controller;


import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.lokiShelter.db.domain.LsAdmin;

import tk.lokiShelter.db.domain.LsRole;
import tk.lokiShelter.manage.api.ResponseBean;
import tk.lokiShelter.server.service.AdminService;
import tk.lokiShelter.server.service.RoleService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AuthController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;



    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getAdminInfo(Principal principal) {
        if(principal==null){
            return ResponseBean.unauthorized(null);
        }
        String username = principal.getName();
        LsAdmin lsAdmin = adminService.selectByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", lsAdmin.getUsername());
        data.put("menus", roleService.selectMenuByAdminId(lsAdmin.getId()));
        data.put("icon", lsAdmin.getIcon());
        List<LsRole> roleList = adminService.getRoleList(lsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(LsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return ResponseBean.success(data);
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseUtil logout() {
//        return ResponseUtil.success(null);
//    }

}
