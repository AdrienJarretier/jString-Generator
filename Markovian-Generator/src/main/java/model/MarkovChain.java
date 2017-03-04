/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * MarkovChain.java
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.util.Pair;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
/**
    Represents a k-order Markov chain
    initialized with a file containing a list of words
 **/
public class MarkovChain extends Observable implements Observer {

    public static MarkovChain construct(String filename, int k) throws IOException {

        MarkovChain mc = new MarkovChain(filename, k);

        mc.wr.addObserver(mc);

        return mc;
    }

    private MarkovChain(String filename, int k) throws IOException {
        super();

        order = k;
        state = new String();
        followingLetters = new HashMap<>();

        for (int i = 0; i < k; ++i) {
            state += '_';
        }

        wr = new WordsReader(filename);

        wr.readAll(k);
    }

    /**
    Executes a transition and returns the new state
     * @return the new state after the transition
     **/
    public String transition() {

        ArrayList<Pair<Integer, Character>> letters = getFollowings().get(state);

        // get the max roll for the letters following the state we are currently in
        int maxRoll = letters.get(letters.size() - 1).getKey();
        int rolled = RandomGenerator.getGenerator().rollI(1, maxRoll);
        // while the cumulative count of this letter is less than the roll,
        // we check the next one
        int i = 0;

        while (letters.get(i).getKey() < rolled) {
            ++i;
        }
        char letter = letters.get(i).getValue();

        state = state.substring(1) + letter;

        return state;

    }

    /**
    Resets the chain to its initial state
     **/
    public void reset() {

        state = "";
        for (int i = 0; i < order; ++i) {
            state += '_';
        }
    }

    public HashMap<String, ArrayList<Pair<Integer, Character>>> getFollowings() {
        return followingLetters;
    }

    // the states, each letter following a key string with their cumulative number of appearances
    private HashMap<String, ArrayList<Pair<Integer, Character>>> followingLetters;

    private int order;

    private String state;

    private WordsReader wr;

    public void cancel() {
        wr.cancelTask();
    }

    public final ReadOnlyDoubleProperty readAllprogressProperty() {
        return wr.readAllprogressProperty();
    }

    @Override
    public void update(Observable o, Object arg) {

        wr.getFollowings().forEach((part, folLetters) -> {
            // dealing with part
            int cumulatedCount = 0;
            // for each letter following part

            followingLetters.putIfAbsent(part, new ArrayList<>());

            ArrayList<Pair<Integer, Character>> l = followingLetters.get(part);

            for (Entry<Character, Integer> e : folLetters.entrySet()) {

                cumulatedCount += e.getValue();

                l.add(new Pair<>(cumulatedCount, e.getKey()));
            }

        });

        setChanged();
        notifyObservers();
    }
}
