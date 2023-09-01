package com.example.storage.controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.storage.model.Product;
import com.example.storage.service.BucketImageService;
import com.example.storage.service.ProductService;
import com.example.storage.utils.ImageCompressor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

        String fileName = product.getName().replace(" ", "_") + ".jpg";
        String thumbnailName = product.getName().replace(" ", "_") + "thumbnail.jpg";

        MultipartFile thumbnailFile = ImageCompressor.compressImage(photo, 200, 200, 1);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(photo.getBytes().length);

        ObjectMetadata thumbnailMetadata = new ObjectMetadata();
        thumbnailMetadata.setContentLength(thumbnailFile.getBytes().length);

        product.setPhotoLink(imageService.savePhoto(fileName, photo.getInputStream(), metadata));
        product.setThumbnailLink(imageService.savePhoto(thumbnailName, thumbnailFile.getInputStream(), thumbnailMetadata));

        productService.add(product);

        return "redirect:/products";
    }

}
