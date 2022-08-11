package Netology.Multithread_Functional.Multithread.WaitNotifyLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SellerReentRant extends Seller {
    private final Shop shop;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    public SellerReentRant(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void sell() {
        lock.lock();
        try {
                System.out.println(GREEN + Thread.currentThread().getName() + " зашел в автосалон");
                while (shop.getCarList().isEmpty()) {
                    System.out.println(RED + Thread.currentThread().getName() + ". Машин нет в наличии");
                    condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            shop.getCarList().removeFirst();
            System.out.println(BLUE + Thread.currentThread().getName() + " ушел домой с машиной");
            lock.unlock();
        }
    }

    @Override
    public void accept() {
        lock.lock();
                shop.getCarList().add(new Car());
                System.out.println(YELLOW + "В продажу поступила машина " + Thread.currentThread().getName());
                condition.signal();
        lock.unlock();
    }
}
