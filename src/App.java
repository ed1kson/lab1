import myUtility.*;
import testMaterial.additionClass;

import java.util.Scanner;

import javax.naming.NameNotFoundException;

public class App {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        task3();
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
}