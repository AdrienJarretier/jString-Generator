/*
 * String_Generator - Text analysis for realistic random strings generation
 *
 * Main.java
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
package com.mycompany.markovian.generator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import view.ImageView;
import view.RootCenterGeneratedContent;
import view.WordsList;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class Main extends Application implements Observer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void stop() throws Exception {

        wordsList.cancel();

    }

    @Override
    public void init() throws Exception {
        wordsList = new WordsList();
        wordsList.addObserver(this);

        imageView = new ImageView();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Markovian Generator");

        // en top, left, right, bottom and center
        root = new BorderPane();

        tabPane = new TabPane();
        tabPane.setTabMinWidth(200);

        root.setCenter(tabPane);

        // main window fixed size
        Scene scene = new Scene(root, 800, 600);

        MenuBar menuBar = new MenuBar();

        Menu menuAbout = new Menu("About");

        MenuItem menuAboutAbout = new MenuItem("License");

        menuAboutAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Hyperlink gnuLicences = new Hyperlink("http://www.gnu.org/licenses/");
                Hyperlink githubProfile = new Hyperlink("https://github.com/AdrienJarretier");

                // used to set the same eventHandler on each web link,
                // that is : display the document in the default web browser
                ArrayList<Hyperlink> webLinks = new ArrayList<>();
                webLinks.add(gnuLicences);
                webLinks.add(githubProfile);

                for (final Hyperlink hyperlink : webLinks) {
                    hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            getHostServices().showDocument(hyperlink.getText());
                        }
                    });
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                Text alertTextPart1 = new Text("Markovian-Generator - Text analysis for seemingly realistic random strings generation\n"
                        + "\n"
                        + "Copyright (C) 2017 Jarretier Adrien\n"
                        + "\n"
                        + "This program is free software: you can redistribute it and/or modify\n"
                        + "it under the terms of the GNU General Public License as published by\n"
                        + "the Free Software Foundation, either version 3 of the License, or\n"
                        + "(at your option) any later version.\n"
                        + "\n"
                        + "This program is distributed in the hope that it will be useful,\n"
                        + "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
                        + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
                        + "GNU General Public License for more details.\n"
                        + "\n"
                        + "You should have received a copy of the GNU General Public License\n");

                TextFlow alertTextFlow = new TextFlow();
                alertTextFlow.getChildren().add(alertTextPart1);

                alertTextFlow.getChildren().add(new Text("along with this program.  If not, see <"));
                alertTextFlow.getChildren().add(gnuLicences);
                alertTextFlow.getChildren().add(new Text(">.\n"));
                alertTextFlow.getChildren().add(new Text("\n"));
                alertTextFlow.getChildren().add(new Text("You can find me on github there : "));
                alertTextFlow.getChildren().add(githubProfile);

                alert.getDialogPane().contentProperty().set(alertTextFlow);
                alert.setTitle("GNU General Public License");
                alert.setHeaderText("GNU General Public License");
                alert.showAndWait();
            }
        });

        menuAbout.getItems().add(menuAboutAbout);

        menuBar.getMenus().add(menuAbout);

        root.setTop(menuBar);

        tabPane.getTabs().add(new Tab("Words", wordsList));
        tabPane.getTabs().add(new Tab("Image", imageView));

        primaryStage.setScene(scene);
        primaryStage.show();

        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(wordsList.readAllprogressProperty());
        bar.prefWidthProperty().bind(root.widthProperty());
        root.setBottom(bar);
    }

    private BorderPane root;
    private TabPane tabPane;

    private WordsList wordsList;
    private ImageView imageView;

    @Override
    public void update(Observable o, Object arg) {

        root.setBottom(new Text("Done ! Use button on the right to generate a list of words"));

        String buttonPrefix = "Generate ";
        Button rollButton = new Button(buttonPrefix + tabPane.getSelectionModel().getSelectedItem().getText());
        rollButton.setOnAction(((RootCenterGeneratedContent) tabPane.getSelectionModel().getSelectedItem().getContent()).onRollButtonAction());

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                rollButton.setText(buttonPrefix + newValue.getText());
                rollButton.setMinWidth(rollButton.getWidth());

                rollButton.setOnAction(((RootCenterGeneratedContent) newValue.getContent()).onRollButtonAction());
            }
        });

        rollButton.setPrefHeight(root.getHeight());
        root.setRight(rollButton);

    }

}
