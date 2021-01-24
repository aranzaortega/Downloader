package aios;

import java.io.File;
import java.net.URL;

/**
 * This interface will contain all the functions
 * that can be done with a download
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public interface DownloadTask {

    URL getURL();
    File getDestinationPath();
    double getProgress();
    double getRemainingTime();
    long getPendingByteCount();
    long getTotalByteCount();
    void performBinaryDownload();
    void performTextDownload();

}
