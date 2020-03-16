package Test3.street_grill;

import Test3.Customer;
import Test3.products.Bread;
import Test3.products.Garnish;
import Test3.products.Meat;

public class Order {

    private Bread.BreadType breadType;
    private Meat.MeatType meatType;
    private Garnish.GarnishType garnishType;
    private Customer customer;

    public Order(Bread.BreadType breadType, Meat.MeatType meatType, Garnish.GarnishType garnishType, Customer customer) {
        this.breadType = breadType;
        this.meatType = meatType;
        this.garnishType = garnishType;
        this.customer = customer;
    }

    public Bread.BreadType getBreadType() {
        return breadType;
    }

    public Meat.MeatType getMeatType() {
        return meatType;
    }

    public Garnish.GarnishType getGarnishType() {
        return garnishType;
    }

    public Customer getCustomer() {
        return customer;
    }
}
