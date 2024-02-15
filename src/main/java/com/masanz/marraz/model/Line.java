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
@JsonPropertyOrder({ "p1", "p2", "strokeColor" })
public class Line extends AForm {

    @JsonProperty("p1")
    private Point p1;
    @JsonProperty("p2")
    private Point p2;

    public Line() {
        super();
        p1 = new Point();
        p2 = new Point();
    }

    public Line(Point p1, Point p2) {
        super();
        this.p1 = p1.clone();
        this.p2 = p2.clone();
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void paint(GraphicsContext gc) {
        gc.setStroke(strokeColor.getJavaFxColor());
        gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        Point centro = p1.getMiddlePoint(p2);
        centro.paint(gc);
    }

    @Override
    public Line clone() {
        return new Line(p1, p2);
    }

    @Override
    public EForm getType() {
        return EForm.LINE;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)_(%.2f,%.2f)", p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}
