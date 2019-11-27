package TheTask_WithSearchingThread;

import java.io.File;
import java.util.ArrayList;

public class SearchingThread extends Thread {

    private File fileOrDirectory;
    private String fileName;
    private static int counterOfSearchingThreads; // For outbound purposes
    private int counterOfSearchingOfCurrentThreads; // For outbound purposes
    private static boolean isFound = false;
    public static ArrayList<SearchingThread> threads;

    public SearchingThread(File fileOrDirectory, String fileName) {
        this.fileOrDirectory = fileOrDirectory;
        this.fileName = fileName;
        SearchingThread.threads = new ArrayList<>();
    }

    @Override
    public void run() {
        synchronized (SearchingThread.class) {
            if (!SearchingThread.isFound) {
                if (this.fileOrDirectory != null && this.fileOrDirectory.getName().length() > 0) {
                    if (this.isExists(this.fileOrDirectory, this.fileName)) {
                        System.out.println(currentThread().getName() + " found the file after: " +
                                this.counterOfSearchingOfCurrentThreads + " operations");
                        System.out.println("The searching with threads found the file after: " +
                                counterOfSearchingThreads + " times voices the method \"isExists\"");
                    } else {
                        if (!SearchingThread.isFound) {
                            System.out.println(currentThread().getName() + " not found the file after: " +
                                    this.counterOfSearchingOfCurrentThreads + " operations");
                        }
                        SearchingThread.removeOfTreads();
                    }
                }
            }
        }
        SearchingThread.checkForEndOfAllThreads();
    }

    private boolean isExists(File fileOrDirectory, String fileName) {
        SearchingThread.incrementCounterOfSearchingThread();

//        System.out.println("=========" + fileOrDirectory.getName()); // for debug

        if (fileOrDirectory.isFile() && fileOrDirectory.getName().equals(fileName)) {
            this.incrementCounterOfSearchingOfCurrentThreads();
            System.out.println(fileOrDirectory.getPath());
//            System.out.println("In first if"); // for debug
            return SearchingThread.changeValueIfFileIsFound();
        }

        File[] files = fileOrDirectory.listFiles();
        for (File file : files) {
            this.incrementCounterOfSearchingOfCurrentThreads();
            if (file != null && file.getName().length() > 0) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    System.out.println(file.getPath());
//                    System.out.println("In second if"); // for debug
                    return SearchingThread.changeValueIfFileIsFound();
                }

                this.fileIsDirectory(file);
            }
        }

        return false;
    }

    private void fileIsDirectory(File file) {
        if (file.isDirectory()) {
            synchronized (SearchingThread.class) {
                if (!SearchingThread.isFound) {
                    SearchingThread temp = new SearchingThread(file, fileName);
                    SearchingThread.threads.add(temp);
                    temp.start();
                }
            }
        }
    }

    private synchronized static boolean changeValueIfFileIsFound() {
        return SearchingThread.isFound = true;
    }

    private synchronized static void checkForEndOfAllThreads() {
        if (SearchingThread.threads.size() == 0) {
            System.out.println("The searching with threads not found the file after: " +
                    counterOfSearchingThreads + " times voices the method \"isExists\"");
        }
    }

    private synchronized static void removeOfTreads() {
        SearchingThread.threads.remove(currentThread());
    }


    // For outbound purposes
    private synchronized static void incrementCounterOfSearchingThread() {
        SearchingThread.counterOfSearchingThreads++;
    }

    // For outbound purposes
    private synchronized void incrementCounterOfSearchingOfCurrentThreads() {
        this.counterOfSearchingOfCurrentThreads++;
    }
}

