package com.rachmie.timetravel;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ControllerMain {

    @FXML
    private ImageView wormhole;

    @FXML
    private DatePicker dateControl;

    @FXML
    void startTimeTravel(ActionEvent event) {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), wormhole);
        rt.setByAngle(720);
        rt.setAutoReverse(false);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }

}
