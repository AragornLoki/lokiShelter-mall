package tk.lokiShelter.server.service;

import tk.lokiShelter.db.domain.LsProduct;

import java.util.List;

public interface ProductService {
    List<LsProduct> getList(Integer pageSize, Integer pageNum);

    int create(LsProduct product);

    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

}
