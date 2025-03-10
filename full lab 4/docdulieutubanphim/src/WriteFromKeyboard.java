import java.io.*;

public class WriteFromKeyboard {
    public static void main(String[] args) {
        String filename = "docdulieutubanphim\\src\\keyboard_input.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(filename)) {
            System.out.println("Enter text (type 'exit' to finish):");
            String line;
            while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
