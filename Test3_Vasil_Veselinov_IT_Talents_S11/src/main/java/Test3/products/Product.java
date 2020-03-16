package Test3.products;

public abstract class Product {

    private int processingTime;
    private double price;

    Product(int processingTime, double price) {
        this.processingTime = processingTime;
        this.price = price;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public double getPrice() {
        return price;
    }
}
