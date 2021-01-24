package aios;

import java.net.URL;
import java.util.Scanner;

/**
 * Represents the User Interface
 * Print screen stuff and read it
 * @author Aranza Ortega
 * @author https://github.com/aranzaortega/Downloader
 * @version 2.0
 * @since 1.0
 */

public class UserInterface {

    private static Scanner input = new Scanner(System.in);

    /**
     * Prints the header style
     * @param header string that represents the text into header style
     * I've made a "flow control" exception for visual reasons only
     */
    public static void printHeader(String header){

        int length = header.length();

        for (int i = 0; i < length + 4; i++){
            System.out.print("-");
        }

        System.out.println("\n  " + header);

        for (int i = 0; i < length + 4; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     *Prints the main menu
     */
    public static void printMainMenu(){
        printHeader("Menú principal");
        System.out.println("1) Descargar una URL en formato texto");
        System.out.println("2) Descargar una URL en formato binario");
        System.out.println("3) Mostrar descargas en curso");
        System.out.println("0) Salir del programa\n\n");
    }

    /**
     *Prints the question to ask the user which URL wants to download
     */
    public static void printUrlAsk(){
        printHeader("Menú de Descarga");
        System.out.println("Introduce una URL para descargar");
    }

    /**
     *Prints the question to ask the user to which PATH wants to store the download
     */
    public static void printPathAsk(){
        System.out.println("Introduce ahora una ruta adonde quieres descargar");
    }

    /**
     *Prints a quote for the user to know that a list is empty
     */
    public static void printEmptyList(){
        System.out.println("Esta lista esta vacía\n\n");
    }

    /**
     *Prints the pending tasks list menu with header style
     */
    public static void printPendingTasksMenu(){
        printHeader("Lista de Descargas pendientes");
    }

    /**
     *Prints the pending tasks list
     * @param url for the user to know which is the download
     */
    public static void printPendingTasks(URL url){
        System.out.println(url + ",\n Está pendiente por descargar\n\n");
    }

    /**
     *Prints the ongoing tasks list menu with header style
     */
    public static void printOngoingTasksMenu(){
        printHeader("Lista de Descargas en curso");
    }

    /**
     *Prints the ongoing tasks list
     * @param url to know which is the download
     * @param pendingBytes to show how many bytes lasts
     * @param totalBytes to show how many bytes are in total
     * @param currentPercent to show the percentage that it takes
     */
    public static void printOngoingTasks(URL url, long pendingBytes, long totalBytes, double currentPercent){
        System.out.println(url + ",\n Se está descargando, faltan " + pendingBytes + " de "
                + totalBytes + "(" + currentPercent + "%) \n\n");
    }

    /**
     *Prints the finished tasks list menu with header style
     */
    public static void printFinishedTasksMenu(){
        printHeader("Lista de Descargas terminadas");

    }

    /**
     *Prints the finished tasks list
     * @param url for the user to know which is the download
     */
    public static void printFinishedTasks(URL url){
        System.out.println(url + ",\n Se ha descargado \n\n");
    }


    /**
     *Reads the strings that the user puts in
     *@return String with the line read
     *
     */
    public static String readUserLine(){
        return input.nextLine();
    }
}
