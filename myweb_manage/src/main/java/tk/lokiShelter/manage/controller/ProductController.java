package tk.lokiShelter.manage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.manage.api.PageBean;
import tk.lokiShelter.manage.api.ResponseBean;
import tk.lokiShelter.server.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
//@PreAuthorize("hasRole('系统管理员')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseBean<PageBean<LsProduct>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<LsProduct> list = productService.getList(pageSize, pageNum);
        return ResponseBean.success(PageBean.restPage(list));
    }

    @PostMapping("/create")
    public ResponseBean create(@RequestBody LsProduct product) {
        int count = productService.create(product);
        if (count > 0) {
            return ResponseBean.success(count);
        } else {
            return ResponseBean.fail();
        }
    }

    @PostMapping("/update/deleteStatus")
    public ResponseBean deleteStatus(@RequestParam("ids") List<Long> ids,@RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return ResponseBean.success(count);
        } else {
            return ResponseBean.fail();
        }
    }




}
