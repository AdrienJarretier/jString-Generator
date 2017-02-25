/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * WordChain.java
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
represents a single word as a chain of order k with
    a following chars count : HashMap<String, HashMap<Character, Integer>> :
        String : any k length string in the word
        HashMap<Character, Integer> :
            the count of characters following that string
            '_' represents NULL

ie :
word = aalii :

    followingCharsCount :

    order 1 :
        ["_"] =
            ['a'] = 1
        ["a"] =
            ['a'] = 1,
            ['l'] = 1
        ["l"] =
            ['i'] = 1
        ['i'] =
            ['i'] = 1,
            ['_'] = 1

    order 2 :
        ["__"] =
            ['a'] = 1
        ["_a"] =
            ['a'] = 1
        ["aa"] =
            ['l'] = 1
        ["al"] =
            ['i'] = 1
        ["li"] =
            ['i'] = 1
        ["ii"] =
            ['_'] = 1
 **/
public class WordChain {

    /**
    Increments the count of the given c letter
    coming after currentPart in this world
    
    @param currentPart
    @param c
     */
    private void incrementCount(StringBuilder currentPart, char c) {

        String currentPartString = currentPart.toString();
        followingCharsCount.putIfAbsent(currentPartString, new HashMap<>());
        int count = followingCharsCount.get(currentPartString).getOrDefault(c, 0);
        followingCharsCount.get(currentPartString).put(c, count + 1);
    }

    public WordChain(String word, Integer k) {
        if (k == null) {
            k = 1;
        }

        followingCharsCount = new HashMap<>();

        // transform the word to lower case
        this.word = word.toLowerCase();

        StringBuilder currentPart = new StringBuilder();

        for (int i = 0; i < k; ++i) {
            currentPart.append('_');
        }

        // currentPart initialized all null
        // for ex with k = 3 => currentPart = "___"
        // now we get each letter and we build our chain
        for (int i = 0; i < this.word.length(); ++i) {

            char nextLetter = word.charAt(i);

            incrementCount(currentPart, nextLetter);

            currentPart.deleteCharAt(0);
            currentPart.append(nextLetter);
        }

        incrementCount(currentPart, '_');
    }

    public final HashMap<String, HashMap<Character, Integer>> getFollowings() {
        return followingCharsCount;
    }

    private String word; // the original word

    private HashMap<String, HashMap<Character, Integer>> followingCharsCount;
}
