package four;

public final class Circle {

    private double radius;
    private double pi;

    public Circle(double radius) {
        this.radius = radius;
        this.pi = 0;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0)
            this.radius = radius;
        else
            throw new IllegalArgumentException("Radius has to be bigger than zero");
    }

    public double perimeter() {
        double piApprox = approxPi();
        return 2 * piApprox * radius;
    }

    private double approxPi() {

        if(this.pi == 0) {
            double piApprox = 3;
            int sign = 1;
            for (int i = 1; i < 200_000; i++) {
                piApprox += sign * (4.0 / ((i * 2) * (i * 2 + 1) * (i * 2 + 2)));
                sign *= -1;
            }
            this.pi = piApprox;
        }

        return this.pi;
    }

    public double area() {
        double piApprox = approxPi();
        return piApprox * radius * radius;
    }

    public double arcLength(double angleInDegrees) {
        double piApprox = approxPi();
        double totalLength = 2 * piApprox * radius;
        return angleInDegrees / 360 * totalLength;
    }

    // Type: different algos doing the same thing

    // added an exception in the method setting the radius such that we get notified if we introduce and error. Removed
    // the duplicate approximation of Pi into one private method. Furthermore, I added a private variable for pi, such
    // that we only calculate Pi once and not upon every method call.
}
