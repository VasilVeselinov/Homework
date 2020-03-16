package Test3;

import Test3.products.Bread;
import Test3.products.Garnish;
import Test3.products.Meat;
import Test3.products.Product;
import Test3.street_grill.Order;
import Test3.street_grill.StreetGrill;

import java.util.ArrayList;

public class Customer extends Thread {

    private StreetGrill streetGrill;
    private ArrayList<Product> portion;

     Customer(StreetGrill streetGrill) {
        this.streetGrill = streetGrill;
        this.portion = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            this.makeOrder();
        }
    }

    private void makeOrder() {
        this.streetGrill.getSaleswoman().addOrder(new Order(Bread.BreadType.getRandomBreadType(),
                Meat.MeatType.getRandomMeatType(),
                Garnish.GarnishType.getRandomGarnishType(),
                this));
    }

    public void setPortion(ArrayList<Product> assemblingTheOrderedPortion) {
        this.portion = assemblingTheOrderedPortion;
    }
}
