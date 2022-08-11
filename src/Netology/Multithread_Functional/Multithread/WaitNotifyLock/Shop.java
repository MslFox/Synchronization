package Netology.Multithread_Functional.Multithread.WaitNotifyLock;

import java.util.LinkedList;
import java.util.Random;

public class Shop {
    private final LinkedList<Car> carList = new LinkedList<>();
    private final int MAX_CAR_VALUE = 10;
    private final int MINIMAL_TIME_OPERATION = 100;
    private final int CAR_INDUSTRY_TIME = 1000;
    private final int BUYER_WISHES_TIME = 500;
    private int carCount;
    private int buyerCount;
    Random random = new Random();
    private Seller seller;

    public Shop(SellerType sellerType) {
        switch (sellerType) {
            case WAIT_NOTIFY -> seller = new SellerWaitNotify(this);
            case REENT_RANT_LOCK -> seller = new SellerReentRant(this);
        }
    }

    public LinkedList<Car> getCarList() {
        return carList;
    }

    public void sell() {
        while (buyerCount < MAX_CAR_VALUE) {
            buyerCount++;
            seller.sell();
            try {
                Thread.sleep(random.nextInt(MINIMAL_TIME_OPERATION, BUYER_WISHES_TIME));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void accept() {
        while (carCount < MAX_CAR_VALUE) {
            carCount++;
            try {
                Thread.sleep(random.nextInt(MINIMAL_TIME_OPERATION, CAR_INDUSTRY_TIME));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            seller.accept();
        }
    }
}
