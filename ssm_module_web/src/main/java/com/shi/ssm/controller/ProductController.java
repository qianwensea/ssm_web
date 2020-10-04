package com.shi.ssm.controller;

import com.shi.ssm.domain.Product;
import com.shi.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-09-30 16:59
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 添加产品
     */
    @RequestMapping("/save.do")
    public String saveProduct(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
