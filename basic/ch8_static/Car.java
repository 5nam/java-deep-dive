package basic.ch8_static;

public class Car {
    private static int totalCar;
    private String name;

    Car(String name) {
        this.name = name;
        System.out.println("차량 구입, 이름 : " + name);
        totalCar++;
    }

    public static void showTotalCars() {
        System.out.println("구매한 차량 수 : " + totalCar);
    }
}
