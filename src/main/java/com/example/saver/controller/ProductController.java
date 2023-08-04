package com.example.saver.controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.saver.model.Product;
import com.example.saver.service.BucketImageService;
import com.example.saver.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final BucketImageService imageService;
    private final ProductService productService;

    public ProductController(BucketImageService imageService, ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @GetMapping()
    public String showProducts(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String getAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(Product product, @RequestParam("photo") MultipartFile photo) throws IOException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(photo.getBytes());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(photo.getBytes().length);

        String fileName = product.getName().replace(" ", "_") + ".jpg";
        product.setPhotoLink(imageService.savePhoto(fileName,inputStream, metadata));

        System.out.println(product);
        productService.add(product);
        return "redirect:/products";
    }

}
