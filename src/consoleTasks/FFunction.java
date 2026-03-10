package consoleTasks;

public class FFunction implements Evaluatable {

    //variables
    private double a;

    public FFunction(double a) {
        this.a = a;
    }

    public FFunction() {
        this(1.0);
    }

    @Override
    public double evalf(double x) {
        return Math.exp(-a*x*x)*Math.sin(x);
    }

    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("FFuncion class check ");
        FFunction fun = new FFunction();
        System.out.println("Enter beginning: ");
        double xBeg = in.nextDouble();
        System.out.println("Enter ending: ");
        double xEnd = in.nextDouble();
        System.out.println("Enter step: ");
        double step = in.nextDouble();

        System.out.println("Parameter a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += step) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }

        System.out.print("x: ");
        double x = in.nextDouble();
        System.out.print("aBeg: ");
        double aBeg = in.nextDouble();
        System.out.print("aEnd: ");
        double aEnd = in.nextDouble();
        System.out.print("aStep: ");
        double aStep = in.nextDouble();

        System.out.println("Змінна x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            fun.setA(a);
            System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getA(), fun.evalf(x));
        }
        in.close(); // Закриваємо сканер після використання
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}