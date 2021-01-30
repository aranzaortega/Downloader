package aios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Contains all the logic functions at the application level
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public class Controllers {

    /**
     * Controls the cases in main menu
     */
    public static void MainMenu(){
        String userChoice;
        boolean finish = false;

        while(!finish){
            UserInterface.printMainMenu();
            userChoice = UserInterface.readUserLine();

            switch (userChoice) {
                case "1":
                    TextDownloadsController();
                    break;
                case "2":
                    BinaryDownloadsController();
                    break;
                case "3":
                    ShowDownloadsController();
                    break;
                case "0":
                    finish = true;
                    break;
                default:
                    System.out.println("Solo números entre 0 y 3");
                    break;
            }
        }
        System.exit(0);
    }

    /**
     * Gets the user data for the text download and
     * calls that task from the DownloadManager
     */
    public static void TextDownloadsController(){
        UserInterface.printUrlAsk();
        String url = UserInterface.readUserLine();
        UserInterface.printPathAsk();
        String path = UserInterface.readUserLine();

        try {
            Application.getDownloadManager().TextDownload(url, path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the user data for the binary download and
     * calls that task from the DownloadManager
     */
    public static void BinaryDownloadsController(){
        UserInterface.printUrlAsk();
        String url = UserInterface.readUserLine();
        UserInterface.printPathAsk();
        String path = UserInterface.readUserLine();

        try {
            Application.getDownloadManager().BinaryDownload(url, path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Controls the show downloads option
     */
    public static void ShowDownloadsController() {
        UserInterface.printIntroToGo();
        ShowListsWithTimer();
    }

    /**
     * It will show all the lists in the moment that is called
     */
    public static void ShowLists(){

        //Pending Tasks
        List<DownloadTask> pendingTasks = Application.getDownloadManager().pendingListCopy();

        UserInterface.printPendingTasksMenu();
        if (pendingTasks.isEmpty()){
            UserInterface.printEmptyList();
        } else {
            for(DownloadTask currentDownload:pendingTasks){
                URL url = currentDownload.getURL();
                UserInterface.printPendingTasks(url);
            }
        }

        //Ongoing Tasks
        List<DownloadTask> ongoingTasks = Application.getDownloadManager().ongoingListCopy();

        UserInterface.printOngoingTasksMenu();
        if (ongoingTasks.isEmpty()){
            UserInterface.printEmptyList();
        } else {
            for(DownloadTask currentDownload:ongoingTasks){
                URL url = currentDownload.getURL();
                double currentPercent = currentDownload.getProgress();
                long pendingBytes = currentDownload.getPendingByteCount();
                long totalBytes = currentDownload.getTotalByteCount();
                UserInterface.printOngoingTasks(url, pendingBytes, totalBytes, currentPercent);
            }
        }

        //Finished Tasks
        List<DownloadTask> finishedTasks = Application.getDownloadManager().finishedListCopy();

        UserInterface.printFinishedTasksMenu();
        if (finishedTasks.isEmpty()){
            UserInterface.printEmptyList();
        } else {
            for(DownloadTask currentDownload:finishedTasks){
                URL url = currentDownload.getURL();
                UserInterface.printFinishedTasks(url);
            }
        }
    }

    /**
     * It will show all the lists
     */
    public static void ShowListsWithTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ShowLists();
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(task, 1000, 3000);

        UserInterface.readUserLine();
        timer.cancel();
        timer.purge();
    }
}
