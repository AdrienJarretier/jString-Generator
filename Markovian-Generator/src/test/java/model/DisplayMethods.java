/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * DisplayMethods.java
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

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
class DisplayMethods {

    static void displayMap(HashMap<String, HashMap<Character, Integer>> mapCmapCUI) {

        mapCmapCUI.forEach((part, folLetters) -> {
            System.out.println("[" + part + "]");
            folLetters.forEach((let, count) -> {

                System.out.println(" | -- [" + let + "] : " + count);

            });
        });
    }

    static void displayMapList(HashMap<String, ArrayList<Pair<Integer, Character>>> mapSlistIC) {

        mapSlistIC.forEach((part, folLetters) -> {
            System.out.println("[" + part + "]");
            folLetters.forEach((pair) -> {

                int count = pair.getKey();
                char let = pair.getValue();
                System.out.println(" | -- [" + count + "] : " + let);

            });
        });
    }

}
