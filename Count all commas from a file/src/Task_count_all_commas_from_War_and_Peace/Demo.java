package Task_count_all_commas_from_War_and_Peace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {

    private static int operationsCounter; // For demo outbound purposes

    public static void main(String[] args) {

        File inputFile = new File("War and Peace.txt");
        int numberOfCommas = counterOfCommasWhitOneThread(inputFile);
        System.out.println("number of commas: " + numberOfCommas);

        // For demo outbound purposes
        System.out.println("number of operations: " + operationsCounter);

        System.out.println("======================================");

        CounterOfCommas.buildCounterOfCommasFromFileWhitNumberOfThread(inputFile, 2);
    }

    private static int counterOfCommasWhitOneThread(File inputFile) {
        StringBuilder textFromFile = new StringBuilder("");

        try (Scanner inputStream = new Scanner(inputFile)) {
            while (inputStream.hasNextLine()) {
                textFromFile.append(inputStream.nextLine());

                // For demo outbound purposes
                operationsCounter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception from class CounterOfCommas in the fromFileToString method, " +
                    "whit " + e.getMessage());
        }

        String tempString = "";
        tempString = textFromFile.substring(0, textFromFile.length());
        String[] temp = tempString.split(",");
        int numberOfCommas = temp.length - 1;

        return numberOfCommas;
    }
}
