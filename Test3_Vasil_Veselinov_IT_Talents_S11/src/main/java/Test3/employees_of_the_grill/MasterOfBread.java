package Test3.employees_of_the_grill;

import Test3.products.Bread;
import Test3.products.WhiteBread;
import Test3.products.WholeGrainBread;
import Test3.street_grill.StreetGrill;

public class MasterOfBread extends Employees {

    public MasterOfBread(String name, StreetGrill streetGrill) {
        super(name, streetGrill);
    }

    @Override
    public void run() {
        while (true){
            this.kneadBread();
        }
    }

    private void kneadBread(){
        try {
            Bread.BreadType randomBreadType = Bread.BreadType.getRandomBreadType();
            switch (randomBreadType) {
                case WHITE_BREAD:
                    WhiteBread whiteBread = new WhiteBread();
                    sleep(whiteBread.getProcessingTime());
                    this.getStreetGrill().addBread(whiteBread);
                    break;
                case WHOLE_GRAIN_BREAD:
                    WholeGrainBread wholeGrainBread = new WholeGrainBread();
                    sleep(wholeGrainBread.getProcessingTime());
                    this.getStreetGrill().addBread(wholeGrainBread);
                    break;
            }
        } catch (InterruptedException e) {
            System.out.println("Exception in kneadBread! " + e.getMessage());
        }
    }
}
