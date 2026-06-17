public abstract class Shape {
    protected String color;
    protected boolean filled;

    /** Default constructor — white, not filled. */
    public Shape() {
        this.color = "white";
        this.filled = false;
    }

    /** Parameterised constructor. */
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // ---- abstract methods ----
    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract void resize(double factor);

    // ---- concrete methods ----
    public String getColor() {
        return color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setColor(String c) {
        this.color = c;
    }

    public void setFilled(boolean f) {
        this.filled = f;
    }

    @Override
    public String toString() {
        return "Shape[color=" + color + ", filled=" + filled + "]";
    }

    public class getArea {
    }
}
