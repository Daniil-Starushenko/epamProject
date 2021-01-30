package by.daniil.epam.project.domain;

import javax.xml.registry.infomodel.TelephoneNumber;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends Entity {
    private User customer;
    private String address;
    private String dateOfOrdering;
    private String phoneNumber;
    private Double totalPrice;
    private OrderingStatus status;
    private DeliveryMan deliveryManForOrder;
    private List<OrderItem> OrderProducts = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<OrderItem> getOrderProducts() {
        return OrderProducts;
    }

    public void setOrderProducts(List<OrderItem> orderProducts) {
        OrderProducts = orderProducts;
    }

    public DeliveryMan getDeliveryManForOrder() {
        return deliveryManForOrder;
    }

    public void setDeliveryManForOrder(DeliveryMan deliveryManForOrder) {
        this.deliveryManForOrder = deliveryManForOrder;
    }

    public String getDateOfOrdering() {
        return dateOfOrdering;
    }

    public void setDateOfOrdering(String dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderingStatus getStatus() {
        return status;
    }

    public void setStatus(OrderingStatus status) {
        this.status = status;
    }
}
