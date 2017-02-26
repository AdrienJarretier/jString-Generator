/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * WordChainTest.java
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

import org.junit.Test;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class WordChainTest {

    public WordChainTest() {
    }

    @Test
    public void displayChain() {
        String word = "Commimmiora";
        WordChain wc = new WordChain(word, 2);
        System.out.println("word : " + word);

        DisplayMethods.displayMap(wc.getFollowings());

    }

}
