package consoleTasks;

import java.io.*;
import java.util.Scanner;

public class DerivativeApplication {

    public static void main(String[] args) throws IOException {
        Evaluatable funcs[] = new Evaluatable[3];
        FFunction func = new FFunction();
        FFunction func1 = new FFunction(1.5);

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of points: ");
        int num = in.nextInt();

        funcs[0] = new ListInterpolation();
        funcs[1] = new FileListInterpolation();
        funcs[2] = new ListInterpolation();

        for ( int i = 0; i < num ; i++) {
            double x = 1.0 + (7.0 - 1.0)*Math.random();
            ((ListInterpolation)(funcs[0])).addPoint(new Point2D(x, func.evalf(x)));
            ((ListInterpolation)(funcs[2])).addPoint(new Point2D(x, func1.evalf(x)));
        }

        ((ListInterpolation)(funcs[0])).sort();
        ((ListInterpolation)(funcs[2])).sort();

        try {
            ((FileListInterpolation)funcs[1]).readFromFile("TblFunc.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String fileName;

        for (Evaluatable f: funcs) {
            System.out.println("функція: " + f.getClass().getSimpleName());
            fileName = f.getClass().getSimpleName() + ".dat";
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (double x = 1.5; x <= 6.5; x += 0.05) {
                System.out.println(("x: " + x + "\tf: " + f.evalf(x) + "\tf': " +
                 NumMethods.der(x, 1.0e-4, f)));
                out.printf("%16.6e%16.6e%16.6e\n", x, f.evalf(x), 
                NumMethods.der(x, 1.0e-4, f));
            }

            System.out.println("\n");
            out.close();
        }

        in.close();
    }
}