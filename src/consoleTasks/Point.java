package consoleTasks;

public abstract class Point {
    private double[] coords = null;

    public Point(int num) {
        this.coords = new double[num];
    }

    public void setCoord(int num, double x) {
        coords[num-1] = x;
    }

    public double getCoord(int num) {
        return coords[num-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < coords.length; i++) {
            sb.append(coords[i]);
            if (i < coords.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }


}