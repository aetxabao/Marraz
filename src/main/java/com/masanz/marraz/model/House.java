package com.masanz.marraz.model;

import com.masanz.marraz.consts.Consts;
import com.masanz.marraz.enums.EForm;
import javafx.scene.canvas.GraphicsContext;

public class House extends Rectangle {

    public House() {
        this(new Point(), new Point(), new Point());
    }

    public House(Point punto1, Point punto2, Point punto3) {
        super();
        if (punto1.getX() <= punto2.getX() && punto1.getY() >= punto3.getY()) {
            corner = new Point(punto1.getX(), punto1.getY() - punto1.getVerticalDistance(punto3) * Consts.HOUSE_RECTANGLE_PORTION);
            width = punto1.getHorizontalDistance(punto2);
            height = punto1.getVerticalDistance(punto3);
        } else if (punto1.getX() > punto2.getX() && punto1.getY() >= punto3.getY()) {
            corner = new Point(punto2.getX(), punto1.getY() - punto1.getVerticalDistance(punto3) * Consts.HOUSE_RECTANGLE_PORTION);
            width = punto1.getHorizontalDistance(punto2);
            height = punto1.getVerticalDistance(punto3);
        } else if (punto1.getX() <= punto2.getX() && punto1.getY() < punto3.getY()) {
            corner = punto1.clone();
            width = punto1.getHorizontalDistance(punto2);
            height = -1 * punto1.getVerticalDistance(punto3);
        } else if (punto1.getX() > punto2.getX() && punto1.getY() < punto3.getY()) {
            corner = new Point(punto2.getX(), punto1.getY());
            width = punto1.getHorizontalDistance(punto2);
            height = -1 * punto1.getVerticalDistance(punto3);
        }
    }

    @Override
    public void paint(GraphicsContext gc) {
        Rectangle rectangulo = new Rectangle(corner, width, Math.abs(height) * Consts.HOUSE_RECTANGLE_PORTION);
        rectangulo.setFillColor(fillColor);
        rectangulo.setStrokeColor(strokeColor);
        rectangulo.paint(gc);
        Triangle triangulo;
        if (height >= 0 ) {
            triangulo = new Triangle(
                    new Point(corner.getX() , corner.getY()),
                    new Point(corner.getX() + width / 2, corner.getY() - height * (1 - Consts.HOUSE_RECTANGLE_PORTION)),
                    new Point(corner.getX() + width, corner.getY()));
        }else {
            triangulo = new Triangle(
                    new Point(corner.getX() , corner.getY() - height * Consts.HOUSE_RECTANGLE_PORTION),
                    new Point(corner.getX() + width / 2, corner.getY() - height),
                    new Point(corner.getX() + width, corner.getY() - height * Consts.HOUSE_RECTANGLE_PORTION));
        }
        triangulo.setFillColor(fillColor);
        triangulo.setStrokeColor(strokeColor);
        triangulo.paint(gc);
    }

    @Override
    public EForm getType() {
        return EForm.HOUSE;
    }

    @Override
    public double getArea() {
        double area = 0;
        AFormFillable formR = new Rectangle(corner, width, Math.abs(height) * Consts.HOUSE_RECTANGLE_PORTION);
        area += formR.getArea();
        if (height >= 0) {
            AFormFillable formT = new Triangle(
                    new Point(corner.getX() , corner.getY()),
                    new Point(corner.getX() + width / 2, corner.getY() - height * (1 - Consts.HOUSE_RECTANGLE_PORTION)),
                    new Point(corner.getX() + width, corner.getY()));
            area += formT.getArea();
        }else {
            AFormFillable formT = new Triangle(
                    new Point(corner.getX() , corner.getY() - height * Consts.HOUSE_RECTANGLE_PORTION),
                    new Point(corner.getX() + width / 2, corner.getY() - height),
                    new Point(corner.getX() + width, corner.getY() - height * Consts.HOUSE_RECTANGLE_PORTION));
            area += formT.getArea();
        }
        return area;
    }

}
