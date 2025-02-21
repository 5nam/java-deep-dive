package basic.ch8_static;

import java.util.Arrays;

public class MathArrayUtils {
    private MathArrayUtils() {} // 인스턴스 생성 못하도록 막음

    public static int sum(int[] array) {
        int result = 0;
        for (int num: array) {
            result += num;
        }

        return result;
    }

    public static double average(int[] array) {
        return (double) sum(array)/array.length;
    }

    public static int min(int[] array) {
        int min = array[0];
        for (int i = 1; i<array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i<array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
