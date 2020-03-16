package Test3.employees_of_the_grill;

import Test3.Customer;
import Test3.products.Product;
import Test3.street_grill.Order;
import Test3.street_grill.StreetGrill;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Saleswoman extends Employees {

    private Queue<Order> orders;
    private Order currentOrder;
    private Customer customer;
    private double totalPrise;
    private File fileWhitOrdersForToDay;

    public Saleswoman(String name, StreetGrill streetGrill) {
        super(name, streetGrill);
        this.orders = new ArrayBlockingQueue<>(100);
    }

    @Override
    public void run() {
        while (true) {
            if (this.orders.peek() != null) {
                this.currentOrder = this.orders.poll();
                this.setCustomer(this.currentOrder.getCustomer());
                System.out.println(currentOrder.getBreadType() + " " + currentOrder.getMeatType() + " " + currentOrder.getGarnishType());
                this.customer.setPortion(this.assemblingTheOrderedPortion(currentOrder));
            }
        }
    }

    private ArrayList<Product> assemblingTheOrderedPortion(Order order) {
        ArrayList<Product> portionOfCustomer =
                this.getStreetGrill().removeFromPannerAndPotAndPorringer(order.getBreadType(), order.getMeatType(), order.getGarnishType());
        for (Product product : portionOfCustomer) {
            this.totalPrise += product.getPrice();
        }
        System.out.println("this.totalPrise = " + this.totalPrise);

        synchronized (this.getStreetGrill()) {
            LocalDateTime now = LocalDateTime.now();
            String timeForOrder = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            String nameOfFile = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH")) + ".txt";
            this.fileWhitOrdersForToDay = new File(nameOfFile);
            try (FileOutputStream streamForAppend = new FileOutputStream(this.fileWhitOrdersForToDay, true);
                 PrintStream printStream = new PrintStream(streamForAppend)) {
                if (!this.fileWhitOrdersForToDay.exists()) {
                    this.fileWhitOrdersForToDay.createNewFile();
                }

                printStream.println(this.currentOrder.getBreadType() + ", " + this.currentOrder.getMeatType() + ", " +
                        this.currentOrder.getGarnishType() + " - " + this.totalPrise + " leva, " + timeForOrder);
                this.totalPrise = 0;

            } catch (IOException e) {
                System.out.println("Exception! " + e.getMessage());
            }

            this.getStreetGrill().getOwnerOfTheStreetGrill().setCurrentFile(this.fileWhitOrdersForToDay);
        }
        return portionOfCustomer;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addOrder(Order order) {
        this.orders.offer(order);
    }

    public File getFileWhitOrdersForToDay() {
        return fileWhitOrdersForToDay;
    }
}
