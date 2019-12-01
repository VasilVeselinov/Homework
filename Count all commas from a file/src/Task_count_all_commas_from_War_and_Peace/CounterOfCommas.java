package Task_count_all_commas_from_War_and_Peace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CounterOfCommas extends Thread {

    private String stringForThread;
    private static int totalNumberOfCommas;
    private static int operationsCounterForEachThread; // For demo outbound purposes
    private static ArrayList<CounterOfCommas> treads = new ArrayList<>(); // For demo outbound purposes

    private CounterOfCommas(String stringForThread) {
        this.stringForThread = stringForThread;
    }

    static void buildCounterOfCommasFromFileWhitNumberOfThread(File file, int numberOfThread) {
        // For demo outbound purposes
        int counterOfOperationsForTheThread = 0;
        String textToString = CounterOfCommas.fromFileToString(file);

        for (int i = 1; i <= numberOfThread; i++) {
            int startIndex = calculatesTheStartIndex(i, textToString.length(), numberOfThread);
            int endIndex = calculatesTheEndIndex(i, textToString.length(), numberOfThread);

            String temporaryString = textToString.substring(startIndex, endIndex);

            CounterOfCommas.createThreadAndStartOfCounters(temporaryString);

            // For demo outbound purposes
            counterOfOperationsForTheThread++;
        }

        // For demo outbound purposes
        synchronized (CounterOfCommas.class) {
            CounterOfCommas.addNumberOfNumberOfOperations(counterOfOperationsForTheThread);
        }
    }

    private static String fromFileToString(File file) {
        // For demo outbound purposes
        int counterOfOperationsForTheThread = 0;
        StringBuilder textFromFile = new StringBuilder("");

        try (Scanner inputStream = new Scanner(file)) {
            while (inputStream.hasNextLine()) {
                textFromFile.append(inputStream.nextLine());

                // For demo outbound purposes
                counterOfOperationsForTheThread++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception from class CounterOfCommas in the fromFileToString method, " +
                    "whit " + e.getMessage());
        }

        // For demo outbound purposes
        synchronized (CounterOfCommas.class) {
            CounterOfCommas.addNumberOfNumberOfOperations(counterOfOperationsForTheThread);
        }

        String temporaryString = "";
        temporaryString = textFromFile.substring(0, textFromFile.length());

        return temporaryString;
    }

    private static int calculatesTheStartIndex(int i, int length, int numberOfThread) {
        if (i == 1) {
            return 0;
        } else {
            return length / numberOfThread * (i - 1);
        }
    }

    private static int calculatesTheEndIndex(int i, int length, int numberOfThread) {
        if (i == numberOfThread) {
            return length;
        } else {
            return length / numberOfThread * i;
        }
    }

    private static void createThreadAndStartOfCounters(String stringForThread) {
        CounterOfCommas temporaryVariable = new CounterOfCommas(stringForThread);
        CounterOfCommas.treads.add(temporaryVariable);
        temporaryVariable.start();
    }


    @Override
    public void run() {
        this.counterOfCommasOfTheThreadStart(this.stringForThread);

        // For demo outbound purposes
        CounterOfCommas.removeCounterOfCommasAfterEnd(this);
        if (CounterOfCommas.treads.size() == 0) {
            System.out.println("number of commas whit threads: " + CounterOfCommas.totalNumberOfCommas);
            System.out.println("number of operations whit threads: " + CounterOfCommas.operationsCounterForEachThread);
        }
    }

    // For outbound purposes
    private static synchronized void removeCounterOfCommasAfterEnd(CounterOfCommas counterOfCommas) {
        CounterOfCommas.treads.remove(counterOfCommas);
    }

    private void counterOfCommasOfTheThreadStart(String inputString) {
        String[] arrayOfTextSplitByCommas = inputString.split(",");
        int numberOfCommas = arrayOfTextSplitByCommas.length - 1;

        synchronized (CounterOfCommas.class) {
            CounterOfCommas.addNumberOfCommas(numberOfCommas);
        }
    }

    // For demo outbound purposes
    private static synchronized void addNumberOfCommas(int numberOfCommas) {
        CounterOfCommas.totalNumberOfCommas += numberOfCommas;
    }

    // For demo outbound purposes
    private static synchronized void addNumberOfNumberOfOperations(int numberOfOperations) {
        CounterOfCommas.operationsCounterForEachThread += numberOfOperations;
    }
}
