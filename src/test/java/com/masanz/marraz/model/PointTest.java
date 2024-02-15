package com.masanz.marraz.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testGetMiddlePoint1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 1);
        Point r = new Point(3, 3);
        assert(r.equals(p1.getMiddlePoint(p2)));
    }

    @Test
    void testGetMiddlePoint2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        Point r = new Point(3, 3);
        assert(r.equals(p1.getMiddlePoint(p2)));
    }

    @Test
    void testGetMiddlePoint3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);
        Point r = new Point(1, 3);
        assert(r.equals(p1.getMiddlePoint(p2)));
    }

    @Test
    void testGetMiddlePoint4() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(5, 5);
        Point r = new Point(3, 5);
        assert(r.equals(p1.getMiddlePoint(p2)));
    }

    @Test
    void testGetDistance1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 1);
        double r = Math.sqrt(4*4 + 4*4);
        assert(Double.compare(r, p1.getDistance(p2)) == 0);
    }

    @Test
    void testGetDistance2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        double r = Math.sqrt(4*4 + 4*4);
        assert(Double.compare(r, p1.getDistance(p2)) == 0);
    }

    @Test
    void testGetDistance3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);
        double r = 4;
        assert(Double.compare(r, p1.getDistance(p2)) == 0);
    }

    @Test
    void testGetDistance4() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(5, 5);
        double r = 4;
        assert(Double.compare(r, p1.getDistance(p2)) == 0);
    }

    @Test
    void testGetHorizontalDistance1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 1);
        double r = 4;
        assert(Double.compare(r, p1.getHorizontalDistance(p2)) == 0);
    }

    @Test
    void testGetHorizontalDistance2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        double r = 4;
        assert(Double.compare(r, p1.getHorizontalDistance(p2)) == 0);
    }

    @Test
    void testGetHorizontalDistance3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);
        double r = 0;
        assert(Double.compare(r, p1.getHorizontalDistance(p2)) == 0);
    }

    @Test
    void testGetHorizontalDistance4() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(5, 5);
        double r = 4;
        assert(Double.compare(r, p1.getHorizontalDistance(p2)) == 0);
    }

    @Test
    void testGetVerticalDistance1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 1);
        double r = 4;
        assert(Double.compare(r, p1.getVerticalDistance(p2)) == 0);
    }

    @Test
    void testGetVerticalDistance2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        double r = 4;
        assert(Double.compare(r, p1.getVerticalDistance(p2)) == 0);
    }

    @Test
    void testGetVerticalDistance3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);
        double r = 4;
        assert(Double.compare(r, p1.getVerticalDistance(p2)) == 0);
    }

    @Test
    void testGetVerticalDistance4() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(5, 5);
        double r = 0;
        assert(Double.compare(r, p1.getVerticalDistance(p2)) == 0);
    }

    @Test
    void testGetTopLeftCorner1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 1);
        Point r = new Point(1, 1);
        assert(r.equals(p1.getTopLeftCorner(p2)));
    }

    @Test
    void testGetTopLeftCorner2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        Point r = new Point(1, 1);
        assert(r.equals(p1.getTopLeftCorner(p2)));
    }

    @Test
    void testGetTopLeftCorner3() {
        Point p1 = new Point(5, 1);
        Point p2 = new Point(1, 5);
        Point r = new Point(1, 1);
        assert(r.equals(p1.getTopLeftCorner(p2)));
    }

    @Test
    void testGetTopLeftCorner4() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(5, 1);
        Point r = new Point(1, 1);
        assert(r.equals(p1.getTopLeftCorner(p2)));
    }

    @Test
    void testClone() {
        Point p1 = new Point(1, 5);
        Point r = new Point(1, 5);
        assert(r != p1);
        assert(r.equals(p1));
    }

    @Test
    void testToString1() {
        Point p1 = new Point(1, 5);
        String r = "(1.00,5.00)";
        assert(r.equals(p1.toString()));
    }

    @Test
    void testToString2() {
        Point p1 = new Point(1.234, 5.567);
        String r = "(1.23,5.57)";
        assert(r.equals(p1.toString()));
    }

}