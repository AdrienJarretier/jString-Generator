/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * Common.java
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
 *
 * Some commonly used functions
 */
public class Common {

    /**
     * Increments int the HasMap the count of the given c letter
     * coming after currentPart in this world
     * 
     * @param map the HasMap
     * @param currentPart part of the world we are reading
     * @param c the letter coming after that part
     * @param count the number of times to increment, default to 1
     */
    public static void incrementCount(HashMap<String, HashMap<Character, Integer>> map, String currentPart, char c, int count) {

        if (count < 1) {
            throw new IllegalArgumentException("count must be > 0");
        }

        map.putIfAbsent(currentPart, new HashMap<>());
        int CurrentCount = map.get(currentPart).getOrDefault(c, 0);
        map.get(currentPart).put(c, CurrentCount + count);
    }

    public static void incrementCount(HashMap<String, HashMap<Character, Integer>> map, String currentPart, char c) {

        incrementCount(map, currentPart, c, 1);
    }
}
