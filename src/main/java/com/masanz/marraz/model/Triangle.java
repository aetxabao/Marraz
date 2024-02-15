package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.masanz.marraz.enums.EForm;
import javafx.scene.canvas.GraphicsContext;
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonPropertyOrder({ "p1", "p2", "p3", "strokeColor", "fillColor" })
public class Triangle extends AFormFillable {

    @JsonProperty("p1")
    private Point point1;
    @JsonProperty("p2")
    private Point point2;
    @JsonProperty("p3")
    private Point point3;

    public Triangle() {
        this(new Point(), new Point(), new Point());
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(fillColor.getJavaFxColor());
        gc.fillPolygon(new double[]{point1.getX(), point2.getX(), point3.getX()},
                new double[]{point1.getY(), point2.getY(), point3.getY()}, 3);
        gc.setStroke(strokeColor.getJavaFxColor());
        gc.strokePolygon(new double[]{point1.getX(), point2.getX(), point3.getX()},
                new double[]{point1.getY(), point2.getY(), point3.getY()}, 3);
        Point center = new Point((point1.getX() + point2.getX() + point3.getX()) / 3,
                (point1.getY() + point2.getY() + point3.getY()) / 3);
        center.paint(gc);
    }

    @Override
    public double getArea() {
        return Math.abs((point1.getX() * (point2.getY() - point3.getY()) + point2.getX() * (point3.getY() - point1.getY()) + point3.getX() * (point1.getY() - point2.getY())) / 2);
    }

    @Override
    public EForm getType() {
        return EForm.TRIANGLE;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)-(%.2f,%.2f)-(%.2f,%.2f)", point1.getX(), point1.getY(), point2.getX(), point2.getY(), point3.getX(), point3.getY());
    }

}
