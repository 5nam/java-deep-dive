package ch13_1_프로세스와_스레드;

import javax.swing.*;

/**
 * 스레드 2개에서 돌리기(main, ThreadEx7_1), 멀티쓰레드
 */
public class ThreadEx7 {
    public static void main(String[] args) throws Exception {
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + " 입니다.");
    }
}

class ThreadEx7_1 extends Thread {
    public void run() {
        for(int i = 10; i>0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            } catch (Exception e) {}
        }
    }
}
