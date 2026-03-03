import myUtility.*;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        task2();
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
}