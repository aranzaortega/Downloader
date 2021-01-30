package aios;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * It will take care of all the tasks that are being attended to
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public class DownloadManager {

    private List<DownloadTask> pendingTasks = new ArrayList<>();
    private List<DownloadTask> ongoingTasks = new ArrayList<>();
    private List<DownloadTask> finishedTasks = new ArrayList<>();

    private ExecutorService service = Executors.newFixedThreadPool(5);
    private Object mutex = new Object();


    /**
     * It will call the execution of the binary download and
     * organize its status in each of the phases
     * @param url for the download content
     * @param path for the download stay
     */
    public void BinaryDownload(String url, String path) throws MalformedURLException {

        DownloadTask binaryTask = new DownloadTaskImpl(url, path);

        synchronized (mutex) {
            pendingTasks.add(binaryTask);
        }

        service.execute(() -> {

            synchronized (mutex) {
                pendingTasks.remove(binaryTask);
                ongoingTasks.add(binaryTask);
            }
            binaryTask.performBinaryDownload();
            synchronized (mutex) {
                ongoingTasks.remove(binaryTask);
                finishedTasks.add(binaryTask);
            }

        });
    }

    /**
     * It will call the execution of the text download and
     * organize its status in each of the phases
     * @param url for the download content
     * @param path for the download stay
     */
    public void TextDownload(String url, String path) throws MalformedURLException {

        DownloadTask textTask = new DownloadTaskImpl(url, path);

        synchronized (mutex) {
            pendingTasks.add(textTask);
        }

        service.execute(() -> {

            synchronized (mutex) {
                pendingTasks.remove(textTask);
                ongoingTasks.add(textTask);
            }
            textTask.performTextDownload();
            synchronized (mutex) {
                ongoingTasks.remove(textTask);
                finishedTasks.add(textTask);
            }
        });
    }

    /**
     * It will show a copy of the pending list
     */
    public List pendingListCopy(){
        return pendingTasks;
    }

    /**
     * It will show a copy of the ongoing list
     */
    public List ongoingListCopy(){
        return ongoingTasks;
    }

    /**
     * It will show a copy of the finished list
     */
    public List finishedListCopy(){
        return finishedTasks;
    }
}
