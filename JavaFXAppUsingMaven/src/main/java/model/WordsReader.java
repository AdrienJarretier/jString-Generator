/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * WordsReader.java
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

import java.util.HashMap;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
/**

Opens and reads all the words inside a text file
where each word is on a separate line.

 **/
public class WordsReader {

    public WordsReader(String filename) {
        this.filename = filename;
        wordsCount = 0;
    }

    /**
    reads the file and fill in the maps
     **/
    public void readAll(Integer k) {
        if (k == null) {
            k = 1;
        }
    }

    public HashMap<String, HashMap<Character, Integer>> getFollowings() {
        return followingLetters;
    }

    private String filename;

    /* map to
        count letters that can follow a given string
     */
    private HashMap<String, HashMap<Character, Integer>> followingLetters;

    private int wordsCount;
}
