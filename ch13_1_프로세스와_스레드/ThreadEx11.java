package ch13_1_프로세스와_스레드;

import java.util.Iterator;
import java.util.Map;

public class ThreadEx11 {
    public static void main(String[] args) throws Exception {
//        ThreadGroup grp1 = new ThreadGroup("grp1");
//        ThreadGroup grp2 = new ThreadGroup("grp2");

        ThreadEx11_1 t1 = new ThreadEx11_1("Thread1");
        ThreadEx11_2 t2 = new ThreadEx11_2("Thread2");
        t1.start();
        t2.start();

//        System.out.println("작업 끝");
    }
}

class ThreadEx11_1 extends Thread {
    ThreadEx11_1(String name) {
        super(name); // 스레드 이름 설정
    }

    public void run() {
        try {
            sleep(6 * 1000); // 5초 기다림
        } catch (InterruptedException e) {}
    }
}

class ThreadEx11_2 extends Thread {
    ThreadEx11_2(String name) {
        super(name);
    }

    public void run() {
        Map map = getAllStackTraces();
        Iterator it = map.keySet().iterator();

        int x = 0;
        while(it.hasNext()) {
            Object obj = it.next();
            Thread t = (Thread) obj;

            StackTraceElement[] ste = (StackTraceElement[]) (map.get(obj));

            System.out.println("[" + ++x + "] name : " + t.getName()
                        + ", group : " + t.getThreadGroup().getName()
                        + ", daemon : " + t.isDaemon());

            for(int i = 0; i<ste.length; i++) {
                System.out.println(ste[i]);
            }

            System.out.println();
        }
    }
}
