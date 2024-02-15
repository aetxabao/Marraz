package com.masanz.marraz.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javafx.scene.paint.Color;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonPropertyOrder({ "red", "green", "blue", "opacity" })
public class ColorRGBO {

    @JsonProperty("red")
    private double red;
    @JsonProperty("green")
    private double green;
    @JsonProperty("blue")
    private double blue;
    @JsonProperty("opacity")
    private double opacity;

    public ColorRGBO() {
        this(0,0,0,0);
    }

    public ColorRGBO(double red, double green, double blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public ColorRGBO(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }

    // region getters y setters

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    // endregion

    public Color getJavaFxColor() {
        return new Color(red, green, blue, opacity);
    }

    public static Color getJavaFxColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "WHITE": return Color.WHITE;
            case "RED": return Color.RED;
            case "GREEN": return Color.GREEN;
            case "BLUE": return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

}
