package oosd.views.components;

public interface Hexagon {
    double SIZE = 40;
    double EQUAL_TRIANGLE_HEIGHT = Math.sqrt(3);
    double HALF_INCREMENT = SIZE * (EQUAL_TRIANGLE_HEIGHT / 2.0);
    double FULL_INCREMENT = SIZE * EQUAL_TRIANGLE_HEIGHT;
    double GAP = Hexagon.SIZE * 1.5;
}
