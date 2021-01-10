package by.daniil.epam.project.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderItem extends Entity {
    private List<Product> productList = new ArrayList();
    private int quantity;
}
