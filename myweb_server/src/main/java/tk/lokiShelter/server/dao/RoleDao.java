package tk.lokiShelter.server.dao;

import org.apache.ibatis.annotations.Param;
import tk.lokiShelter.db.domain.LsMenu;
import tk.lokiShelter.db.domain.LsRole;

import java.util.List;

public interface RoleDao {

    List<LsMenu> getMenuListByAdmin(@Param("adminId") Long adminId);
    List<LsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

}
