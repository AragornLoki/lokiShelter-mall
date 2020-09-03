package tk.lokiShelter.manage.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.manage.api.PageBean;
import tk.lokiShelter.server.dto.ProductQueryBean;
import tk.lokiShelter.manage.api.ResponseBean;
import tk.lokiShelter.server.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${URL}")
    private String URL;
    @Value("${uploadDir}")
    private String uploadDir;
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/list")
    public ResponseBean<PageBean<LsProduct>> getList(ProductQueryBean productQueryBean,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<LsProduct> list = productService.getList(productQueryBean,pageSize, pageNum);
        return ResponseBean.success(PageBean.restPage(list));
    }

    @PostMapping("/create")
    public ResponseBean create(@RequestBody LsProduct product) {
        if(product.getPic()!=null||!product.getPic().isEmpty()) {
            product.setPic(URL + product.getPic());
        }
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

    @GetMapping("/updateInfo/{id}")
    public ResponseBean<LsProduct> getUpdateInfo(@PathVariable Long id) {
        LsProduct product = productService.getOne(id);
        return ResponseBean.success(product);
    }

    @PostMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody LsProduct product) {
        if(product.getPic()!=null||!product.getPic().isEmpty()) {
            product.setPic(URL + product.getPic());
        }
        int count = productService.update(id, product);
        if (count > 0) {
            return ResponseBean.success(count);
        } else {
            return ResponseBean.fail();
        }
    }

    @GetMapping("/countSum")
    public ResponseBean getSum(){
        long count = productService.countAll();

        if (count > 0) {
            return ResponseBean.success(count);
        } else {
            return ResponseBean.fail();
        }
    }

    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public ResponseBean uploadPic(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseBean.fail("文件上传失败");
        }
        String fileName = file.getOriginalFilename();// 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
        String filePath = uploadDir;// 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName.toLowerCase();// 解决中文问题，liunx下中文路径，图片显示问题
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return ResponseBean.success(fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseBean.fail("文件上传失败");

    }

}
