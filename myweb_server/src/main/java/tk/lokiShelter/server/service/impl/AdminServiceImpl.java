package tk.lokiShelter.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lokiShelter.db.dao.*;
import tk.lokiShelter.db.domain.*;
import tk.lokiShelter.server.dao.AdminDao;
import tk.lokiShelter.server.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private LsAdminMapper adminMapper;
    @Autowired
    private AdminDao adminDao;

    @Override
    public LsAdmin selectByUsername(String username) {
        LsAdminExample example = new LsAdminExample();
        example.or().andUsernameEqualTo(username);
        List<LsAdmin> lsUsers = adminMapper.selectByExample(example);
        if (lsUsers==null||lsUsers.size()==0)return null;
        return adminMapper.selectByPrimaryKey(lsUsers.get(0).getId());
    }

    @Override
    public List<LsRole> getRoleList(Long adminId) {
        return adminDao.getRoleList(adminId);
    }
}
