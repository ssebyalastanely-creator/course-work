public class ShapeDemo {
    public static void printAreas(Shape[] shapes) {
        for (Shape s : shapes) {
            System.out.println(s.getArea());
        }
    }

    public static Shape largest(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) {
            return null;
        }

        Shape largestShape = shapes[0];
        double largestArea = shapes[0].getArea();

        for (int i = 1; i < shapes.length; i++) {
            double area = shapes[i].getArea();
            if (area > largestArea) {
                largestArea = area;
                largestShape = shapes[i];
            }
        }

        System.out.println("Largest area: " + largestArea);
        return largestShape;
    }
}
