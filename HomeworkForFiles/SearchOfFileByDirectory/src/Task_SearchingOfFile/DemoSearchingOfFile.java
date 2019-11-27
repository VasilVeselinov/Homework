package Task_SearchingOfFile;

import TheTask_WithSearchingThread.SearchingThread;

import java.io.File;

public class DemoSearchingOfFile {

    private static int counterOfMainThread = 0;
    private static boolean isFoundOfMain = false;

    public static void main(String[] args) {

        // The directory from which it starts
        File directory = new File("C:\\Users\\ACER\\IdeaProjects\\");
        // The file you want to find
        String searchingFileName = new String("Dealer.java");
        // Searching with a main thread (one thread)
        isExists(directory, searchingFileName);
        if (isFoundOfMain) {
            System.out.println("The searching with a main thread found the file after: " + counterOfMainThread +
                    " times voices the method \"isExists\"");
        } else {
            System.out.println("The searching with a main thread not found the file after: " + counterOfMainThread +
                    " times voices the method \"isExists\"");
        }

        // Searching with more threads
        SearchingThread searchingWithThreads = new SearchingThread(directory, searchingFileName);
        searchingWithThreads.start();
        SearchingThread.threads.add(searchingWithThreads);

    }

    private static boolean isExists(File fileOrDirectory, String fileName) {
        counterOfMainThread++;
//        System.out.println(fileOrDirectory.getPath()); // for debug
        if (fileOrDirectory != null && fileOrDirectory.getName().length() > 0) {
            if (fileOrDirectory.isFile() && fileOrDirectory.getName().equals(fileName)) {
                System.out.println(fileOrDirectory.getPath());
                return isFoundOfMain = true;
            }
            File[] files = fileOrDirectory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    System.out.println(file.getPath());
                    return isFoundOfMain = true;
                }
                if (file.isDirectory()) {
                    isExists(file, fileName);
                }
            }
        }
        return false;
    }
}
