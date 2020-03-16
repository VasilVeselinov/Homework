package Test3;

import Test3.street_grill.StreetGrill;

public class Demo {

    public static void main(String[] args) {
        StreetGrill streetGrill = new StreetGrill();
        Customer customer1 = new Customer(streetGrill);
        customer1.start();
    }
}
