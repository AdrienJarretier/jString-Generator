/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * MarkovChainTest.java
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
public class MarkovChainTest {
    
    public MarkovChainTest() {
    }

    @Test
    public void display() throws IOException {
        
        String filename = "resources/words-lists/shortEnglish.txt";

        int order = 1;
        System.out.println(order + "-order : ");
        
        System.out.println("WordsReader followingLetters : ");
        
        WordsReader wr = new WordsReader(filename);

        wr.readAll(order);
        DisplayMethods.displayMap(wr.getFollowings());
        
        
        
        
        System.out.println("MarkovChain followingLetters : ");
        
        MarkovChain mc = new MarkovChain(filename, order);

        DisplayMethods.displayMapList(mc.getFollowings());
    }
    
}
