package com.masanz.marraz.manager;

import com.masanz.marraz.consts.Consts;
import com.masanz.marraz.db.Dao;
import com.masanz.marraz.enums.EForm;
import com.masanz.marraz.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.masanz.marraz.consts.Consts.THICKNESS;

public class Manager {

    private GraphicsContext gc;

    private List<AForm> formsList;

    private List<Point> pointsList;

    private EForm tool;

    private Color strokeColor;
    private Color fillColor;

    public Manager(GraphicsContext gc) {
        this.gc = gc;
        initGc();
        formsList = new ArrayList<>();
        pointsList = new ArrayList<>();
        tool = EForm.LINE;
        strokeColor = Consts.COLOR_DEF_STROKE;
        fillColor = Consts.COLOR_DEF_FILL;
        cleanCanvas();
    }

    private void initGc() {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(THICKNESS);
    }

    public EForm getTool() {
        return tool;
    }

    public void setTool(EForm tool) {
        this.tool = tool;
        resetearPuntos();
    }

    public int getNumeroPuntos(EForm herramienta) {
        switch (herramienta) {
            case LINE:
                return 2;
            case CIRCLE:
                return 2;
            case RECTANGLE:
                return 2;
            case TRIANGLE:
                return 3;
            case HOUSE:
                return 3;
            default:
                return 0;
        }
    }

    public void resetearPuntos() {
        pointsList.clear();
    }

    public void addPoint(double x, double y) {
        Point p = new Point(x, y);
        p.paint(gc);
        pointsList.add(p);
        if (pointsList.size() == getNumeroPuntos(tool)) {
            AForm forma = null;
            switch (tool) {
                case LINE:
                    forma = new Line(pointsList.get(0), pointsList.get(1));
                    break;
                case CIRCLE:
                    forma = new Circle(pointsList.get(0), pointsList.get(1));
                    break;
                case RECTANGLE:
                    forma = new Rectangle(pointsList.get(0), pointsList.get(1));
                    break;
                case TRIANGLE:
                    forma = new Triangle(pointsList.get(0), pointsList.get(1), pointsList.get(2));
                    break;
                case HOUSE:
                    forma = new House(pointsList.get(0), pointsList.get(1), pointsList.get(2));
                    break;
            }
            forma.setStrokeColor(new ColorRGBO(strokeColor));
            if (forma instanceof AFormFillable) {
                ((AFormFillable) forma).setJavaFxFillColor(fillColor);
            }
            forma.paint(gc);
            formsList.add(forma);
            resetearPuntos();
        }
    }

    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void cleanCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Consts.COLOR_CANVAS_BACKGROUND);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        formsList.clear();
        resetearPuntos();
    }

    public double getAreaSum() {
        double area = 0;
        for (AForm forma : formsList) {
            if (forma instanceof AFormFillable) {
                area += ((AFormFillable) forma).getArea();
            }
        }
        return area;
    }

    public int saveDrawing() {
        if (!Dao.testConnection()){
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            String path = selectedFile.getAbsolutePath();
            Dao.setupConnection(path);
            if (!Dao.testConnection()){
                return 0;
            }
        }
        return Dao.save(formsList);
    }

    public Map<String, Integer> getDrawings() {
        return Dao.getDrawings();
    }

    public void loadDrawing(int drawingId) {
        cleanCanvas();
        this.formsList = Dao.getForms(drawingId);
        drawFormsList();
    }

    private void drawFormsList() {
        for (AForm forma : formsList) {
            forma.paint(gc);
        }
    }
}
