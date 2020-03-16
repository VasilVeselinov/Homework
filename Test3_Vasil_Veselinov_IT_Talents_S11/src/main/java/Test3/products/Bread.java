package Test3.products;

import java.util.Random;

public abstract class Bread extends Product {

    public enum BreadType {
        WHITE_BREAD, WHOLE_GRAIN_BREAD;

        public static BreadType getRandomBreadType() {
            BreadType[] meattype = BreadType.values();
            return meattype[new Random().nextInt(meattype.length)];
        }
    }

    private Bread.BreadType type;

    Bread(int processingTime, double price, Bread.BreadType type) {
        super(processingTime, price);
        this.type = type;
    }

    public BreadType getType() {
        return type;
    }
}
