package tk.lokiShelter.server.service;

import org.springframework.stereotype.Service;
import tk.lokiShelter.db.domain.LsAdmin;
import tk.lokiShelter.db.domain.LsPermission;
import tk.lokiShelter.db.domain.LsRole;

import java.util.List;


public interface AdminService {

    LsAdmin selectByUsername(String username);

    List<LsRole> getRoleList(Long adminId);
}
