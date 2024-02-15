package com.masanz.marraz.consts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class Consts {
    public static final String APP_TITLE = "Marraz";
    public static final double APP_WIDTH = 1024;//1152;
    public static final double APP_HEIGHT = 768;//864;
    public static final String MNUSTROKE = "mnuStroke";
    public static final String MNUFILL = "mnuFill";

    public static final int POINT_SIZE = 10;
    public static final int THICKNESS = 3;

    public static final Paint COLOR_CANVAS_BACKGROUND = Color.WHITE;
    public static final Color COLOR_DEF_STROKE = Color.BLACK;
    public static final Color COLOR_DEF_FILL = Color.WHITE;
    public static final Color COLOR_CLICK_POINT = Color.BLUE;

    public static final double HOUSE_RECTANGLE_PORTION = 0.60;

    public static String DB_PATH = "./db/marraz.db";
    public static final String DB_URL_SQLITE = "jdbc:sqlite:%s";

//    private static final String DB_URL_MYSQL = "jdbc:mysql://%s/%s";
//    private static final String DB_SERVER = "localhost";
//    private static final String DB_NAME = "libreria";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "root";

}
