package testMaterial;

public class TestClass {
    
    private double a;
    
    public TestClass(double a) {
        this.a = a;
    }

    public double calculate(double x) {
        return Math.exp(-Math.abs(a)*x)*Math.sin(x);
    }
}
