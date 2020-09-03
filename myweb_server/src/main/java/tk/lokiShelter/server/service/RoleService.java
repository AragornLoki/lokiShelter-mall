package tk.lokiShelter.server.service;

import org.springframework.stereotype.Service;
import tk.lokiShelter.db.domain.LsMenu;
import tk.lokiShelter.db.domain.LsRole;

import java.util.List;


public interface RoleService {
    List<LsRole> selectByAdminId(long AdminId);

    List<LsMenu> selectMenuByRoleId(long RoleId);

    List<LsMenu> selectMenuByAdminId(long AdminId);


}
