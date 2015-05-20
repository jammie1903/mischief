package com.rachmie.timetravel;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControllerMain implements Initializable {

    @FXML
    private TextField yearEntry;

    @FXML
    private ComboBox<String> monthSelector;

    @FXML
    private Label eventText;

    @FXML
    private ImageView wormhole;

    @FXML
    private VBox messagePane;

    @FXML
    private StackPane timeMachineResultPane;

    @FXML
    private Button goButton;

    @FXML
    private Button goAgainButton;

    private Map<String, Integer> months = new HashMap<>();

    @FXML
    void startTimeTravel(ActionEvent event) {

        boolean success = yearIsValid();

        LocalDate.parse("21/12/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        RotateTransition rt = new RotateTransition(Duration.millis(4000), wormhole);
        rt.setByAngle(720);
        rt.setAutoReverse(false);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();

        messagePane.setOpacity(0);
        timeMachineResultPane.setOpacity(0);

        goButton.setDisable(true);
        goButton.setDefaultButton(false);
        goAgainButton.setDisable(true);
        goAgainButton.setDefaultButton(true);

        FadeTransition fade = new FadeTransition(Duration.millis(1000), timeMachineResultPane);
        fade.setDelay(Duration.millis(2000));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.play();

        fade = new FadeTransition(Duration.millis(1000), messagePane);
        fade.setDelay(Duration.millis(3000));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.play();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                timeMachineResultPane.setMouseTransparent(false);
                goAgainButton.setDisable(false);
            }
        }, 4000);
    }

    @FXML
    void resetTimeMachine(ActionEvent event) {
        System.out.println("hello");
    }

    private void popupAngryMechanic() {
        // TODO Auto-generated method stub

    }

    private boolean yearIsValid() {
        try {
            int year = Integer.parseInt(yearEntry.getText());
            //july 1991 - january - 2093
            return year > 1990 && year < 2094;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (int c = 0; c < 12; c++) {
            monthSelector.getItems().add(new DateFormatSymbols().getMonths()[c]);
            months.put(new DateFormatSymbols().getMonths()[c], c);
        }
    }
}
