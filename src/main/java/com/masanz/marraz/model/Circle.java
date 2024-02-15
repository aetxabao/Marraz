package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.masanz.marraz.enums.EForm;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;


@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonPropertyOrder({ "center", "radius", "strokeColor", "fillColor" })
public class Circle extends AFormFillable {

    @JsonProperty("center")
    private Point center;
    @JsonProperty("radius")
    private double radius;

    public Circle() {
        this(new Point(), 0 );
    }

    public Circle(Point center, double radius) {
        super();
        this.center = center.clone();
        this.radius = radius;
    }

    public Circle(Point p1, Point p2) {
        center = p1.getMiddlePoint(p2);
        radius = center.getDistance(p1);
    }

    // region getters y setters atributos
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    // endregion

    public void paint(GraphicsContext gc) {
        Point esquina = new Point(center.getX() - radius, center.getY() - radius);
        gc.setFill(fillColor.getJavaFxColor());
        gc.fillOval(esquina.getX(), esquina.getY(), 2 * radius, 2 * radius);
        gc.setStroke(strokeColor.getJavaFxColor());
        gc.strokeOval(esquina.getX(), esquina.getY(), 2 * radius, 2 * radius);
        center.paint(gc);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public EForm getType() {
        return EForm.CIRCLE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return Double.compare(this.radius, other.radius) == 0 && Objects.equals(this.center, other.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public Circle clone() {
        return new Circle(center, radius);
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)_r=%.2f", center.getX(), center.getY(), radius);
    }

}
