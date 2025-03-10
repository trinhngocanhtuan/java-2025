import java.io.*;
import java.util.concurrent.*;

public class MergeFiles {
    public static void main(String[] args) {
        String[] inputFiles = {"D:/codetungmon/java/full lab 4/bai6/src/file1.txt", "D:/codetungmon/java/full lab 4/bai6/src/file2.txt"};
        String outputFile = "D:/codetungmon/java/full lab 4/bai6/src/merged.txt";
        ExecutorService executor = Executors.newFixedThreadPool(inputFiles.length);
        CountDownLatch latch = new CountDownLatch(inputFiles.length);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String file : inputFiles) {
                executor.execute(() -> {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        synchronized (writer) {
                            writer.write(content.toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await(); // Chờ tất cả các luồng hoàn thành
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
