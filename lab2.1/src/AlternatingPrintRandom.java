import java.util.Random;

public class AlternatingPrintRandom {
    // Đối tượng khóa dùng để đồng bộ 2 thread
    private static final Object lock = new Object();
    // Cờ xác định lượt in: true nếu đến lượt in số chẵn, false nếu đến lượt in số lẻ.
    // Lượt in ban đầu được chọn ngẫu nhiên mỗi lần chạy
    private static boolean isEvenTurn = new Random().nextBoolean();
    // Số lần in mỗi thread (với 2 thread sẽ in tổng cộng 20 số)
    private static final int COUNT_PER_THREAD = 10;

    // Thread in số chẵn
    static class EvenThread extends Thread {
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < COUNT_PER_THREAD; i++) {
                synchronized (lock) {
                    while (!isEvenTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // Sinh số chẵn ngẫu nhiên trong khoảng 2 đến 100
                    int evenNumber;
                    do {
                        // rand.nextInt(50) cho ra số từ 0 đến 49, nhân 2 cho ra số chẵn từ 0 đến 98
                        // Sau đó cộng 2 để đảm bảo số chẵn luôn ≥2
                        evenNumber = (rand.nextInt(50) * 2) + 2;
                    } while (evenNumber > 100); // tùy chỉnh giới hạn nếu cần
                    System.out.println("Chẵn: " + evenNumber);
                    isEvenTurn = false;
                    lock.notify();
                }
            }
        }
    }

    // Thread in số lẻ
    static class OddThread extends Thread {
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < COUNT_PER_THREAD; i++) {
                synchronized (lock) {
                    while (isEvenTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // Sinh số lẻ ngẫu nhiên trong khoảng 1 đến 99
                    int oddNumber;
                    do {
                        // rand.nextInt(50) cho ra số từ 0 đến 49, nhân 2 và cộng 1 cho ra số lẻ từ 1 đến 99
                        oddNumber = (rand.nextInt(50) * 2) + 1;
                    } while (oddNumber > 99); // tùy chỉnh giới hạn nếu cần
                    System.out.println("Lẻ: " + oddNumber);
                    isEvenTurn = true;
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Bắt đầu in với " + (isEvenTurn ? "số chẵn" : "số lẻ") + " đầu tiên.");
        Thread evenThread = new EvenThread();
        Thread oddThread = new OddThread();
        evenThread.start();
        oddThread.start();
    }
}
