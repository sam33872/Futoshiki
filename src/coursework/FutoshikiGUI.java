/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 184504
 */
public class FutoshikiGUI extends Application {
    int dif;
    int siz;
    String name;
    
    @Override
    public void start(Stage primaryStage) {
        TilePane root = new TilePane();
        
        Label futoshiki = new Label("Futoshiki Puzzle");
        Label enterName = new Label("Name");
        TextField nameEnter = new TextField();
        ComboBox difficulty = new ComboBox();
        ComboBox size = new ComboBox();
        Button play = new Button();
        
        futoshiki.getStyleClass().add("futoshiki");
        // Warning message here
        // Adds options to difficulty combobox
        difficulty.getItems().add("Easy");
        difficulty.getItems().add("Medium");
        difficulty.getItems().add("Hard");
        
        // Adds options to size combobox
        size.getItems().add("2");
        size.getItems().add("3");
        size.getItems().add("4");
        size.getItems().add("5");
        
        play.setText("Play");
        play.setOnAction((ActionEvent event) -> {
            name = nameEnter.getText();
            String d = (String) difficulty.getValue();
            String s = (String) size.getValue();
            if(name.equals("") || difficulty.getSelectionModel().isEmpty() || size.getSelectionModel().isEmpty()){
                System.out.println("Invalid inputs");
            }
            else{
                switch (d) {
                    case "Easy":
                        dif = 1;
                        break;
                    case "Medium":
                        dif = 2;
                        break;
                    case "Hard":
                        dif = 3;
                        break;
                }
                
                switch (s) {
                    case "2":
                        siz = 2;
                        break;
                    case "3":
                        siz = 3;
                        break;
                    case "4":
                        siz = 4;
                        break;
                    default:
                        siz = 5;
                        break;
                }
                
                // run game
                gameScreen(primaryStage);  
            }
        });
        
        VBox mainMenu = new VBox(futoshiki,enterName,nameEnter,difficulty,size,play);
        mainMenu.getStyleClass().add("mainMenu");
        mainMenu.setSpacing(5);
        
        root.getChildren().add(mainMenu);
        
        Scene scene = new Scene(root, 350, 350);
        scene.getStylesheets().add("coursework/startCSS.css");
        
        primaryStage.setTitle("New Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Game Screen method that runs the actual game based on the choice made
     * in start
     * @param primaryStage 
     */
    public void gameScreen(Stage primaryStage){
        FutoshikiPuzzle puzz = new FutoshikiPuzzle(siz);
        Futoshiki puzzle = new Futoshiki();
        puzz.setDif(dif);
        puzz.fillPuzzle();
        FutoshikiPuzzle puzz2 = new FutoshikiPuzzle(puzz,siz);
        while(!(puzzle.solve(puzz,siz))){
            puzz2.blankGrid();
            puzz2.fillPuzzle();
            puzz2 = new FutoshikiPuzzle(puzz,siz);
        }
        final FutoshikiPuzzle game = new FutoshikiPuzzle(puzz2,siz);
        System.out.println(game.displayString());
        
        // Errors label
        Label errors = new Label();
        errors.setText("");
        
        // Buttons
        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setOnAction((ActionEvent event) -> {
            start(primaryStage);
        });
        Button complete = new Button();
        complete.setText("Complete");
        complete.setOnAction((ActionEvent event) -> {
            FutoshikiPuzzle old = new FutoshikiPuzzle(game,siz);
            if(puzzle.solve(old, siz) == true && old.completelyFullTest(siz) == true) {
                System.out.println("Congrats");
                primaryStage.close();
            }
            else{
                System.out.println("Not complete yet");
                System.out.println(game.displayString());
            }
        });
        
        //Creates buttons and label to be used in grid
        final int maxSize = (siz*2) - 1;
        Button[][] squares = new Button[maxSize][maxSize];
        Label[][] constraints = new Label[maxSize][maxSize];
        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                Button button = new Button();
                button.setText(" ");
                squares[i][j] = button;
                Label label = new Label();
                label.setText(" ");
                constraints[i][j] = label;
            }
        }
        
        int I=1;
        int J=1;
        // Fills grid with numbers
        for(int i = 0; i < maxSize; i+=2){
            for(int j = 0; j < maxSize; j+=2){
                Button button = new Button();
                final int fj = j/2, fi=i/2;
                final int size = siz;
                if(game.getNumber(I,J) != 0){
                    button.setText("" + game.getNumber(I,J));
                    button.setStyle("-fx-background-color: #D3D3D3;");
                }
                else{
                    button.setText("  ");
                    button.setStyle("-fx-background-color: #FFFFFF;");
                    button.setOnAction((ActionEvent event) -> {
                        int num = game.getNumber(fi+1,fj+1);
                        if(num < size){
                            game.setNum(fi+1, fj+1, num+1);
                            button.setText("" + game.getNumber(fi+1, fj+1));
                        }
                        else if(num == size){
                            game.setNum(fi+1, fj+1, 0);
                            button.setText("  ");
                        }
                        errors.setText(game.getProblems(game));
                        if(errors.getText().equals("")){
                            errors.setText("No errors");
                        }
                        System.out.println(game.displayString());
                    });
                } 
                squares[j][i] = button;
                J++;
            }
            I++;
            J=1;
        }
        
        I=1;
        J=1;
        // Fills grid with constraints
        for(int i = 1; i < maxSize; i+=2){
            for(int j = 0; j < maxSize; j+=2){
                Label col = new Label();
                Label row = new Label();
                if(game.getCol(J,I).equals(" ")){
                    col.setText("   ");
                }
                else{
                    col.setText(" " + game.getCol(J,I));
                }
                if(game.getRow(J,I).equals(" ")){
                    row.setText("  ");
                }
                else{
                    row.setText(" " + game.getRow(J,I));
                }
                constraints[i][j] = row;
                constraints[j][i] = col;
                J++;
                System.out.println("running point 1");
            }
            I++;
            J=1;
        }
        
        // Create new GridPane
        GridPane gameGrid = new GridPane();
        // Gets styling for GridPane
        gameGrid.getStyleClass().add("gameGrid");
        
        // Fills the GridPane with buttons and labels
        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                if((i % 2 == 0) && (j % 2 == 0)){
                    gameGrid.add(squares[i][j],i,j);
                }
                else{
                    gameGrid.add(constraints[i][j],i,j);
                    System.out.println("running point 2");
                }     
                  
            }
        }
        
        HBox buttons = new HBox(newGame, complete);
        buttons.getStyleClass().add("hbox");
        buttons.setSpacing(10);
        VBox screen = new VBox(buttons, gameGrid, errors);
        
        int width = 80 + (80 * siz);
        int height = 145 + (102 * siz);
        Scene scene = new Scene(screen, width, height);
        scene.getStylesheets().add("coursework/gameScreenCSS.css");
        
        primaryStage.setTitle("Futoshiki");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
