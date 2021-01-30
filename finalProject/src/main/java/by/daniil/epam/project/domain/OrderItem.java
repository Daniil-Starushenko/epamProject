package by.daniil.epam.project.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderItem extends Entity {
    Order order;
    User user;
    private List<Product> productList;
    private int quantity;

    public User getUser() {
        return user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(Product product, int number) {
        productList = new ArrayList<>();
        for (int i = 0; i < number; i++ ) {
            this.productList.add(product);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
