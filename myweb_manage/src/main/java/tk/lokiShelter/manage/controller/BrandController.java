package tk.lokiShelter.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.lokiShelter.db.domain.LsBrand;
import tk.lokiShelter.manage.api.PageBean;
import tk.lokiShelter.manage.api.ResponseBean;
import tk.lokiShelter.server.service.BrandService;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean<PageBean<LsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<LsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return ResponseBean.success(PageBean.restPage(brandList));
    }
}
