import java.io.*;

public class ListFiles {
    public static void main(String[] args) {
        String directoryPath = "D:/codetungmon/java/full lab 4";
        File folder = new File(directoryPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
}
