import java.io.*;

public class WriteReadIntegers {
    public static void main(String[] args) {
        String filename = "ghidanhsachsonguyenvaofilevadoclai\\src\\numbers.txt";
        int[] numbers = {1, 2, 3, 4, 5};

        // Ghi số nguyên vào file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            for (int num : numbers) {
                dos.writeInt(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đọc số nguyên từ file
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            while (dis.available() > 0) {
                System.out.println(dis.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
