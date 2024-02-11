package com.example.arbolbinariofx;

import com.example.arbolbinariofx.gui.bst.BstPane;
import com.example.arbolbinariofx.implementation.operaciones.BST;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    private static ArrayList<Integer> nodes = new ArrayList<>();

    // Metodo de javafx que inicia el hilo principal de javafx y usa setStage y setPane para pintar la interfaz inicial
    @Override
    public void start(Stage primaryStage){
        BST<Integer> tree = new BST<>();
        BorderPane pane = new BorderPane();
        BstPane view = new BstPane(tree);

        setPane(pane, view, tree);
        setStage(pane, primaryStage, "Arbol de exprecionnes");
    }

    //Metodo que usa el metodo start
    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane, 500,500);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Metodo que usa el metodo start
    public void setPane(BorderPane pane, BstPane view, BST<Integer> tree){
        pane.setCenter(view);

        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);

        Button insert = new Button("Insertar");
        addFunctionalities(textField, insert, tree, view);

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Ingresa un token"), textField, insert);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        pane.setBottom(hBox);
    }

    //Se agregan los eventos de OnAction al boton insert
    public void addFunctionalities(TextField textField, Button insert, BST<Integer> tree, BstPane view){

        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No has ingresado ningun token", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                nodes.add(key);
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " ya esta insertado en el arbol");
                } else {
                    tree.insert(key);
                    view.displayTree();
                    view.setStatus(key + " se inserto");
                }
                textField.clear();
            }
        });

    }
}
