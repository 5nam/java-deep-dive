package ch13_1_프로세스와_스레드;

public class ThreadEx10 implements Runnable {
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadEx10());
        t.setDaemon(true); // 이 부분이 없으면 종료되지 않음(무한루프)
        t.start();

        for(int i = 1; i<=10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(i);

            if(i==5) {
                autoSave = true;
            }
        }

        System.out.println("프로그램을 종료합니다.");
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(3 * 1000); // 3초마다
            } catch(InterruptedException e) {}

            // autoSave 값이 true 면 autoSave() 호출
            if(autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
