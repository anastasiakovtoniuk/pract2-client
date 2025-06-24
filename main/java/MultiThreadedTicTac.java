public class MultiThreadedTicTac {

    public static void main(String[] args) {


        System.out.println("Завдання 1: Виведення \"Tic-Tak\" 5 разів");
        TicTakPrinter printer1 = new TicTakPrinter(5); // 5 разів

        Thread ticThread1 = new Thread(new TicThread(printer1));
        Thread takThread1 = new Thread(new TakThread(printer1));

        ticThread1.start();
        takThread1.start();

        try {
            ticThread1.join();
            takThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Головний потік перервано під час Завдання 1.");
        }


        System.out.println("Завдання 2: Виведення \"Tic-Tak-Toy\" 5 разів");
        TicTakToyPrinter printer2 = new TicTakToyPrinter(5); // 5 разів

        Thread ticThread2 = new Thread(new TicThreadP2(printer2));
        Thread takThread2 = new Thread(new TakThreadP2(printer2));
        Thread toyThread2 = new Thread(new ToyThreadP2(printer2));

        ticThread2.start();
        takThread2.start();
        toyThread2.start();

        try {
            ticThread2.join();
            takThread2.join();
            toyThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Головний потік перервано під час Завдання 2.");
        }
    }
}