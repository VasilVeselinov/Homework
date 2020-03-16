package Test3.employees_of_the_grill;

import Test3.street_grill.StreetGrill;

abstract class Employees extends Thread {

    private StreetGrill streetGrill;

    Employees(String name ,StreetGrill streetGrill){
        super(name);
        this.streetGrill = streetGrill;
    }

    StreetGrill getStreetGrill() {
        return streetGrill;
    }
}
