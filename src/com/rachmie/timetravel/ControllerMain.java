package com.rachmie.timetravel;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ControllerMain implements Initializable {

    @FXML
    private ImageView wormhole;

    @FXML
    private TextField yearEntry;

    @FXML
    private ComboBox<String> monthSelector;

    private Map<String, Integer> months = new HashMap<>();

    @FXML
    void startTimeTravel(ActionEvent event) {

        LocalDate.parse("21/12/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        RotateTransition rt = new RotateTransition(Duration.millis(3000), wormhole);
        rt.setByAngle(720);
        rt.setAutoReverse(false);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (int c = 0; c < 12; c++) {
            monthSelector.getItems().add(new DateFormatSymbols().getMonths()[c]);
            months.put(new DateFormatSymbols().getMonths()[c], c);
        }
    }
}
