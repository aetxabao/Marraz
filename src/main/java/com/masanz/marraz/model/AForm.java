package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masanz.marraz.consts.Consts;
import com.masanz.marraz.enums.EForm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AForm {
    @JsonProperty("strokeColor")
    protected ColorRGBO strokeColor;

    public AForm() {
        this.strokeColor = new ColorRGBO(Consts.COLOR_DEF_STROKE);
    }

    public ColorRGBO getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(ColorRGBO strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getJavaFxStrokeColor() {
        return strokeColor.getJavaFxColor();
    }

    public void setJavaFxStrokeColor(Color color) {
        this.strokeColor = new ColorRGBO(color);
    }

    public abstract void paint(GraphicsContext gc);

    public abstract EForm getType();

}
