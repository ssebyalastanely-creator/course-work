public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    /** Default constructor — equilateral triangle with side 1.0. */
    public Triangle() {
        super();
        setSides(1.0, 1.0, 1.0);
    }

    /** Constructs with three sides, default colour and fill. */
    public Triangle(double sideA, double sideB, double sideC) {
        super();
        setSides(sideA, sideB, sideC);
    }

    /** Full constructor. */
    public Triangle(String color, boolean filled,
            double sideA, double sideB, double sideC) {
        super(color, filled);
        setSides(sideA, sideB, sideC);
    }

    // ---- getters ----
    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    /**
     * Validates and sets all three sides atomically.
     * Throws InvalidShapeException if any side ≤ 0 or
     * if the sides violate the triangle inequality
     * (sum of any two ≤ the third).
     */
    private void setSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new InvalidShapeException(
                    "All sides must be positive, got (" + a + ", " + b + ", " + c + ")");

        // Triangle inequality: each side must be less than sum of the other two
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new InvalidShapeException(
                    "Sides (" + a + ", " + b + ", " + c
                            + ") violate the triangle inequality");

        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    // ---- abstract method implementations ----
    @Override
    public double getArea() {
        // Heron's formula
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0)
            throw new InvalidShapeException(
                    "Resize factor must be positive, got " + factor);
        // Since scaling preserves proportions, the scaled sides
        // will still satisfy the triangle inequality.
        this.sideA *= factor;
        this.sideB *= factor;
        this.sideC *= factor;
    }

    @Override
    public String toString() {
        return "Triangle[sides=" + sideA + ", " + sideB + ", " + sideC
            + ", " + super.toString() + "]";
    }
