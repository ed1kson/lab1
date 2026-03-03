import myUtility.*;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println(MyReflector.getClassInfo( "java.lang.String" ));
        System.out.println(MyReflector.getClassInfo( "suka blyat" ));
    }
}