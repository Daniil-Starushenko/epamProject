package by.daniil.epam.project.domain;

public class DeliverymanOrder extends Entity {
    private DeliveryMan deliveryMan;

    private Order order;

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}