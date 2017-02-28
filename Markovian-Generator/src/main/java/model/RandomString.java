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

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class RandomString {

    /**
        Analyses the given wordsList test file to get statistics about the order of letters in words
        k : Order of the generated Markov chain
     **/
    public RandomString(String wordsList, int k) throws IOException {
        mc = new MarkovChain(wordsList, k);
        order = k;
    }

    /**
        Generates one random word using a Markov Chain
        of size in between minSize and maxSize
     **/
    public String roll(int minSize, int maxSize) {
        if (minSize < 1) {
            throw new IllegalArgumentException("minSize should be greater than 0");
        }

        if (maxSize < minSize) {
            throw new IllegalArgumentException("maxSize should be greater than minSize");
        }

        StringBuilder word;

        do {
            word = new StringBuilder();
            mc.reset();

            char newLetter;
            do {
                newLetter = mc.transition().charAt(order - 1);
                word.append(newLetter);
            } while (newLetter != '_');

            word.deleteCharAt(word.length() - 1);
        } while (minSize > word.length() || word.length() > maxSize);

        return word.toString();
    }

    private MarkovChain mc;

    private final int order;
}