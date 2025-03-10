import java.io.*;

public class CopyFile {
    public static void main(String[] args) {
        String sourceFile = "docvaghidulieutufile\\src\\source.txt";
        String destinationFile = "docvaghidulieutufile\\src\\destination.txt";

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
