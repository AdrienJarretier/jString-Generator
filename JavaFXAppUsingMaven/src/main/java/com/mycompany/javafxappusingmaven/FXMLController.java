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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.RandomGenerator;
import model.RandomString;

public class FXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        label.setText(String.valueOf(RandomGenerator.getGenerator().rollI(0, 10)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String WORDS_LIST_FOLDER = "resources/words-lists";
        String ENGLISH_WORDS = WORDS_LIST_FOLDER + "/english.txt";
        String FRENCH_WORDS = WORDS_LIST_FOLDER + "/liste.de.mots.francais.frgut.txt";
        String ENGLISH_SHORT = WORDS_LIST_FOLDER + "/shortEnglish.txt";

        int ORDER = 3;

        System.out.println("Analysing language for generation of a " + ORDER + "-order Markov Chain...");

        RandomString rs;
        try {
            rs = new RandomString(ENGLISH_WORDS, ORDER);

            System.out.println("Done.");

            int minLength, maxLength;

            System.out.print("Min length : ");
            Scanner sc = new Scanner(System.in);

            minLength = sc.nextInt();
            System.out.print("Max length : ");
            maxLength = sc.nextInt();

            System.out.println("Rolling 16 words :");

            for (int i = 0; i < 16; ++i) {
                StringBuilder rolled = new StringBuilder(rs.roll(minLength, maxLength));
                rolled.setCharAt(0, Character.toUpperCase(rolled.charAt(0)));

                System.out.println(rolled);
            }
        } catch (IOException ex) {
//            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }
}
