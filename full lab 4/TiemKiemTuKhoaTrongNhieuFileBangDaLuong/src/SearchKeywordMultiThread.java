import java.io.*;
import java.util.concurrent.*;

public class SearchKeywordMultiThread {
    public static void main(String[] args) {
        String[] files = {"TiemKiemTuKhoaTrongNhieuFileBangDaLuong\\src\\file1.txt", "TiemKiemTuKhoaTrongNhieuFileBangDaLuong\\src\\file2.txt", "TiemKiemTuKhoaTrongNhieuFileBangDaLuong\\src\\file3.txt"}; // Danh sách file cần tìm kiếm
        String keyword = "Jack"; // Từ khóa cần tìm

        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        ConcurrentHashMap<String, Integer> results = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(files.length);

        for (String file : files) {
            executor.execute(() -> {
                int count = 0;
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        count += countOccurrences(line, keyword);
                    }
                    results.put(file, count);
                    System.out.println("Từ mày tiềm kiếm nè : " + file + " - nó ra được nứ đó: " + count);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(); // Chờ tất cả các luồng hoàn thành
            executor.shutdown();
            System.out.println("ok chưa mày: " + results);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int countOccurrences(String line, String keyword) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }
        return count;
    }
}