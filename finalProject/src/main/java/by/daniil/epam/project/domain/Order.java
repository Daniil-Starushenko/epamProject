package by.daniil.epam.project.domain;

public class Order extends Entity {
    private User customer;
    private String address;
    private String dateOfOrdering;
    private String phoneNumber;
    private Double totalPrice;
    private OrderingStatus status;
    private DeliveryMan deliveryManForOrder;

    public DeliveryMan getDeliveryManForOrder() {
        return deliveryManForOrder;
    }

    public void setDeliveryManForOrder(DeliveryMan deliveryManForOrder) {
        this.deliveryManForOrder = deliveryManForOrder;
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

    public String getDateOfOrdering() {
        return dateOfOrdering;
    }

    public void setDateOfOrdering(String dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
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
