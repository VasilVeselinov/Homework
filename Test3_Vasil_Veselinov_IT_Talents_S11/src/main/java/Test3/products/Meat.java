package Test3.products;

import java.util.Random;

public abstract class Meat extends Product {

    public enum  MeatType{
        MEATBALL, SPLASH, STEAK;

        public static MeatType getRandomMeatType(){
            MeatType[] meattype = MeatType.values();
            return meattype[new Random().nextInt(meattype.length)];
        }
    }

    private MeatType type;

    Meat(int processingTime, double price, MeatType type) {
        super(processingTime, price);
        this.type = type;
    }

    public MeatType getType() {
        return type;
    }
}
