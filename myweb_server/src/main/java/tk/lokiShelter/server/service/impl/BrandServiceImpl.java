package tk.lokiShelter.server.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lokiShelter.db.dao.LsBrandMapper;
import tk.lokiShelter.db.domain.LsBrand;
import tk.lokiShelter.db.domain.LsBrandExample;
import tk.lokiShelter.server.service.BrandService;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private LsBrandMapper brandMapper;
    @Override
    public List<LsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new LsBrandExample());
    }
}
