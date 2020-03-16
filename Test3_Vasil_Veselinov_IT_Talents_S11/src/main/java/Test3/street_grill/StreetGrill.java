package Test3.street_grill;

import Test3.employees_of_the_grill.*;
import Test3.products.Bread;
import Test3.products.Garnish;
import Test3.products.Meat;
import Test3.products.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class StreetGrill {

    private static final int CAPACITY_OF_THE_PANNER = 30;
    private static final int CAPACITY_OF_THE_POT = 20;
    private static final int CAPACITY_OF_THE_PORRINGER = 50;
    private int shop_Id = 1;
    private HashMap<Bread.BreadType, ArrayList<Bread>> breads;
    private HashMap<Meat.MeatType, ArrayList<Meat>> meats;
    private HashMap<Garnish.GarnishType, ArrayList<Garnish>> garnishes;
    private OwnerOfTheStreetGrill ownerOfTheStreetGrill;
    private Saleswoman saleswoman;
    private MasterScarab masterScarab;
    private MasterOfBread masterOfBread;
    private MasterOfGarnish masterOfGarnish;

    public StreetGrill() {
        this.breads = new HashMap<Bread.BreadType, ArrayList<Bread>>();
        for (Bread.BreadType type : Bread.BreadType.values()) {
            this.breads.put(type, new ArrayList<Bread>());
        }

        this.meats = new HashMap<Meat.MeatType, ArrayList<Meat>>();
        for (Meat.MeatType type : Meat.MeatType.values()) {
            this.meats.put(type, new ArrayList<Meat>());
        }

        this.garnishes = new HashMap<Garnish.GarnishType, ArrayList<Garnish>>();
        for (Garnish.GarnishType type : Garnish.GarnishType.values()) {
            this.garnishes.put(type, new ArrayList<Garnish>());
        }

        this.ownerOfTheStreetGrill = new OwnerOfTheStreetGrill("Owner", this);
        this.ownerOfTheStreetGrill.start();
        this.saleswoman = new Saleswoman("Saleswoman", this);
        this.saleswoman.start();
        this.masterScarab = new MasterScarab("MasterScarab", this);
        this.masterScarab.start();
        this.masterOfBread = new MasterOfBread("MasterOfBread", this);
        this.masterOfBread.start();
        this.masterOfGarnish = new MasterOfGarnish("MasterOfGarnish", this);
        this.masterOfGarnish.start();
    }

    public synchronized void addBread(Bread bread) {
        while (this.breads.get(bread.getType()).size() == CAPACITY_OF_THE_PANNER) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception in addBread method! " + e.getMessage());
            }
        }

        System.out.println("MasterOfBread put " + bread.getType());
        this.breads.get(bread.getType()).add(bread);
        notifyAll();
    }

    public synchronized void addMeat(Meat meat) {
        while (this.meats.get(meat.getType()).size() == CAPACITY_OF_THE_POT) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception in addBread method! " + e.getMessage());
            }
        }

        System.out.println("MasterScarab put " + meat.getType());
        this.meats.get(meat.getType()).add(meat);
        notifyAll();
    }

    public synchronized void addGarnish(ArrayList<Garnish> garnishList) {
        for (Garnish garnish : garnishList) {
            if (!this.garnishes.containsKey(garnish.getType())) {
                this.garnishes.put(garnish.getType(), new ArrayList<Garnish>());
            }
            while (this.garnishes.get(garnish.getType()).size() == CAPACITY_OF_THE_PORRINGER) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Exception in addBread method! " + e.getMessage());
                }
            }

            System.out.println("MasterOfGarnish put " + garnish.getType());
            this.garnishes.get(garnish.getType()).add(garnish);
            notifyAll();
        }
    }

    public synchronized ArrayList<Product>
    removeFromPannerAndPotAndPorringer(Bread.BreadType bread, Meat.MeatType meat, Garnish.GarnishType garnish) {
        ArrayList<Product> portion = new ArrayList<Product>();
        // add bread
        System.out.println("Saleswoman start remove");
        while (true) {
            if (this.breads.containsKey(bread)) {
                int lastIndex = this.breads.get(bread).size() - 1;
                if (lastIndex >= 0) {
                    Bread tempBread = this.breads.get(bread).get(lastIndex);
                    System.out.println("Saleswoman get " + bread);
                    portion.add(tempBread);
                    this.breads.get(bread).remove(lastIndex);
                    break;
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Exception in while of after remove bread!" + e.getMessage());
                    }
                }
            }
        }

        // add meat
        while (true) {
            if (this.meats.containsKey(meat)) {
                int lastIndex = this.meats.get(meat).size() - 1;
                if (lastIndex >= 0) {
                    Meat tempMeat = this.meats.get(meat).get(lastIndex);
                    System.out.println("Saleswoman get " + meat);
                    portion.add(tempMeat);
                    this.meats.get(meat).remove(lastIndex);
                    break;
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Exception in while of after remove meat!" + e.getMessage());
                    }
                }
            }
        }

        // add garnish
        ArrayList<Garnish> garnishPortion = new ArrayList<>();
        while (true) {
            if (this.garnishes.containsKey(garnish)) {
                for (int i = 0; i < 2; i++) {
                    int lastIndex = this.garnishes.get(garnish).size() - 1;
                    if (lastIndex >= 0) {
                        Garnish tempGarnish = this.garnishes.get(garnish).get(lastIndex);
                        System.out.println("Saleswoman get " + garnish);
                        garnishPortion.add(tempGarnish);
                        this.garnishes.get(garnish).remove(lastIndex);
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println("Exception in while of after remove garnish!" + e.getMessage());
                        }
                    }
                }
                portion.addAll(garnishPortion);
                break;
            }
        }
        notifyAll();
        System.out.println("Saleswoman is ready");
        return portion;
    }

    public Saleswoman getSaleswoman() {
        return saleswoman;
    }

    public OwnerOfTheStreetGrill getOwnerOfTheStreetGrill() {
        return ownerOfTheStreetGrill;
    }

    public int getShop_Id() {
        return shop_Id;
    }
}
