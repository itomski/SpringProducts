package de.lubowiecki.sproducts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart implements Serializable {

    private List<Product> products = new ArrayList<>();

    public void buy(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void removeAll() {
        products.clear();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
