package Test3.employees_of_the_grill;

import Test3.Connection_To_The_Databases.DBManager;
import Test3.street_grill.StreetGrill;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OwnerOfTheStreetGrill extends Employees {

    private Connection ownerConection = DBManager.getDbManager().getConnectionWithScarajERPSystem();
    private File currentFile;

    public OwnerOfTheStreetGrill(String name, StreetGrill streetGrill) {
        super(name, streetGrill);
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(30000);
                this.getStreetGrill().getSaleswoman().getFileWhitOrdersForToDay().delete();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    private void uploadInScarajERPSystem() {
        synchronized (this.getStreetGrill()) {
            this.getStreetGrill().getSaleswoman().getFileWhitOrdersForToDay().delete();
        }

        String insertSales = "INSERT INTO sales (" +
                "shop_id, " +           // 1
                "bread_type_id, " +     // 2
                "meat_type_id, " +      // 3
                "garnish_type_id," +    // 4
                "date_created" +        // 5
                "VALUES(?,?,?,?,?);";
        try (PreparedStatement ps = this.ownerConection.prepareStatement(insertSales)) {
            // open transaction
            this.ownerConection.setAutoCommit(false);


            // Todo insert sales
            // read from the file row after row
            // arrayFromFourElements from String split by ","
            // create variables: bradType, meatType, garnishType, dateOfCreatedSales
            // first element from arrayFromFourElements = bradType
            // second element from arrayFromFourElements = meatType
            // fourth element from arrayFromFourElements = dateOfCreatedSales
            // third element from arrayFromTwoElements from String split by "-"
            // first element from arrayFromTwoElements = garnishType

            ps.setInt(1, this.getStreetGrill().getShop_Id());
            ps.setString(2, "bradType"); // bread_type
            ps.setString(3, "meatType"); // meat_type
            ps.setString(4, "garnishType"); // garnish_type
            LocalDate date = LocalDate.now();
            ps.setDate(5, Date.valueOf("dateOfCreatedSales")); // date_created

            // execute query
            ps.executeUpdate();


            // commit transaction
            this.ownerConection.commit();
            System.out.println("Successful upload");
        } catch (SQLException e) {
            try {
                // rollback() transaction
                this.ownerConection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                // close transaction
                this.ownerConection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
