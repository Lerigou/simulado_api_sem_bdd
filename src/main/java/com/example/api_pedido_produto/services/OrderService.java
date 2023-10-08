package com.example.api_pedido_produto.services;

import com.example.api_pedido_produto.entities.OrderEntity;
import com.example.api_pedido_produto.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private List<OrderEntity> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public OrderEntity newOrder(OrderEntity order) throws Exception{
        orders.add(order);
        return order;
    }

    public List<OrderEntity> searchOrders(){
        return orders;
    }

    public OrderEntity searchOrder(Long order_id) throws Exception {
        Optional<OrderEntity> order = orders.stream().filter(p -> p.getOrder_id() == order_id).findFirst();
        if(order.isPresent()){
            return order.get();
        } else {
            throw new Exception("Order not found!");
        }
    }

    public OrderEntity editOrder(Long order_id, OrderEntity order) throws Exception {
        Optional<OrderEntity> selectedOrder = orders.stream().filter
                (e -> e.getOrder_id() == order_id).findFirst();
        if(selectedOrder.isPresent()){
            selectedOrder.get().setProducts(order.getProducts());
            selectedOrder.get().setQuantity(order.getQuantity());
            selectedOrder.get().setTotal(order.getTotal());
            return order;
        } else {
            throw new Exception("Order not found!");
        }
    }

    public void addProductsOrder(Long order_id, List<ProductEntity> products) throws Exception{
        Optional<OrderEntity> selectedOrder = orders.stream().filter
                (e -> e.getOrder_id() == order_id).findFirst();
        if(selectedOrder.isPresent()){
            selectedOrder.get().addProduct(products);
        } else {
            throw new Exception("Order not found!");
        }
    }

    public void deleteProductsOrder(Long order_id, List<ProductEntity> products) throws Exception{
        Optional<OrderEntity> selectedOrder = orders.stream().filter
                (e -> e.getOrder_id() == order_id).findFirst();
        if(selectedOrder.isPresent()){
            products.stream().forEach(product -> {
                selectedOrder.get().getProducts().removeIf(p -> p.getProduct_id() == product.getProduct_id());
            });
        } else {
            throw new Exception("Order not found!");
        }
    }

    public void deleteOrder(Long order_id) {
        orders.removeIf(order -> order.getOrder_id() == order_id);
    }

}
