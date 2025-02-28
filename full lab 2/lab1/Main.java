package lab1;

public class Main {
    public static void main(String[] args) {
        int maxNumber = 10; // Số tối đa muốn in
        NumberPrinter printer = new NumberPrinter(maxNumber);

        Thread oddThread = new Thread(printer::printOdd, "Odd Thread");
        Thread evenThread = new Thread(printer::printEven, "Even Thread");

        oddThread.start();
        evenThread.start();
    }
}
