public class Rectangle extends Shape {
    private double width;
    private double height;

    /** Default constructor — width and height 1.0. */
    public Rectangle() {
        super();
        this.width = 1.0;
        this.height = 1.0;
    }

    /** Constructs with dimensions, default color and fill. */
    public Rectangle(double width, double height) {
        super();
        setWidth(width);
        setHeight(height);
    }

    /** Full constructor. */
    public Rectangle(String color, boolean filled, double width, double height) {
        super(color, filled);
        setWidth(width);
        setHeight(height);
    }

    // ---- getters / setters with validation ----
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        if (width <= 0)
            throw new InvalidShapeException(
                    "Rectangle width must be positive, got " + width);
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0)
            throw new InvalidShapeException(
                    "Rectangle height must be positive, got " + height);
        this.height = height;
    }

    // ---- abstract method implementations ----
    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2.0 * (width + height);
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0)
            throw new InvalidShapeException(
                    "Resize factor must be positive, got " + factor);
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public String toString() {
        return "Rectangle[width=" + width + ", height=" + height
                + ", " + super.toString() + "]";
    }
}
