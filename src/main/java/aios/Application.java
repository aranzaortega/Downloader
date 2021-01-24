package aios;

/**
 * It will be in charge of storing all those
 * global variables of our application
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public class Application {
    private static DownloadManager downloadManager = new DownloadManager();

    public static DownloadManager getDownloadManager() {
        return downloadManager;
    }
}
