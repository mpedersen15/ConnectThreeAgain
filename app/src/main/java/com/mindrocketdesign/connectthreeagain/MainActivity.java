package com.mindrocketdesign.connectthreeagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Set<Integer> redSpaces = new HashSet<>();
    Set<Integer> yellowSpaces = new HashSet<>();
    int currentPlayer = 0; // 0 = RED, 1 = YELLOW
    boolean gameOver = false;

    public void placePiece(View view) {

        ImageView current = (ImageView) view;
        TextView instructions = (TextView) findViewById(R.id.instructionsText);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);


        if (current.getDrawable() == null && !gameOver){ // only allow pieces to be placed on empty spaces

            current.setTranslationY(-1000f);

            boolean playerHasWon = false;

            if (currentPlayer == 0 ){
                current.setImageResource(R.drawable.red);
                redSpaces.add(Integer.parseInt(view.getTag().toString()));

                playerHasWon = checkForWinner(redSpaces);

                if (playerHasWon){
                    gameOver = true;
                    Log.i("MattInfo", "RED has won!");
                    instructions.setText("RED has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    playAgainButton.animate().alpha(1f).setStartDelay(500).setDuration(500);

                }else{
                    currentPlayer = 1;
                    instructions.setText("It's YELLOW's turn");
                }

            }else {
                current.setImageResource(R.drawable.yellow);
                yellowSpaces.add(Integer.parseInt(view.getTag().toString()));

                playerHasWon = checkForWinner(yellowSpaces);

                if (playerHasWon){
                    gameOver = true;
                    instructions.setText("YELLOW has won!");
                    Log.i("MattInfo", "YELLOW has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    playAgainButton.animate().alpha(1f).setStartDelay(500).setDuration(200);

                }else{
                    currentPlayer = 0;
                    instructions.setText("It's RED's turn");
                }
            }

            current.animate().translationYBy(1000f).rotation(180).setDuration(500);

            if (redSpaces.size() + yellowSpaces.size() == 9) {
                instructions.setText("It's a cat's game");
                playAgainButton.setVisibility(View.VISIBLE);
                playAgainButton.animate().alpha(1f).setStartDelay(500).setDuration(200);
            }
        }


    }

    public void playAgain(View view) {
        Log.i("MattInfo", "Need to restart the game");
        redSpaces = new HashSet<>();
        yellowSpaces = new HashSet<>();
        currentPlayer = 0;

        TextView instructions = (TextView) findViewById(R.id.instructionsText);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        instructions.setText("It's RED's turn");
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0, numChildren = grid.getChildCount(); i < numChildren; i++) {
            ImageView image = (ImageView) grid.getChildAt(i);

            image.setImageDrawable(null);
        }

        gameOver = false;

    }

    public boolean checkForWinner(Set set){
        Set<Integer> winCondition1 = new HashSet<>(Arrays.asList(1,2,3));
        Set<Integer> winCondition2 = new HashSet<>(Arrays.asList(4,5,6));
        Set<Integer> winCondition3 = new HashSet<>(Arrays.asList(7,8,9));
        Set<Integer> winCondition4 = new HashSet<>(Arrays.asList(1,4,7));
        Set<Integer> winCondition5 = new HashSet<>(Arrays.asList(2,5,8));
        Set<Integer> winCondition6 = new HashSet<>(Arrays.asList(3,6,9));
        Set<Integer> winCondition7 = new HashSet<>(Arrays.asList(1,5,9));
        Set<Integer> winCondition8 = new HashSet<>(Arrays.asList(3,5,7));
        if (
                set.containsAll(winCondition1)
                        || set.containsAll(winCondition2)
                        || set.containsAll(winCondition3)
                        || set.containsAll(winCondition4)
                        || set.containsAll(winCondition5)
                        || set.containsAll(winCondition6)
                        || set.containsAll(winCondition7)
                        || set.containsAll(winCondition8)
                ){
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
