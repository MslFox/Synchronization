package Netology.Multithread_Functional.Multithread.WaitNotifyLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop(SellerType.WAIT_NOTIFY);
        shopping(shop, 3);
        Thread.sleep(8000);
        System.out.println( "\n****************************************  ReentrantLock(true)  ****************************************\n");
        Shop shopReent = new Shop(SellerType.REENT_RANT_LOCK);
        shopping(shopReent, 3);
    }

    static void shopping(Shop shop, int buyersValue) {
        for (int i = 1; i <= buyersValue; i++) {
            new Thread(null, shop::sell, "Покупатель " + i).start();
        }
        new Thread(null, shop::accept, "Toyota").start();
    }
}

