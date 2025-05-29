package com.example.shopwise;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addProductToCart(Long userId, Product product) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }

        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>());
        }

        product = productRepository.save(product);
        
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null && cart.getProducts() != null) {
            cart.getProducts().removeIf(product -> product.getId().equals(productId));
            cartRepository.save(cart);
        }
    }
}
