package ch13_1_프로세스와_스레드;

public class ThreadEx20 {
    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;

        for(int i = 0; i<20; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;

            // 필요한 메모리가 사용할 수 있는 양보다 크거나 전체 메모리 60%이상을 사용할 경우 gc 깨운다.
            if(gc.freeMemory() < requiredMemory
                || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt();
                try {
                    /**
                     * join 을 사용해서 쓰레드 gc가 작업할 시간을 어느 정도 주고 main 쓰레드가 기다리도록 해서 사용할 수 있는 메모리가 확보된 다음 작업을 계속하는 것이 필요
                     */
                    gc.join(100); // 현재 실행중인 쓰레드가 쓰레드 gc 의 작업을 100 millis 만큼 기다린다.
                } catch (InterruptedException e) {}
            }

            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory : " + gc.usedMemory);
        }
    }
}

class ThreadEx20_1 extends Thread {
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    public void run() {
        while(true) {
            try {
                Thread.sleep(10 * 1000); // 10초 기다린다.
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt()");
            }

            gc(); // garbage collection 수행
            System.out.println("Garbage Collected. Free Memory : " + freeMemory());
        }
    }

    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }

    public int totalMemory() {
        return MAX_MEMORY;
    }

    public int freeMemory() {
        return MAX_MEMORY - usedMemory;
    }
}
