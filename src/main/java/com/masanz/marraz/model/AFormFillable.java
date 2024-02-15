package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masanz.marraz.consts.Consts;
import javafx.scene.paint.Color;

public abstract class AFormFillable extends AForm {

    @JsonProperty("fillColor")
    protected ColorRGBO fillColor;

    public AFormFillable() {
        super();
        this.fillColor = new ColorRGBO(Consts.COLOR_DEF_FILL);
    }

    public ColorRGBO getFillColor() {
        return fillColor;
    }

    public void setFillColor(ColorRGBO fillColor) {
        this.fillColor = fillColor;
    }

    public Color getJavaFxFillColor() {
        return fillColor.getJavaFxColor();
    }

    public void setJavaFxFillColor(Color fillColor) {
        this.fillColor = new ColorRGBO(fillColor);
    }

    public abstract double getArea();

}
