import java.io.*;

public class CountLines {
    public static void main(String[] args) {
        String filename = "tinhsodongtrongfile\\src\\source.txt";
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of lines: " + lineCount);
    }
}
