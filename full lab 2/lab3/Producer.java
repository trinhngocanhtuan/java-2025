package lab3;

public class Producer extends Thread {
    private int id;
    private Buffer buffer;
    private static final int MAX_ITERATIONS = 20; // Giới hạn số lần chạy

    public Producer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        int i = 0;
        for (int count = 0; count < MAX_ITERATIONS; count++) {
            try {
                buffer.addProduct(i++, this.id);
                Thread.sleep((long) (Math.random() * 100)); // Giảm tải CPU
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Nhân viên " + id + " đã hoàn thành công việc!");
    }
}
