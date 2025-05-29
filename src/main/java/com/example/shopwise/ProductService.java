package com.example.shopwise;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product createProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("This is a sample product");
        product.setPrice(19.99);
        product.setCategory("Electronics");

        return productRepository.save(product);
    }

    public void addSampleProduct() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setDescription("Latest smartphone with OLED Display");
        product.setPrice(999.99);
        product.setCategory("Electronics");

        productRepository.save(product);
    }

    @Component
    public class SampleDataLoader implements CommandLineRunner {
        private final ProductService productService;

        public SampleDataLoader(ProductService productService) {
            this.productService = productService;
        }
        
        @Override
        public void run(String... args) throws Exception {
            productService.addSampleProduct();
        }
    }
}