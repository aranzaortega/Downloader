package aios;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Implementation of the DownloadTask interface
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public class DownloadTaskImpl implements DownloadTask{

    URL url;
    File path;
    long currentBytes;
    long totalBytes;
    double currentPercent;

    /**
     * Constructor of the class
     */
    public DownloadTaskImpl(String url, String path) throws MalformedURLException {
        this.url = new URL(url);
        this.path = new File(path);
    }

    /**
     * Methods for getting the download information
     */
    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public File getDestinationPath() {
        return path;
    }

    @Override
    public double getProgress() {
        return currentPercent;
    }

    @Override
    public double getRemainingTime() {
        return 0;
    }

    @Override
    public long getPendingByteCount() {
        return totalBytes - currentBytes;
    }
    @Override
    public long getTotalByteCount() {
        return totalBytes;
    }

    /**
     *Performs the download in binary format (jpg, png...)
     */
    public void performBinaryDownload() {
        try (FileOutputStream file = new FileOutputStream(path.toString())){
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = url.openStream();
            BufferedInputStream is = new BufferedInputStream(stream);
            int bc;
            byte[] buffer = new byte[1024];
            totalBytes = connection.getContentLengthLong();

            while( (bc = is.read(buffer)) != -1){
                currentBytes += bc;
                currentPercent =  ((double)currentBytes / totalBytes * 100);
                file.write(buffer, 0, bc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Performs the download in txt format
     */
    public void performTextDownload() {
        try (FileWriter file = new FileWriter(path.toString())){
            File fileBytes = new File("file.txt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String text;
            totalBytes = connection.getContentLengthLong();

            while ((text = reader.readLine()) != null){
                file.write(text);
            }
            currentBytes = fileBytes.length();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
