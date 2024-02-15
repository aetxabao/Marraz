package com.masanz.marraz.model;

import com.masanz.marraz.enums.EForm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void testCircle1() {
        Circle c1 = new Circle();
        double r = 0;
        assert(Double.compare(r, c1.getRadius()) == 0);
        Point p = new Point();
        assert (p != c1.getCenter());
        assert (p.equals(c1.getCenter()));
    }

    @Test
    void testCircle2() {
        Point p1 = new Point(10, 20);
        double r1 = 5;
        Circle c1 = new Circle(p1, r1);
        double r = 5;
        assert(Double.compare(r, c1.getRadius()) == 0);
        Point p = new Point(10, 20);
        assert (p != c1.getCenter());
        assert (p.equals(c1.getCenter()));
    }

    @Test
    void testCircle3() {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(20, 20);
        Circle c1 = new Circle(p1, p2);
        Point p = new Point(15, 15);
        double r = p.getDistance(p2);
        assert(Double.compare(r, c1.getRadius()) == 0);
        assert (p != c1.getCenter());
        assert (p.equals(c1.getCenter()));
    }

    @Test
    void testGetArea() {
        Point p1 = new Point(10, 20);
        double r1 = 5;
        Circle c1 = new Circle(p1, r1);
        double r = Math.PI * r1 * r1;
        assert(Double.compare(r, c1.getArea()) == 0);
    }

    @Test
    void testClone() {
        Point p1 = new Point(10, 20);
        double r1 = 5;
        Circle c1 = new Circle(p1, r1);
        Circle r = c1.clone();
        assert (r != c1);
        assert (r.equals(c1));
    }

    @Test
    void getType() {
        Circle c1 = new Circle();
        EForm r = EForm.valueOf("CIRCLE");
        assertEquals(r, c1.getType());
        assertEquals("CIRCLE", c1.getType().toString());
    }

    @Test
    void testToString1() {
        Circle c1 = new Circle();
        String r = "(0.00,0.00)_r=0.00";
        assertEquals(r, c1.toString());
    }

    @Test
    void testToString2() {
        Point p1 = new Point(10, 20);
        double r1 = 5;
        Circle c1 = new Circle(p1, r1);
        String r = "(10.00,20.00)_r=5.00";
        assertEquals(r, c1.toString());
    }

    @Test
    void testToString3() {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(20, 20);
        Circle c1 = new Circle(p1, p2);
        String r = "(15.00,15.00)_r=7.07";
        assertEquals(r, c1.toString());
    }

}