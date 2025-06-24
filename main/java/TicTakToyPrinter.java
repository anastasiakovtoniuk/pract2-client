class TicTakToyPrinter {
    private int count = 0;
    private final int maxCount;

    private volatile Turn currentTurn = Turn.TIC;

    enum Turn {
        TIC, TAK, TOY
    }

    public TicTakToyPrinter(int maxCount) {
        this.maxCount = maxCount;
    }


    public synchronized void printTic() throws InterruptedException {
        while (count < maxCount) {
            while (currentTurn != Turn.TIC) {
                wait();
            }
            if (count >= maxCount) break;

            System.out.print("Tic-");
            currentTurn = Turn.TAK;
            notifyAll();
        }
    }


    public synchronized void printTak() throws InterruptedException {
        while (count < maxCount) {
            while (currentTurn != Turn.TAK) {
                wait();
            }
            if (count >= maxCount) break;

            System.out.print("Tak-");
            currentTurn = Turn.TOY;
            notifyAll();
        }
    }


    public synchronized void printToy() throws InterruptedException {
        while (count < maxCount) {
            while (currentTurn != Turn.TOY) {
                wait();
            }
            if (count >= maxCount) break;

            System.out.println("Toy");
            count++;
            currentTurn = Turn.TIC;
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


class TicThreadP2 implements Runnable {
    private final TicTakToyPrinter printer;

    public TicThreadP2(TicTakToyPrinter printer) {
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
            System.out.println("TicThreadP2 interrupted.");
        }
    }
}


class TakThreadP2 implements Runnable {
    private final TicTakToyPrinter printer;

    public TakThreadP2(TicTakToyPrinter printer) {
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
            System.out.println("TakThreadP2 interrupted.");
        }
    }
}


class ToyThreadP2 implements Runnable {
    private final TicTakToyPrinter printer;

    public ToyThreadP2(TicTakToyPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            while (printer.getCount() < printer.getMaxCount()) {
                printer.printToy();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("ToyThreadP2 interrupted.");
        }
    }
}