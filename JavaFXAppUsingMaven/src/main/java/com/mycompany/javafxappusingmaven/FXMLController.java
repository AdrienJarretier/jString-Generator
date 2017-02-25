/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * FXMLController.java
 * Copyright (C) 2017 Jarretier Adrien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycompany.javafxappusingmaven;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.RandomGenerator;
import umontreal.ssj.rng.MT19937;

public class FXMLController implements Initializable {

    private MT19937 rng;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        label.setText(String.valueOf(RandomGenerator.getGenerator().rollI(0, 10)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
