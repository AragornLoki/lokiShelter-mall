package tk.lokiShelter.server.service;

import org.springframework.stereotype.Service;
import tk.lokiShelter.db.domain.LsPermission;

import java.util.List;


public interface PermissionService {
    List<LsPermission> selectByAdminId(long id);
}
