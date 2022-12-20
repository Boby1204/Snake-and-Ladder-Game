package com.example.snake_ladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    @FXML
    Text number,changeTurn;
    @FXML
    ImageView player1,player2;
    int turn = 1;
    HashMap<Pair<Double,Double> , Pair<Double,Double>> snakeLadderChanges;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        getCoordinates();
        Random random = new Random();
        int rolling = random.nextInt(6) + 1;
        number.setText(""+rolling);
        if(turn == 1){
           Pair<Double,Double> move = movement(player1.getTranslateX(), player1.getTranslateY(), rolling);
           player1.setTranslateX(move.getKey());
           player1.setTranslateY(move.getValue());

           if(snakeLadderChanges.containsKey(new Pair<>(move.getKey(),move.getValue()))){
               player1.setTranslateX(snakeLadderChanges.get(
                       new Pair<>(move.getKey(),move.getValue())).getKey());
               player1.setTranslateY(snakeLadderChanges.get(
                       new Pair<>(move.getKey(),move.getValue())).getValue());
           }

           checkWin(player1.getTranslateX(), player1.getTranslateY());
        }
        else {
            Pair<Double,Double> move = movement(player2.getTranslateX(), player2.getTranslateY(), rolling);
            player2.setTranslateX(move.getKey());
            player2.setTranslateY(move.getValue());

            if(snakeLadderChanges.containsKey(new Pair<>(move.getKey(),move.getValue()))){
                player2.setTranslateX(snakeLadderChanges.get(
                        new Pair<>(move.getKey(),move.getValue())).getKey());
                player2.setTranslateY(snakeLadderChanges.get(
                        new Pair<>(move.getKey(),move.getValue())).getValue());
            }

            checkWin(player2.getTranslateX(), player2.getTranslateY());
        }
        if(rolling != 6){
            if(turn == 1){
                turn = 2;
                changeTurn.setText("Player 2's turn to play.....");
            }
        else {
                turn = 1;
                changeTurn.setText("Player 1's turn to play.....");
            }
        }

    }
    Pair<Double,Double> movement(double x, double y, int rolling){
       double moveX = x;
       double moveY = y;

       if(moveY % 100 == 0) {
           moveX += rolling * 50;
           if (moveX > 500) {
               moveX = 500 * 2 - moveX + 50;
               moveY -= 50;
           }
       }
       else {
           moveX -= rolling * 50;
           if (moveX < 50) {
               if(moveY == -450)
                   return (new Pair<>(x,y));
               moveX = -1*(moveX - 50);
               moveY -= 50;
           }
       }
        return new Pair<>(moveX,moveY);
    }
    void checkWin(Double x, Double y) throws IOException {
        if(x == 50 && y == -450){
            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setHeaderText("Result");
            if(turn == 1)
            {
                winAlert.setContentText("Player 1 has won the game");
            }
            else{
                winAlert.setContentText("Player 2 has won the game");
            }
            GamePage page = new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
            winAlert.showAndWait();
        }
    }
    void getCoordinates(){
        snakeLadderChanges = new HashMap<>();
        // For Ladders
        // 2 -> 38
        snakeLadderChanges.put(new Pair<>(100.0,0.0),new Pair<>(150.0,-150.0));
        // 7 -> 14
        snakeLadderChanges.put(new Pair<>(350.0,0.0),new Pair<>(350.0,-50.0));
        // 8 -> 31
        snakeLadderChanges.put(new Pair<>(400.0,0.0),new Pair<>(500.0,-150.0));
        // 15 -> 26
        snakeLadderChanges.put(new Pair<>(300.0,-50.0),new Pair<>(300.0,-100.0));
        // 21 -> 42
        snakeLadderChanges.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));
        // 36 -> 44
        snakeLadderChanges.put(new Pair<>(250.0,-150.0),new Pair<>(200.0,-200.0));
        // 28 -> 84
        snakeLadderChanges.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));
        // 51 -> 67
        snakeLadderChanges.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        // 71 -> 91
        snakeLadderChanges.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        // 87 -> 94
        snakeLadderChanges.put(new Pair<>(350.0,-400.0),new Pair<>(350.0,-450.0));
        // 78 -> 98
        snakeLadderChanges.put(new Pair<>(150.0,-350.0),new Pair<>(150.0,-450.0));

        // For snakes
        // 74 -> 53
        snakeLadderChanges.put(new Pair<>(350.0,-350.0),new Pair<>(400.0,-250.0));
        // 16 -> 6
        snakeLadderChanges.put(new Pair<>(250.0,-50.0),new Pair<>(300.0,0.0));
        // 62 -> 19
        snakeLadderChanges.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        // 46 -> 25
        snakeLadderChanges.put(new Pair<>(300.0,-200.0),new Pair<>(250.0,-100.0));
        // 49 -> 11
        snakeLadderChanges.put(new Pair<>(450.0,-200.0),new Pair<>(500.0,-50.0));
        // 64 -> 60
        snakeLadderChanges.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        // 89 -> 68
        snakeLadderChanges.put(new Pair<>(450.0,-400.0),new Pair<>(400.0,-300.0));
        // 92 -> 88
        snakeLadderChanges.put(new Pair<>(450.0,-450.0),new Pair<>(400.0,-400.0));
        // 95 -> 75
        snakeLadderChanges.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        // 99 -> 80
        snakeLadderChanges.put(new Pair<>(100.0,-450.0),new Pair<>(50.0,-350.0));
    }
}
