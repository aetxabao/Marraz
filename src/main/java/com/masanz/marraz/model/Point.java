package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;

import static com.masanz.marraz.consts.Consts.*;

public class Point {

    @JsonProperty("x")
    private double x;
    @JsonProperty("y")
    private double y;

    public Point() {
        this(0,0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // region getters y setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // endregion

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point getMiddlePoint(Point otro) {
        return new Point((this.x+otro.x)/2, (this.y+otro.y)/2);
    }

    public double getDistance(Point otro) {
        return Math.sqrt((this.x - otro.x)*(this.x - otro.x) + (this.y - otro.y)*(this.y - otro.y));
    }

    public double getHorizontalDistance(Point otro) {
        return Math.abs(this.x - otro.x);
    }

    public double getVerticalDistance(Point otro) {
        return Math.abs(this.y - otro.y);
    }

    public Point getTopLeftCorner(Point otro) {
        return new Point(Math.min(this.x, otro.x), Math.min(this.y, otro.y));
    }

    public void paint(GraphicsContext gc) {
        gc.setStroke(COLOR_CLICK_POINT);
        gc.strokeLine(x - POINT_SIZE / 2.0, y, x + POINT_SIZE / 2.0, y);
        gc.strokeLine(x, y - POINT_SIZE / 2.0, x, y + POINT_SIZE / 2.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Point clone() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", x, y);
    }

}
