package tk.lokiShelter.server.service;

import tk.lokiShelter.db.domain.LsBrand;

import java.util.List;

public interface BrandService {
    List<LsBrand> listBrand(int pageNum, int pageSize);
}
