package ch13_1_프로세스와_스레드;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            String name = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(name + " ate a " + food);
        }
    }
}

class Cook implements Runnable {
    private Table table;

    Cook(Table table) {
        this.table = table;
    }

    public void run() {
        while(true) {
            int idx = (int) (Math.random()*table.dishNum());
            table.add(table.dishNames[idx]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }
}

class Table {
    String[] dishNames = {"donut", "donut", "burger"}; // 메뉴판 느낌
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCust = lock.newCondition();

    public void add(String dish) {
        lock.lock();
        String name = Thread.currentThread().getName();

        try {
            while (dishes.size() >= MAX_FOOD) {
                System.out.println(name + " is waiting.");
                try {
                    forCook.await(); // COOK 쓰레드를 기다리게 함
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            dishes.add(dish);
            forCust.signal(); // notify(); 기다리고 있는 CUST 깨우기
            System.out.println("Dishes : " + dishes.toString());
        } finally {
            lock.unlock();
        }
    }

    public void remove(String dishName) {
        lock.lock();
        String name = Thread.currentThread().getName();

        try {
            while(dishes.size()==0) {
                System.out.println(name + " is waiting.");
                try {
                    forCust.await(); // wait() CUST 쓰레드를 대기
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            while (true) {
                for(int i = 0; i<dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        forCook.signal(); // notify() 잠자고 있는 COOK 을 깨움
                        return;
                    }
                }

                try {
                    System.out.println(name + " is waiting.");
                    forCust.await(); // wait() CUST 쓰레드 대기
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        } finally {
            lock.unlock();
        }
    }

    public int dishNum() {
        return dishNames.length;
    }
}
public class ThreadWaitEx4 {
    public static void main(String[] args) throws Exception {
        Table table = new Table(); // 여러 쓰레드 공유하는 객체

        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "donut"), "CUST1").start();
        new Thread(new Customer(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}
