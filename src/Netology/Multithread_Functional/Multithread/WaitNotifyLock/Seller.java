package Netology.Multithread_Functional.Multithread.WaitNotifyLock;

public abstract class Seller {
    String RED = "\033[0;31m";
    String GREEN = "\033[0;32m";
    String YELLOW = "\033[0;33m";
    String BLUE = "\033[0;34m";

    abstract void sell();
    abstract void accept();

}
