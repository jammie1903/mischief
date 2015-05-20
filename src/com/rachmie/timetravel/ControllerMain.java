package com.rachmie.timetravel;

import java.net.URL;
import java.text.DateFormatSymbols;
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
import javafx.scene.image.Image;
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
    private ImageView resultImage;

    @FXML
    private VBox messagePane;

    @FXML
    private StackPane timeMachineResultPane;

    @FXML
    private Button goButton;

    @FXML
    private Button goAgainButton;

    private Map<String, Integer> months = new HashMap<>();

    private static final int MIN_MONTH = 1991 * 12 + 6;
    private static final int MAX_MONTH = 2093 * 12;
    private static final String WORKING_IMAGE = "/time_machine_working.jpg";
    private static final String BROKEN_IMAGE = "/time_machine_broken.jpg";

    @FXML
    void startTimeTravel(ActionEvent event) {

        if (dateIsValid()) {
            TimeEvent timeEvent = TimeTravelController.getInstance().getEvent(months.get(monthSelector.getValue()), Integer.parseInt(yearEntry.getText()));
            if (timeEvent != null) {
                showEvent(timeEvent);
            } else {
                popupAngryMechanic();
            }
        } else {
            popupAngryMechanic();
        }

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
        timeMachineResultPane.setMouseTransparent(true);
        goAgainButton.setDisable(true);
        goAgainButton.setDefaultButton(false);
        goButton.setDisable(false);
        goButton.setDefaultButton(true);

        monthSelector.setValue(null);
        yearEntry.setText(null);

        FadeTransition fade = new FadeTransition(Duration.millis(1000), timeMachineResultPane);
        fade.setDelay(Duration.millis(1000));
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.play();

    }

    private void popupAngryMechanic() {
        eventText.setText("What the heck did you type in? You broke the time machine!");
        resultImage.setImage(new Image(BROKEN_IMAGE));
    }

    private void showEvent(final TimeEvent event) {
        eventText.setText(event.getEventText());
        resultImage.setImage(new Image(WORKING_IMAGE));
        if (event.getEventSound() != null && !event.getEventSound().trim().isEmpty()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    AudioController.getInstance().playSound(event.getEventSound());
                }
            }, 2000);
        }
    }

    private boolean dateIsValid() {
        try {
            int month = months.get(monthSelector.getValue());
            int year = Integer.parseInt(yearEntry.getText());

            int totalMonths = year * 12 + month;

            // july 1991 - january - 2093
            return totalMonths >= MIN_MONTH && totalMonths <= MAX_MONTH;
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
