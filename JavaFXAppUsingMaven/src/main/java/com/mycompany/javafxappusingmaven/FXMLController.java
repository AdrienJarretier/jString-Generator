package com.mycompany.javafxappusingmaven;

import java.net.URL;
import java.sql.Time;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.LongStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.MT19937;

public class FXMLController implements Initializable {

    private MT19937 rng;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        label.setText(String.valueOf(rng.nextInt(1, 10)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Random rand = new Random();
        LongStream seed = LongStream.concat(rand.longs(3, 1, 4294967087L), rand.longs(3, 1, 4294944443L));

        MRG32k3a.setPackageSeed(seed.toArray());

        MRG32k3a mrg = new MRG32k3a();

        System.out.println(mrg.nextDouble());
        rng = new MT19937(mrg);

    }
}
