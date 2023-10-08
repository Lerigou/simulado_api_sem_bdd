package com.example.api_pedido_produto.services;

import com.example.api_pedido_produto.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private List<ProductEntity> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    public List<ProductEntity> searchProducts(){
        return products;
    }

    public ProductEntity newProduct(ProductEntity product){
        products.add(product);
        return product;
    }

    public ProductEntity searchProduct(Long product_id) throws Exception{
        Optional<ProductEntity> product = products.stream().filter(p -> p.getProduct_id() == product_id).findFirst();

        if (product.isPresent()){
            return product.get();
        } else {
            throw new Exception("Product not found!");
        }
    }

    public ProductEntity editProduct(Long product_id, ProductEntity product) throws Exception{
        Optional<ProductEntity> editedProduct = products.stream().filter(p -> p.getProduct_id() == product_id).findFirst();

        if(editedProduct.isPresent()){
            editedProduct.get().setName(product.getName());
            editedProduct.get().setDescription(product.getDescription());
            editedProduct.get().setPrice(product.getPrice());
            return product;
        } else {
            throw new Exception("Product not found!");
        }
    }

    /* Resolução 2 para o edit
    public Product editProduct(Long id, Product updatedProduct) throws Exception {
        ListIterator<Product> iterator = products.listIterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }
        }
        throw new Exception("Produto não encontrado!");
    } */

    public void deleteProduct(Long product_id){
        products.removeIf(product -> product.getProduct_id() == product_id);
    }
}
