package com.example.api_pedido_produto.controllers;

import com.example.api_pedido_produto.entities.ProductEntity;
import com.example.api_pedido_produto.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public ResponseEntity<?> searchProducts(){
        try {
            List<ProductEntity> productList = productService.searchProducts();
            return new ResponseEntity(productList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Erro na requisição",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchProduct(@PathVariable("id") Long id){
        try {
            ProductEntity product = productService.searchProduct(id);
            return new ResponseEntity(product, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("add")
    public ResponseEntity<?> newProduct(@RequestBody ProductEntity product){
        try{
            product = productService.newProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity("Erro na criação de novo produto",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Produto excluído", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ProductEntity editProduct(@PathVariable Long id, @RequestBody ProductEntity updatedProduct) throws Exception {
        return productService.editProduct(id, updatedProduct);
    }
}
