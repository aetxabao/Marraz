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
@JsonPropertyOrder({ "corner", "width", "height", "strokeColor", "fillColor" })
public class Rectangle extends AFormFillable {

    @JsonProperty("corner")
    protected Point corner;
    @JsonProperty("width")
    protected double width;
    @JsonProperty("height")
    protected double height;

    public Rectangle() {
        this(new Point(), 0, 0 );
    }

    public Rectangle(Point corner, double width, double height) {
        super();
        this.corner = corner.clone();
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point p1, Point p2) {
        super();
        corner = p1.getTopLeftCorner(p2);
        width = p1.getHorizontalDistance(p2);
        height = p1.getVerticalDistance(p2);
    }

    // region getters y setters atributos
    public Point getCorner() {
        return corner;
    }

    public void setCorner(Point corner) {
        this.corner = corner;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
  // endregion

    public Point getEsquinaContraria() {
      return new Point(corner.getX() + width, corner.getY() + height);
    }

    public void paint(GraphicsContext gc) {
        gc.setFill(fillColor.getJavaFxColor());
        gc.fillRect(corner.getX(), corner.getY(), width, height);
        gc.setStroke(strokeColor.getJavaFxColor());
        gc.strokeRect(corner.getX(), corner.getY(), width, height);
        Point centro = new Point(corner.getX() + width / 2, corner.getY() + height / 2);
        centro.paint(gc);
    }

    public double getArea() {
        return width * height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(corner, width, height);
    }

    @Override
    public EForm getType() {
        return EForm.RECTANGLE;
    }

    @Override
    public String toString() {
        Point esqCon = getEsquinaContraria();
        return String.format("(%.2f,%.2f)\\(%.2f,%.2f)", corner.getX(), corner.getY(), esqCon.getX(), esqCon.getY());
    }

}
