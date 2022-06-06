package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean gameActive = true;

    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
    TextView status;

    int player_o = 0;
    int player_x = 1;

    int activePlayer = player_o;

    int[] filledPos={2,2,2,2,2,2,2,2,2,2};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status=findViewById(R.id.status);
        status.setText("O's Turn");

        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);





    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        if (!gameActive){
            return;
        }

        
        ImageView clickImage = findViewById(v.getId());
        int clickTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickTag]!=2){
            return;
        }

        filledPos[clickTag] = activePlayer;

        if (activePlayer == player_o){
            clickImage.setImageResource(R.drawable.o);
            activePlayer = player_x;
            status.setText("X's Turn");
        }else {
            clickImage.setImageResource(R.drawable.x);
            activePlayer = player_o;
            status.setText("O's Turn");

        }
        checkForWin();

    }

    private void checkForWin() {
        //Winner checking

        int[][] winPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i=0 ;i<8;i++){
            int val0=winPosition[i][0];
            int val1=winPosition[i][1];
            int val2=winPosition[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2] && filledPos[val0]!= 2){
                    gameActive=false;
                    if (filledPos[val0] == player_o){
                        showDialogue("O is WINNER");
                    }else{
                        showDialogue("X is WINNER");
                    }
                }
            }
        }



    private void showDialogue(String winnerText) {
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();

            }
        }).show();
    }

    private void restartGame() {
        activePlayer=player_o;
        status.setText("O's Turn");
        filledPos = new int[]{2,2,2,2,2,2,2,2,2,2};
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);


        gameActive = true;
    }
}