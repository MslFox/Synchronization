package Netology.Multithread_Functional.Multithread.WaitNotifyLock;

public class SellerWaitNotify extends Seller {
    private final Shop shop;

    public SellerWaitNotify(Shop shop) {
        this.shop = shop;
    }

    @Override
    public synchronized void sell() {
        System.out.println(GREEN + Thread.currentThread().getName() + " зашел в автосалон");
        try {
            while (shop.getCarList().isEmpty()) {
                System.out.println(RED + Thread.currentThread().getName() + ". Машин нет в наличии");
                wait();
            }
            shop.getCarList().removeFirst();
            System.out.println(BLUE + Thread.currentThread().getName() + " ушел домой с машиной");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public synchronized void accept() {
        shop.getCarList().add(new Car());
        System.out.println(YELLOW + "В продажу поступила машина " + Thread.currentThread().getName());
        notify();
    }

}
