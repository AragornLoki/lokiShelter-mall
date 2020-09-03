package tk.lokiShelter.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lokiShelter.db.dao.LsAdminRoleRelationMapper;
import tk.lokiShelter.db.dao.LsMenuMapper;
import tk.lokiShelter.db.dao.LsRoleMapper;
import tk.lokiShelter.db.dao.LsRoleMnuRelationMapper;
import tk.lokiShelter.db.domain.*;
import tk.lokiShelter.server.dao.RoleDao;
import tk.lokiShelter.server.service.RoleService;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleSeviceImpl implements RoleService {
    @Autowired
    private LsRoleMapper roleMapper;
    @Autowired
    private LsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<LsRole> selectByAdminId(long AdminId) {
        LsAdminRoleRelationExample adminRoleRelationExample=new LsAdminRoleRelationExample();
        adminRoleRelationExample.or().andAdminIdEqualTo(AdminId);
        List<LsAdminRoleRelation> RRelations = adminRoleRelationMapper.selectByExample(adminRoleRelationExample);
        List<LsRole> roles=new LinkedList<>();
        for (LsAdminRoleRelation RRelation:
                RRelations) {
            LsRole lsRole = roleMapper.selectByPrimaryKey(RRelation.getRoleId());
            roles.add(lsRole);
        }
        return roles;
    }

    @Override
    public List<LsMenu> selectMenuByRoleId(long RoleId) {
        return roleDao.getMenuListByRoleId(RoleId);
    }
    @Override
    public List<LsMenu> selectMenuByAdminId(long AdminId) {
        return roleDao.getMenuListByAdmin(AdminId);
    }
}
