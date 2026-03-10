import myUtility.*;
import testMaterial.additionClass;
import consoleTasks.*;

import java.lang.reflect.Proxy;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        task5();
    }

    public static void task1() {
       System.out.print("Enter the full name of the class: ");
       String str = MyReflector.getClassInfo(in.nextLine());

        if ( str == null ) {
            System.out.println("CLASS NOT FOUND");
        } else {
            System.out.println(str);
        }
    }

    public static void task2() {
        App a = new App();
        String str = MyReflector.getObjectState(a);
        System.out.println(str);
    }

    public static void task3() {
        additionClass ac = new additionClass();
        Object res = null;
        try {
            res = MyReflector.callMethod(ac, "add", 5, 10, 15);
        } catch( FunctionNotFoundException e ) {
            e.printStackTrace();
        }

        System.out.println("result of invoking add: " + res);
    }

    public static void task4() {
        // 1. Тест масиву
        Integer[] arr1d = (Integer[]) MatrixBuilder.createArray(Integer.class, 3);
        arr1d[0] = 10; arr1d[1] = 20; arr1d[2] = 30;
        System.out.println("Original 1D: " + java.util.Arrays.toString(arr1d));

        // Зміна розміру (збільшення)
        Integer[] resized1d = (Integer[]) MatrixBuilder.setSize(arr1d, 5);
        System.out.println("Resized 1D (size 5): " + java.util.Arrays.toString(resized1d));

        // 2. Тест 2D масиву (Матриці)
        // Використовуємо Integer.class, щоб уникнути проблем із примітивами у вашому поточному методі
        Integer[][] matrix = (Integer[][]) MatrixBuilder.createMatrix(Integer.class, 2, 2);
        matrix[0][0] = 1; matrix[0][1] = 2;
        matrix[1][0] = 3; matrix[1][1] = 4;
        System.out.println("Original Matrix 2x2: " + MatrixBuilder.getString(matrix));

        // Спроба змінити розмір матриці
        try {
            Integer[][] bigMatrix = (Integer[][]) MatrixBuilder.setSize(matrix, 3, 3);
            System.out.println("Resized Matrix 3x3: " + MatrixBuilder.getString(bigMatrix));
        } catch (Exception e) {
            System.out.println("Error in setSize 2D: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void task5() {
        Evaluatable f = new FFunction(2);

        Evaluatable proxyF = (Evaluatable) Proxy.newProxyInstance(
            Evaluatable.class.getClassLoader(),
            new Class<?>[]{Evaluatable.class},
            new MyHandler(f) 
        );

        proxyF.evalf(0);
    }
}