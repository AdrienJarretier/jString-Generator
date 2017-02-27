/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * WordsReaderTest.java
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
import org.junit.Test;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class WordsReaderTest {

    public WordsReaderTest() {
    }

    @Test
    public void display() throws IOException {
        WordsReader wr = new WordsReader("resources/words-lists/shortEnglish.txt");

        System.out.println("followingLetters : ");

        System.out.println("1-order : ");
        wr.readAll(1);
        DisplayMethods.displayMap(wr.getFollowings());

        System.out.println("2-order : ");
        wr.readAll(2);
        DisplayMethods.displayMap(wr.getFollowings());
    }

}
