package by.daniil.epam.project.domain;

public enum OrderingStatus {
    IN_BASKET("in_basket"),
    IN_TRANSIT("in_transit"),
    READY("ready");

    private String name;

    OrderingStatus(String name) {
        this.name = name;
    }

    public static OrderingStatus getByTag(String status) {
        return OrderingStatus.valueOf(status);
    }
}
