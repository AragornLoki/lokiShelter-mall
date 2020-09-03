package tk.lokiShelter.server.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.lokiShelter.db.dao.LsProductMapper;
import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.db.domain.LsProductExample;
import tk.lokiShelter.server.dto.ProductQueryBean;
import tk.lokiShelter.server.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private LsProductMapper productMapper;

    @Override
    public LsProduct getOne(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LsProduct> getList(ProductQueryBean productQueryBean, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        LsProductExample productExample = new LsProductExample();
        LsProductExample.Criteria criteria = productExample.createCriteria();
        if (productQueryBean.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryBean.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryBean.getKeyword())) {
            criteria.andNameLike("%" + productQueryBean.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryBean.getProductSn())) {
            criteria.andProductSnEqualTo(productQueryBean.getProductSn());
        }
        if (productQueryBean.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryBean.getBrandId());
        }
        criteria.andDeleteStatusEqualTo(0);
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


    @Override
    public int update(Long id, LsProduct product) {
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public long countAll() {
        LsProductExample example = new LsProductExample();
        example.or().andIdIsNotNull().andDeleteStatusEqualTo(1);
        return productMapper.countByExample(example);
    }


}
