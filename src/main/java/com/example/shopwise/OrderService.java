package com.example.shopwise;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public Order creatOrder(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot create order.");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(cart.getProducts());
        order.setStatus("Pending");

        order = orderRepository.save(order);

        cart.getProducts().clear();
        cartRepository.save(cart);

        return order;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
