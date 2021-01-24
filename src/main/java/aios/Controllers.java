package aios;

import java.net.MalformedURLException;

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
                    TextDownloader();
                    break;
                case "2":
                    BinaryDownloader();
                    break;
                case "3":
                    ShowDownloads();
                    break;
                case "0":
                    finish = true;
                    break;
                default:
                    System.out.println("Solo números entre 0 y 3");
                    break;
            }
        }
    }

    /**
     * Gets the user data for the text download and
     * calls that task from the DownloadManager
     */
    public static void TextDownloader(){
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
    public static void BinaryDownloader(){
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
     * Shows the current downloads
     * calls that task from the DownloadManager
     */
    public static void ShowDownloads() {
        Application.getDownloadManager().ShowLists();
    }
}
