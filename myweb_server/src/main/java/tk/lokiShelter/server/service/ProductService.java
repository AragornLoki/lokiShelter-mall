package tk.lokiShelter.server.service;

import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.server.dto.ProductQueryBean;

import java.util.List;

public interface ProductService {
    LsProduct getOne(Long id);

    List<LsProduct> getList(ProductQueryBean productQueryBean, Integer pageSize, Integer pageNum);

    int create(LsProduct product);

    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    int update(Long id, LsProduct product);

    long countAll();

}
