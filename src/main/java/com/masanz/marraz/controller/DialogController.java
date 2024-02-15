package com.masanz.marraz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Map;

public class DialogController {

    @FXML
    private Button btnAccept;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private ListView lstViewTimestamps;

    @FXML
    private ImageView imvImagen;

    private Map<String, Integer> drawingsMap = null;

    public void initialize() {
    }

    public void setDrawingsMap(Map<String, Integer> drawingsMap) {
        this.drawingsMap = drawingsMap;

        lstViewTimestamps.getItems().addAll(drawingsMap.keySet());
        ObservableList obList = FXCollections.observableList(drawingsMap.keySet().stream().toList());
        lstViewTimestamps.getItems().clear();
        lstViewTimestamps.setItems(obList);
    }

    @FXML
    void accept(ActionEvent event) {
        String ts = lstViewTimestamps.getSelectionModel().getSelectedItem().toString();
        Integer obj = drawingsMap.get(ts);
        Stage stage = (Stage) btnAccept.getScene().getWindow();
        stage.setUserData(obj);
        close();
    }

    @FXML
    void cancel(ActionEvent event) {
        Integer obj = Integer.valueOf(0);
        Stage stage = (Stage) btnAccept.getScene().getWindow();
        stage.setUserData(obj);
        close();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }


}
