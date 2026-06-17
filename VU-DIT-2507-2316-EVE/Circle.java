public class Circle extends Shape.getArea {
    private double radius;

    /** Default constructor — radius 1.0. */
    public Circle() {
        super();
        this.radius = 1.0;
    }

    /** Constructs a circle with given radius, default color and fill. */
    public Circle(double radius) {
        super();
        setRadius(radius);
    }

    /** Full constructor. */
    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        setRadius(radius);
    }

    // ---- getters / setters with validation ----
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0)
            throw new InvalidShapeException(
                    "Circle radius must be positive, got " + radius);
        this.radius = radius;
    }

}
