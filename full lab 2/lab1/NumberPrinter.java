package lab1;

public class NumberPrinter {
    private int number = 1;
    private final int MAX;

    public NumberPrinter(int max) {
        this.MAX = max;
    }

    public synchronized void printOdd() {
        while (number <= MAX) {
            while (number % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(Thread.currentThread().getName() + " bị gián đoạn!");
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + number);
            number++;
            notifyAll();
        }
    }

    public synchronized void printEven() {
        while (number <= MAX) {
            while (number % 2 == 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(Thread.currentThread().getName() + " bị gián đoạn!");
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + number);
            number++;
            notifyAll();
        }
    }
}
