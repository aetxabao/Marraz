package com.masanz.marraz.controller;

import com.masanz.marraz.Main;
import com.masanz.marraz.enums.EForm;
import com.masanz.marraz.manager.Manager;
import com.masanz.marraz.model.ColorRGBO;
import static com.masanz.marraz.consts.Consts.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class MainController {

    // region FXML attributes
    @FXML
    private Button btnArea;

    @FXML
    private Button btnClean;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorFill;

    @FXML
    private ColorPicker colorStroke;

    @FXML
    private ToggleGroup herramienta;

    @FXML
    private Label lblFill;

    @FXML
    private Label lblStroke;

    @FXML
    private MenuItem mnuAbout;

    @FXML
    private MenuItem mnuArea;

    @FXML
    private MenuItem mnuCircle;

    @FXML
    private MenuItem mnuFillBlack;

    @FXML
    private MenuItem mnuFillBlue;

    @FXML
    private MenuItem mnuFillGreen;

    @FXML
    private MenuItem mnuFillRed;

    @FXML
    private MenuItem mnuFillWhite;

    @FXML
    private MenuItem mnuHouse;

    @FXML
    private MenuItem mnuLine;

    @FXML
    private MenuItem mnuLoad;

    @FXML
    private MenuItem mnuClose;

    @FXML
    private MenuItem mnuNew;

    @FXML
    private MenuItem mnuRectangle;

    @FXML
    private MenuItem mnuSave;

    @FXML
    private MenuItem mnuStrokeBlack;

    @FXML
    private MenuItem mnuStrokeBlue;

    @FXML
    private MenuItem mnuStrokeGreen;

    @FXML
    private MenuItem mnuStrokeRed;

    @FXML
    private MenuItem mnuStrokeWhite;

    @FXML
    private MenuItem mnuTriangle;

    @FXML
    private Pane paneCanvas;

    @FXML
    private RadioButton rbtHouse;

    @FXML
    private RadioButton rbtCircle;

    @FXML
    private RadioButton rbtLine;

    @FXML
    private RadioButton rbtRectangle;

    @FXML
    private RadioButton rbtTriangle;

    @FXML
    private HBox toolbox;
    // endregion

    private Manager manager;

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        manager = new Manager(gc);
    }

    @FXML
    public void onCanvasMouseClicked(MouseEvent event) {
        manager.addPoint(event.getX(), event.getY());
    }

    @FXML
    public void onStrokeColorChange(ActionEvent event) {
        manager.setStrokeColor(colorStroke.getValue());
    }

    @FXML
    public void onMenuStrokeColorChange(ActionEvent event) {
        String colorName = ((MenuItem)event.getSource()).getId();
        colorName = colorName.substring(MNUSTROKE.length());
        colorStroke.setValue(ColorRGBO.getJavaFxColor(colorName));
        manager.setStrokeColor(colorStroke.getValue());
    }

    @FXML
    public void onFillColorChange(ActionEvent event) {
        manager.setFillColor(colorFill.getValue());
    }

    @FXML
    public void onMenuFillColorChange(ActionEvent event) {
        String colorName = ((MenuItem)event.getSource()).getId();
        colorName = colorName.substring(MNUFILL.length());
        colorFill.setValue(ColorRGBO.getJavaFxColor(colorName));
        manager.setFillColor(colorFill.getValue());
    }

    @FXML
    public void onToolChange(ActionEvent event) {
        if (event.getSource() == rbtLine || event.getSource() == mnuLine) {
            rbtLine.setSelected(true);
            manager.setTool(EForm.LINE);
        } else if (event.getSource() == rbtCircle || event.getSource() == mnuCircle) {
            rbtCircle.setSelected(true);
            manager.setTool(EForm.CIRCLE);
        } else if (event.getSource() == rbtRectangle || event.getSource() == mnuRectangle) {
            rbtRectangle.setSelected(true);
            manager.setTool(EForm.RECTANGLE);
        } else if (event.getSource() == rbtTriangle || event.getSource() == mnuTriangle) {
            rbtTriangle.setSelected(true);
            manager.setTool(EForm.TRIANGLE);
        } else if (event.getSource() == rbtHouse || event.getSource() == mnuHouse) {
            rbtHouse.setSelected(true);
            manager.setTool(EForm.HOUSE);
        }
    }

    @FXML
    public void onCleanCanvas(ActionEvent actionEvent) {
        if (showConfirm("empezar un nuevo dibujo")){
            manager.cleanCanvas();
        }
    }

    @FXML
    public void onGetArea(ActionEvent actionEvent) {
        double area = manager.getAreaSum();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(String.format("El area total es: %.2f u^2", area));
        alert.showAndWait();
    }

    @FXML
    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Aplicación desarrollada para aprender a programar.");
        alert.showAndWait();
    }

    @FXML
    public void onSaveDrawing(ActionEvent event) {
        int v = manager.saveDrawing();
        if (v == 0) {
            showError("No se puede conectar a la base de datos indicada.");
        }else if (v < 0) {
            showError("Error al grabar los datos.");
        }else {
            showInfo("Dibujo guardado correctamente con id " + v);
        }
    }

    @FXML
    void onLoadDrawing(ActionEvent event) {
        Integer v = selectDrawingFromDialog(event);
        if (v > 0) {
            manager.loadDrawing(v);
        }
    }

    @FXML
    void onClose(ActionEvent event) {
        if (showConfirm("salir")){
            Platform.exit();
        }
    }

    private void showError(String msg) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha detectado un problema.");
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    private void showInfo(String msg) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Acción realizada.");
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    private boolean showConfirm(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Confirma que desea");
        alert.setContentText(msg);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK || result.get() == ButtonType.YES);
    }

    private Integer selectDrawingFromDialog(ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/masanz/marraz/view/drawingSelector.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> drawingsMap = manager.getDrawings();

        DialogController dialogController = loader.getController();
        dialogController.setDrawingsMap(drawingsMap);

        stage.setScene(new Scene(root));
        stage.setTitle("Selector de dibujos");
        stage.initOwner( ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow() );
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return (Integer) stage.getUserData();
    }

}