/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * RandomString.java
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
package model;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class RandomString extends Observable implements Observer {

    public static RandomString construct(String wordsListFile, int k) throws IOException {

        RandomString rs = new RandomString(wordsListFile, k);

        rs.mc.addObserver(rs);

        return rs;
    }

    /**
        Analyses the given wordsList test file to get statistics about the order of letters in words
        k : Order of the generated Markov chain
     **/
    private RandomString(String wordsList, int k) throws IOException {
        super();

        mc = MarkovChain.construct(wordsList, k);

        order = k;
    }

    /**
     * Generates one random word using a Markov Chain
     * @return The generated String
     */
    public StringBuilder roll() {

        StringBuilder word;

        word = new StringBuilder();
        mc.reset();

        char newLetter;
        do {
            newLetter = mc.transition().charAt(order - 1);
            word.append(newLetter);
        } while (newLetter != '_');

        word.deleteCharAt(word.length() - 1);

        return word;
    }

    /**
        Generates one random word using a Markov Chain
        of size in between minSize and maxSize
     * @param minSize the minimum length acceptable
     * @param maxSize the maximum length
     * @return a random word which length is between minSize and maxSize
     **/
    public StringBuilder roll(int minSize, int maxSize) {
        if (minSize < 1) {
            throw new IllegalArgumentException("minSize should be greater than 0");
        }

        if (maxSize < minSize) {
            throw new IllegalArgumentException("maxSize should be greater than minSize");
        }
        StringBuilder word;
        do {

            word = roll();

        } while (minSize > word.length() || word.length() > maxSize);

        return word;
    }

    private MarkovChain mc;

    private final int order;

    public void cancel() {
        mc.cancel();
    }

    public final ReadOnlyDoubleProperty readAllprogressProperty() {
        return mc.readAllprogressProperty();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
