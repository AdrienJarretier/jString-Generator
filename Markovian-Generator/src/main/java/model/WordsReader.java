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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.concurrent.Task;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
/**

Opens and reads all the words inside a text file
where each word is on a separate line.

 **/
public class WordsReader extends Observable {

    public WordsReader(String filename) {
        this.filename = filename;
        lettersCount = 0L;
        followingLetters = new HashMap<>();
    }

    /**
    reads the file and fill in the maps
     **/
    public void readAll(int k) throws FileNotFoundException, IOException {

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                followingLetters.clear();

                File file = new File(filename);

                BufferedReader wordList
                        = new BufferedReader(new FileReader(file));

                String word;

                long fileLength = file.length();

                while ((word = wordList.readLine()) != null) {

                    WordChain wordChain = new WordChain(word, k);

                    wordChain.getFollowings().forEach((part, folLetters) -> {
                        lettersCount += folLetters.size();
                        folLetters.forEach((let, count) -> {
                            Common.incrementCount(followingLetters, part, let, count);
                        });
                    });

                    // counting the newline character '\n'
                    ++lettersCount;

                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                    updateProgress(lettersCount, fileLength);
                }

                wordList.close();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                setChanged();
                notifyObservers();
            }
        };
        new Thread(task).start();

    }

    public void cancelTask() {
        task.cancel();
    }

    public HashMap<String, HashMap<Character, Integer>> getFollowings() {
        return followingLetters;
    }

    private Task<Void> task;

    public final ReadOnlyDoubleProperty readAllprogressProperty() {
        return task.progressProperty();
    }

    private String filename;

    /* map to
        count letters that can follow a given string
     */
    private HashMap<String, HashMap<Character, Integer>> followingLetters;

    private long lettersCount;
}
