package tk.lokiShelter.server.dao;

import org.apache.ibatis.annotations.Param;
import tk.lokiShelter.db.domain.LsRole;

import java.util.List;

public interface AdminDao {
    List<LsRole> getRoleList(@Param("adminId") Long adminId);
}
