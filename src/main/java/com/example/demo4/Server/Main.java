package com.example.demo4.Server;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
  
public class Main extends Application{
      
    CheckBox java;
    CheckBox javaScript;
    CheckBox csharp;
    Label selectedLangs;
     
    public static void main(String[] args) {
          
        Application.launch(args);
    }
      
    @Override
    public void start(Stage stage) throws Exception {
          
        selectedLangs = new Label("No languages seleted");
         
        java = new CheckBox("Java");
        javaScript = new CheckBox("JavaScript");
        csharp = new CheckBox("C#");
         
        java.setOnAction(event -> show());
        javaScript.setOnAction(event -> show());
        csharp.setOnAction(event -> show());
         
        FlowPane root = new FlowPane(Orientation.VERTICAL, 0, 10);
        root.getChildren().addAll(java, javaScript, csharp, selectedLangs);
        root.setPadding(new Insets(0, 0, 0, 10));
        Scene scene = new Scene(root, 250, 200);
          
        stage.setScene(scene);
          
        stage.setTitle("Hello JavaFX");
        stage.show();
    }
     
    private void show(){
         
        String allSelected = "";
         
        if(java.isSelected()) allSelected += "Java ";
        if(javaScript.isSelected()) allSelected += "JavaScript ";
        if(csharp.isSelected()) allSelected += "C#";
         
        if(allSelected.equals("")) 
            selectedLangs.setText("No languages selected");
        else
            selectedLangs.setText(allSelected);
    }
}