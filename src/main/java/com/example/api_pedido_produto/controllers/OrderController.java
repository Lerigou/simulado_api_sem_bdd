package com.example.api_pedido_produto.controllers;

import com.example.api_pedido_produto.entities.OrderEntity;
import com.example.api_pedido_produto.entities.ProductEntity;
import com.example.api_pedido_produto.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("add")
    public ResponseEntity<?> newOrder(@RequestBody OrderEntity order){
        try{
            order = orderService.newOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity("Error creating new order",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> searchOrders(){
        try {
            List<OrderEntity> orders = orderService.searchOrders();
            return new ResponseEntity(orders, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Error",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchOrder(@PathVariable("id") Long order_id){
        try {
            OrderEntity order = orderService.searchOrder(order_id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long order_id){
        try {
            orderService.deleteOrder(order_id);
            return new ResponseEntity<>("Order deleted", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public OrderEntity editOrder(@PathVariable Long order_id, @RequestBody OrderEntity updateOrder) throws Exception {
        return orderService.editOrder(order_id, updateOrder);
    }

    @PatchMapping("addProduct/{id}")
    public ResponseEntity<?> addProductsOrder(@RequestBody List<ProductEntity> product, @PathVariable("id") Long product_id){
        try{
            orderService.addProductsOrder(product_id, product);
            return new ResponseEntity<>("Products added successfully!", HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProductsOrder(@RequestBody List<ProductEntity> product, @PathVariable("id") Long product_id){
        try{
            orderService.deleteProductsOrder(product_id, product);
            return new ResponseEntity<>("Products removed successfully!", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
