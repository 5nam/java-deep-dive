package ch7_6_추상클래스_abstract_class;

public class CDPlayer extends Player {
    @Override
    void play(int pos) {
        /* 조상의 추상메서드 구현. 내용 생략 */
    }

    @Override
    void stop() {
        /* 조상의 추상메서드 구현. 내용 생략 */
    }

    // CDPlayer클래스에 추가로 정의된 멤버
    int currentTrack; // 현재 재생 중인 트랙

    void nextTrack() {
        currentTrack++;
    }

    void preTrack() {
        if(currentTrack > 1) {
            currentTrack--;
        }
    }
}
