package tk.lokiShelter.server.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lokiShelter.db.dao.LsProductMapper;
import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.db.domain.LsProductExample;
import tk.lokiShelter.server.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private LsProductMapper productMapper;
    @Override
    public List<LsProduct> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        LsProductExample productExample = new LsProductExample();
        productExample.or().andDeleteStatusEqualTo(0);
        return productMapper.selectByExample(productExample);
    }

    @Override
    public int create(LsProduct product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        LsProduct record = new LsProduct();
        record.setDeleteStatus(deleteStatus);
        LsProductExample example = new LsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }
}
