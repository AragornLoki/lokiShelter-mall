package tk.lokiShelter.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lokiShelter.db.dao.LsAdminPermissionRelationMapper;
import tk.lokiShelter.db.dao.LsPermissionMapper;
import tk.lokiShelter.db.domain.LsAdminPermissionRelation;
import tk.lokiShelter.db.domain.LsAdminPermissionRelationExample;
import tk.lokiShelter.db.domain.LsPermission;
import tk.lokiShelter.server.service.PermissionService;

import java.util.LinkedList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private LsPermissionMapper permissionMapper;
    @Autowired
    private LsAdminPermissionRelationMapper adminPermissionRelationMapper;

    @Override
    public List<LsPermission> selectByAdminId(long id) {
        LsAdminPermissionRelationExample adminPermissionRelationExample=new LsAdminPermissionRelationExample();
        adminPermissionRelationExample.or().andAdminIdEqualTo(id);
        List<LsAdminPermissionRelation> PRelations = adminPermissionRelationMapper.selectByExample(adminPermissionRelationExample);
        List<LsPermission> permissions=new LinkedList<>();
        for (LsAdminPermissionRelation PRelation:
                PRelations) {
            LsPermission lspermission = permissionMapper.selectByPrimaryKey(PRelation.getPermissionId());
            permissions.add(lspermission);
        }
        return permissions;
    }
}
