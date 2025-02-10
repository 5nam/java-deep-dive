package ch13_1_프로세스와_스레드;

import javax.swing.*;

public class ThreadEx13 {
    public static void main(String[] args) throws Exception {
        ThreadEx13_1 th1 = new ThreadEx13_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이 입력하세요.");
        System.out.println("입력하신 값은 " + input + " 입니다.");
        th1.interrupt(); // interrupted 상태 true 로 변경
        System.out.println("isInterrupted() : " + th1.isInterrupted());
    }
}

class ThreadEx13_1 extends Thread {
    public void run() {
        int i = 10;

        while(i!=0 && !isInterrupted()) {
            System.out.println(i--);
            for(long x=0; x<2500000000L; x++); // 시간 지연(sleep 같은 역할)
        }

        System.out.println("카운트가 종료되었습니다.");
    }
}
