package by.daniil.epam.project.domain;

public enum OrderingStatus {
    IN_BASKET("in_basket"),
    IN_TRANSIT("in_transit"),
    READY("ready");

    private String name;

    OrderingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static OrderingStatus getByTag(String tag) {
        for (OrderingStatus status: OrderingStatus.values()) {
            if (status.getName().equals(tag)) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }
}
