package Test3.products;

import java.util.Random;

public abstract class Garnish extends Product {

    public enum GarnishType {
        RUSIAN_SALAD, LUTENICA, SNOW_WHITE, CABBAGE_AND_CARROTS, TOMATOES_AND_CUCUMBERS;

        public static GarnishType getRandomGarnishType() {
            GarnishType[] meattype = GarnishType.values();
            return meattype[new Random().nextInt(meattype.length)];
        }
    }

    private GarnishType type;

    Garnish(int processingTime, double price, GarnishType type) {
        super(processingTime, price);
        this.type = type;
    }

    public GarnishType getType() {
        return type;
    }
}