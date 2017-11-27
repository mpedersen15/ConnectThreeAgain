package com.mindrocketdesign.connectthreeagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Set<Integer> redSpaces = new HashSet<>();
    Set<Integer> yellowSpaces = new HashSet<>();
    int currentPlayer = 0; // 0 = RED, 1 = YELLOW

    public void placePiece(View view) {

        ImageView current = (ImageView) view;

        if (current.getDrawable() == null){ // only allow for

            current.setTranslationY(-1000f);

            boolean playerHasWon = false;

            if (currentPlayer == 0 ){
                current.setImageResource(R.drawable.red);
                redSpaces.add(Integer.parseInt(view.getTag().toString()));

                playerHasWon = checkForWinner(redSpaces);

                if (playerHasWon){
                    Log.i("MattInfo", "RED has won!");
                }else{
                    currentPlayer = 1;
                }

            }else {
                current.setImageResource(R.drawable.yellow);
                yellowSpaces.add(Integer.parseInt(view.getTag().toString()));

                playerHasWon = checkForWinner(yellowSpaces);

                if (playerHasWon){
                    Log.i("MattInfo", "YELLOW has won!");
                }else{
                    currentPlayer = 0;
                }
            }

            current.animate().translationYBy(1000f).rotation(180).setDuration(500);
        }


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
