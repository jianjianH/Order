package jne.com.order;

/**
 * Created by Jne
 * Date: 2015/1/6.
 */
public class Order {
    public int id;
    public String customName;
    public int orderPrice;
    public String country;

    public Order() {
    }

    public Order(int id, String customName, int orderPrice, String country) {
        this.id = id;
        this.customName = customName;
        this.orderPrice = orderPrice;
        this.country = country;
    }
}
