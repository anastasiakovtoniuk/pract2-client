
class TicTakPrinter {
    private int count = 0;
    private final int maxCount;
    private boolean isTicTurn = true;

    public TicTakPrinter(int maxCount) {
        this.maxCount = maxCount;
    }


    public synchronized void printTic() throws InterruptedException {

        while (count < maxCount) {

            while (!isTicTurn) {
                wait();
            }

            if (count >= maxCount) break;

            System.out.print("Tic-");
            isTicTurn = false;
            notifyAll();
        }
    }


    public synchronized void printTak() throws InterruptedException {
        while (count < maxCount) {

            while (isTicTurn) {
                wait();
            }
            if (count >= maxCount) break;

            System.out.println("Tak");
            count++;
            isTicTurn = true;
            notifyAll();
        }
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized int getMaxCount() {
        return maxCount;
    }
}


class TicThread implements Runnable {
    private final TicTakPrinter printer;

    public TicThread(TicTakPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {

            while (printer.getCount() < printer.getMaxCount()) {
                printer.printTic();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("TicThread interrupted.");
        }
    }
}


class TakThread implements Runnable {
    private final TicTakPrinter printer;

    public TakThread(TicTakPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            while (printer.getCount() < printer.getMaxCount()) {
                printer.printTak();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("TakThread interrupted.");
        }
    }
}