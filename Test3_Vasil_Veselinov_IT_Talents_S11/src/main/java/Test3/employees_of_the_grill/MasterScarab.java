package Test3.employees_of_the_grill;

import Test3.products.Meat;
import Test3.products.Meatball;
import Test3.products.Splash;
import Test3.products.Steak;
import Test3.street_grill.StreetGrill;

public class MasterScarab extends Employees {

    public MasterScarab(String name, StreetGrill streetGrill) {
        super(name, streetGrill);
    }

    @Override
    public void run() {
        while (true){
            this.roastMeatOnTheGrill();
        }
    }

    private void roastMeatOnTheGrill(){
        try {
            Meat.MeatType randomMeatType = Meat.MeatType.getRandomMeatType();
            switch (randomMeatType) {
                case STEAK:
                    Steak steak = new Steak();
                    sleep(steak.getProcessingTime());
                    this.getStreetGrill().addMeat(steak);
                    break;
                case SPLASH:
                    Splash splash = new Splash();
                    sleep(splash.getProcessingTime());
                    this.getStreetGrill().addMeat(splash);
                    break;
                case MEATBALL:
                    Meatball meat = new Meatball();
                    sleep(meat.getProcessingTime());
                    this.getStreetGrill().addMeat(meat);
                    break;
            }
        } catch (InterruptedException e) {
            System.out.println("Exeption in roastMeatOnTheGrill! " + e.getMessage());
        }
    }
}
