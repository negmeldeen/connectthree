package com.example.negm.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int playerturn=0;
    int[]gamestate={2,2,2,2,2,2,2,2,2};//2 means not played area
    boolean gameIsActive =true;
    int [][]gamewining={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};//all winning positions

    public void appear (View view){
        ImageView counter = (ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        if( gamestate[tappedCounter]==2 && gameIsActive){
            gamestate[tappedCounter]=playerturn;
            counter.setTranslationY(-1000f);
            if (playerturn == 0){
                counter.setImageResource(R.drawable.x);
                playerturn++;
            }
            else {
                counter.setImageResource(R.drawable.o);
                playerturn--;
            }

            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[]wining :gamewining){
                if (gamestate[wining[0]]==gamestate[wining[1]] &&
                        gamestate[wining[1]]==gamestate[wining[2]]&&
                        gamestate[wining[0]]!=2){//condition if someone won
                    gameIsActive =false;
                    String winner = "O";
                    if (gamestate[wining[0]]==0){
                        winner ="X";
                    }
                    TextView winneris =findViewById(R.id.winnerMessege);
                    winneris.setText(winner + " has won !");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);
                    layout.setVisibility(View.VISIBLE); //to appear the messege box to start playing again

                }
                else {// if no one won
                    boolean gameIsOver =true;
                    for (int counterstate :gamestate){
                        if (counterstate==2){gameIsOver=false;}
                    }
                    if (gameIsOver){
                        TextView winneris =findViewById(R.id.winnerMessege);
                        winneris.setText("it's a tie");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }




    }
    public void playagainhoba(View view){//to reset the layout to play again
        gameIsActive=true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);
        layout.setVisibility(View.INVISIBLE);
        playerturn=0;
        // int[]gamestate={2,2,2,2,2,2,2,2,2};
        for (int i =0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ( (ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
