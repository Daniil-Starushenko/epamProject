package by.daniil.epam.project.domain;

public enum Role {
    ADMINISTRATOR("admin"),
    USER("user"),
    DELIVERY_MAN("deliveryman");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByTag(String tag) {
        for (Role role: Role.values()) {
            if (role.getName().equals(tag)) {
                return role;
            }
        }
        return null;
    }
}
