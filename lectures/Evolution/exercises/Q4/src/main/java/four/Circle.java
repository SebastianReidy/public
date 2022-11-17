package four;


/**
 * instance of duplicated function
 */
public final class Circle {

    private double radius;

    private double pi = approxPi();

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0)
            this.radius = radius;
    }

    public double perimeter() {
        return 2 * pi * radius;
    }

    public double area() {
        return pi * radius * radius;
    }

    public double arcLength(double angleInDegrees) {
        double totalLength = 2 * pi * radius;
        return angleInDegrees / 360 * totalLength;
    }

    private double approxPi(){
        double piApprox = 3;
        int sign = 1;
        for (int i = 1; i < 200_000; i++) {
            piApprox += sign * (4.0 / ((i * 2) * (i * 2 + 1) * (i * 2 + 2)));
            sign *= -1;
        }
        return piApprox;
    }
}
