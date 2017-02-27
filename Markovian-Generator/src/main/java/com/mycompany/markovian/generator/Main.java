/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.markovian.generator;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.RandomString;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);

//        String WORDS_LIST_FOLDER = "resources/words-lists";
//        String ENGLISH_WORDS = WORDS_LIST_FOLDER + "/english.txt";
//        String FRENCH_WORDS = WORDS_LIST_FOLDER + "/liste.de.mots.francais.frgut.txt";
//        String ENGLISH_SHORT = WORDS_LIST_FOLDER + "/shortEnglish.txt";
//
//        int ORDER = 3;
//
//        System.out.println("Analysing language for generation of a " + ORDER + "-order Markov Chain...");
//
//        RandomString rs;
//        try {
//            rs = new RandomString(ENGLISH_WORDS, ORDER);
//
//            System.out.println("Done.");
//
//            int minLength, maxLength;
//
//            System.out.print("Min length : ");
//            Scanner sc = new Scanner(System.in);
//
//            minLength = sc.nextInt();
//            System.out.print("Max length : ");
//            maxLength = sc.nextInt();
//
//            System.out.println("Rolling 16 words :");
//
//            for (int i = 0; i < 16; ++i) {
//                StringBuilder rolled = new StringBuilder(rs.roll(minLength, maxLength));
//                rolled.setCharAt(0, Character.toUpperCase(rolled.charAt(0)));
//
//                System.out.println(rolled);
//            }
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Markovian Generator");

        // en top, left, right, bottom and center
        BorderPane root = new BorderPane();

        // main window fixed size of 1280 x 720p
        Scene scene = new Scene(root, 1280, 720);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
