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

import java.io.File;
import java.util.Observer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import model.RandomString;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class WordsList extends ListView<TextField> implements RootCenterGeneratedContent {

    private RandomString rs;

    private ObservableList<TextField> names;

    public final ReadOnlyDoubleProperty readAllprogressProperty() {
        return rs.readAllprogressProperty();
    }

    public WordsList(String USED_LIST) {
        super();

        names = FXCollections.observableArrayList();

        setItems(names);

//        String WORKING_DIR = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
//
//        String WORDS_LIST_FOLDER = WORKING_DIR + "/resources/words-lists";
//        String ENGLISH_WORDS = WORDS_LIST_FOLDER + "/english.txt";
//        String ENGLISH_WORDS_X_4 = WORDS_LIST_FOLDER + "/englishx4.txt";
//        String FRENCH_WORDS = WORDS_LIST_FOLDER + "/liste.de.mots.francais.frgut.txt";
//        String ENGLISH_SHORT = WORDS_LIST_FOLDER + "/shortEnglish.txt";
//        String TOLKIEN = WORDS_LIST_FOLDER + "/tolkiensCharacters.txt";
//
//        String USED_LIST = ENGLISH_WORDS;

        int ORDER = 3;

        TextField tf = new TextField("Analysing <" + USED_LIST + "> for generation of a " + ORDER + "-order Markov Chain...");
        tf.setEditable(false);
        tf.setBackground(Background.EMPTY);
        names.add(tf);

        setFixedCellSize(22);

        rs = RandomString.construct(USED_LIST, ORDER);

    }

    public WordsList(ObservableList<TextField> names, ObservableList<TextField> items) {
        super(items);
        this.names = names;
    }

    public synchronized void addObserver(Observer o) {
        rs.addObserver(o);
    }

    private void addLine(String word) {

        StringBuilder wordSB = new StringBuilder(word);
        wordSB.setCharAt(0, Character.toUpperCase(wordSB.charAt(0)));
        TextField tf = new TextField(wordSB.toString());
        tf.setEditable(false);
        tf.setBackground(Background.EMPTY);
        names.add(tf);
    }

    /**
    Generates a list of n pseudo random words and display them in the UI
    @param n the number of words to generate
     */
    public void rollWords(int n) {

        names.clear();

        addLine("Rolling " + n + " words :");
        addLine(" ");

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
