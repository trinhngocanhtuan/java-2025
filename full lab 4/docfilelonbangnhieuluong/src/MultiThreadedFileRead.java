import java.io.*;
import java.util.concurrent.*;

public class MultiThreadedFileRead {
    public static void main(String[] args) {
        String inputFilename = "docfilelonbangnhieuluong\\src\\largefile.txt";
        String outputDirectory = "D:/codetungmon/java/full lab 4/docfilelonbangnhieuluong/output/";
        int chunkSize = 1024 * 1024; // 1MB mỗi đoạn

        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        try (RandomAccessFile file = new RandomAccessFile(inputFilename, "r")) {
            long fileSize = file.length();
            int numThreads = (int) Math.ceil((double) fileSize / chunkSize);
            System.out.println("File size: " + fileSize + " bytes, Total threads: " + numThreads);

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            CountDownLatch latch = new CountDownLatch(numThreads);

            for (int i = 0; i < numThreads; i++) {
                long start = i * chunkSize;
                int partIndex = i;
                long end = Math.min(start + chunkSize, fileSize); // Giới hạn kích thước đọc
                executor.execute(() -> {
                    try (RandomAccessFile partFile = new RandomAccessFile(inputFilename, "r");
                         FileOutputStream partOutput = new FileOutputStream(outputDirectory + "part_" + partIndex + ".txt")) {
                        partFile.seek(start);
                        byte[] buffer = new byte[(int) (end - start)];
                        int bytesRead = partFile.read(buffer);
                        partOutput.write(buffer, 0, bytesRead);
                        System.out.println("Thread " + partIndex + " saved " + bytesRead + " bytes to " + outputDirectory + "part_" + partIndex + ".txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await(); // Chờ tất cả các luồng hoàn thành
            executor.shutdown();
            System.out.println("File has been successfully split into parts and saved in " + outputDirectory);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
