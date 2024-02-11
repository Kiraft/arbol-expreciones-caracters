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

    // Metodo de javafx que inicia el hilo principal de javafx y usa setStage
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
        Button delete = new Button("Borrar");
        addFunctionalities(textField, insert, delete, tree, view);
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Ingresa un token"), textField, insert, delete);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(hBox);
    }

    public void addFunctionalities(TextField textField, Button insert, Button delete, BST<Integer> tree, BstPane view){

        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                nodes.add(key);
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " is already present!");
                } else {
                    tree.insert(key);
                    view.displayTree();
                    view.setStatus(key + " is inserted!");
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
            int key = Integer.parseInt(textField.getText());
            if(!tree.search(key)){
                view.displayTree();
                view.setStatus(key +" is not present!");
            }
            else{
                tree.delete(key);
                view.displayTree();
                view.setStatus(key+" is replaced by its predecessor and is deleted!");
            }
            textField.clear();
        });
    }
}
