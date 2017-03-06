/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * WordsList.java
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
package view;

import java.io.IOException;
import java.util.Observer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.RandomString;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class WordsList extends TextFlow implements RootCenterGeneratedContent {

    private RandomString rs;

    public final ReadOnlyDoubleProperty readAllprogressProperty() {
        return rs.readAllprogressProperty();
    }

    public WordsList() {
        super();

        String WORDS_LIST_FOLDER = "resources/words-lists";
        String ENGLISH_WORDS = WORDS_LIST_FOLDER + "/english.txt";
        String ENGLISH_WORDS_X_4 = WORDS_LIST_FOLDER + "/englishx4.txt";
        String FRENCH_WORDS = WORDS_LIST_FOLDER + "/liste.de.mots.francais.frgut.txt";
        String ENGLISH_SHORT = WORDS_LIST_FOLDER + "/shortEnglish.txt";
        String TOLKIEN = WORDS_LIST_FOLDER + "/tolkiensCharacters.txt";

        String USED_LIST = TOLKIEN;

        int ORDER = 3;

        getChildren().add(new Text("Analysing <" + USED_LIST + "> for generation of a " + ORDER + "-order Markov Chain..."));
        getChildren().add(new Text(System.getProperty("line.separator")));

        try {

            rs = RandomString.construct(USED_LIST, ORDER);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public synchronized void addObserver(Observer o) {
        rs.addObserver(o);
    }

    private void addLine(String word) {

        StringBuilder wordSB = new StringBuilder(word);
        wordSB.setCharAt(0, Character.toUpperCase(wordSB.charAt(0)));
        getChildren().add(new Text(wordSB.toString()));
        getChildren().add(new Text(System.getProperty("line.separator")));
    }

    /**
    Generates a list of n pseudo random words and display them in the UI
    @param n the number of words to generate
     */
    public void rollWords(int n) {

        getChildren().clear();

        addLine("Rolling " + n + " words :");
        getChildren().add(new Text(System.getProperty("line.separator")));

        for (int i = 0; i < n; ++i) {
            StringBuilder rolled = rs.roll();
            rolled.setCharAt(0, Character.toUpperCase(rolled.charAt(0)));

            addLine(rolled.toString());
        }
    }

    public void cancel() {
        rs.cancel();
    }

    @Override
    public EventHandler<ActionEvent> onRollButtonAction() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rollWords(20);
            }
        };
    }
}
