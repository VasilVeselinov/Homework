package Test3.employees_of_the_grill;

import Test3.products.*;
import Test3.street_grill.StreetGrill;

import java.util.ArrayList;

public class MasterOfGarnish extends Employees {

    private static final int NUMBER_OF_UNIT = 5;

    public MasterOfGarnish(String name, StreetGrill streetGrill) {
        super(name, streetGrill);
    }

    @Override
    public void run() {
        while (true){
            this.makeGarnish();
        }
    }

    private void makeGarnish(){
        try {
            Garnish.GarnishType randomGarnishType = Garnish.GarnishType.getRandomGarnishType();
            ArrayList<Garnish> tempList = new ArrayList<>();
            switch (randomGarnishType) {
                case RUSIAN_SALAD:
                    for (int i = 0; i < NUMBER_OF_UNIT; i++) {
                        tempList.add(new RussianSalad());
                    }
                    break;
                case CABBAGE_AND_CARROTS:
                    for (int i = 0; i < NUMBER_OF_UNIT; i++) {
                        tempList.add(new CabbageAndCarrots());
                    }
                    break;
                case TOMATOES_AND_CUCUMBERS:
                    for (int i = 0; i < NUMBER_OF_UNIT; i++) {
                        tempList.add(new TomatoesAndCucumbers());
                    }
                    break;
                case LUTENICA:
                    for (int i = 0; i < NUMBER_OF_UNIT; i++) {
                        tempList.add(new Lutenitsa());
                    }
                    break;
                case SNOW_WHITE:
                    for (int i = 0; i < NUMBER_OF_UNIT; i++) {
                        tempList.add(new SnowWhite());
                    }
                    break;
            }
            sleep(tempList.get(0).getProcessingTime());
            this.getStreetGrill().addGarnish(tempList);
            tempList.clear();
        } catch (InterruptedException e) {
            System.out.println("Exception in makeGarnish! " + e.getMessage());
        }
    }
}
