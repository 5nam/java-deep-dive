package ch7_6_추상클래스_abstract_class;

abstract public class Player {
    boolean pause;
    int currentPos;

    Player() {
        pause = false;
        currentPos = 0;
    }

    /**
     * 지정된 위치(pos)에서 재생을 시작하는 기능이 수행하도록 작성되어야 함
     */
    abstract void play(int pos); // 추상 메서드
    /**
     * 재생을 즉시 멈추는 기능을 수행하도록 작성되어야 한다.
     */
    abstract void stop();

    void play() {
        play(currentPos); // 추상 메서드 사용 가능
    }

    // 노래 재생버튼 생각하면 됨
    void pause() {
        if(pause) { // pause가 true일 때 (정지상태)에서 pause가 호출되면,
            pause = false; // pause의 상태를 false로 바꾸고,
            play(currentPos); // 현재의 위치에서 play를 한다.
        } else { // pause가 false 일 때 (play 상태)에서 pause가 호출되면,
            pause = true; // pause 의 상태를 true 로 바꾸고,
            stop(); // play 를 멈춘다.
        }
    }
}
