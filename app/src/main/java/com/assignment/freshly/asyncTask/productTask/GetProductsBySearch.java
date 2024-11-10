package com.assignment.freshly.asyncTask.productTask;

import com.assignment.freshly.entity.Product;

import java.util.List;

public interface GetProductsBySearch {
    void onProductsReceived(List<Product> products);
}
