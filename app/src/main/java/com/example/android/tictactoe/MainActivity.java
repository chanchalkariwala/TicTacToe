package com.example.android.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int TURN = 1;

    private static int[] sPlayer1 = new int[9];
    private static int[] sPlayer2 = new int[9];

    private static int[][] sBoard = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPlayer1 = new int[9];
        sPlayer2 = new int[9];

        sBoard = new int[3][3];
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3; j++)
                sBoard[i][j] = i*3 + (j+1);

        GridLayout lGrid = (GridLayout) findViewById( R.id.grid);

        updatePlayerName();

        for( int i = 0; i < lGrid.getChildCount(); i++ )
        {
            if( lGrid.getChildAt( i ) instanceof TextView)
            {
                TextView lTextView = (TextView) lGrid.getChildAt(i);
                lTextView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        click(v);
                        checkForWinner();
                    }
                });
            }
        }
    }

    public void click(View pView)
    {
        if(TURN > 9)
        {
            clearAll();
            return;
        }

        String lTic = "X";
        int liColor = Color.RED;

        int liCell = Integer.valueOf((String)pView.getTag()) - 1 ;

        if(TURN % 2 == 0)
        {
            lTic = "O";
            liColor = Color.BLUE;
            //sPlayer2[liCell - 1] = 1;

            sBoard[liCell/3][liCell%3] = 2;
        }
        else
        {
            //sPlayer1[liCell - 1] = 1;

            sBoard[liCell/3][liCell%3] = 1;
        }

        ((TextView)pView).setText(lTic);
        ((TextView)pView).setTextColor(liColor);

        TURN++;
    }

    public void updatePlayerName()
    {
        String lstrPlayer = "Player ";
        int liColor = Color.BLACK;

        if(TURN % 2 == 1)
        {
            lstrPlayer = lstrPlayer.concat("1");
            liColor = Color.RED;
        }
        else
        {
            lstrPlayer = lstrPlayer.concat("2");
            liColor = Color.BLUE;
        }

        ((TextView)findViewById(R.id.player_name)).setText(lstrPlayer);
        ((TextView)findViewById(R.id.player_name)).setTextColor(liColor);
    }

    public void checkForWinner()
    {

        //1,2,3;-6 ; 4,5,6;-15 7,8,9;-24 - Difference of 1 :
        //1,4,7;-12 2,5,8;-15 3,6,9;-18 - Difference of 3
        //1,5,9;-15 3,5,7;-15 - Difference of 4, 2

        for(int row = 0; row < 3; row++)
        {
            if(sBoard[row][0] == sBoard[row][1] && sBoard[row][1] == sBoard[row][2])
            {
                showWinner(row*3+1, row*3+2, row*3+3);
                return;
            }
        }

        for(int column = 0; column < sBoard.length; column++)
        {
            if(sBoard[0][column] == sBoard[1][column] && sBoard[1][column] == sBoard[2][column])
            {
                int liColumn = 1;
                showWinner(0*3+liColumn, 1*3+liColumn, 2*3+liColumn);
                return;
            }
        }

        if(sBoard[0][0] == sBoard[1][1] && sBoard[1][1] == sBoard[2][2])
        {
            showWinner(1, 5, 9);
            return;
        }

        if(sBoard[0][2] == sBoard[1][1] && sBoard[1][1] == sBoard[2][0])
        {
            showWinner(3, 5, 7);
            return;
        }

        updatePlayerName();
    }

    public void showWinner(int A, int B, int C)
    {
        TURN = 10;

        GridLayout lGrid = (GridLayout) findViewById( R.id.grid);

        for( int i = 0; i < lGrid.getChildCount(); i++ )
        {
            Integer lTag = Integer.valueOf((String)lGrid.getChildAt( i ).getTag());

            if(A == lTag || B == lTag || C == lTag)
            {
                lGrid.getChildAt( i ).setBackgroundResource(R.drawable.winner_tile);
                continue;
            }
        }
    }

    public void clearAll()
    {
        TURN = 1;
        GridLayout lGrid = (GridLayout) findViewById( R.id.grid);

        for( int i = 0; i < lGrid.getChildCount(); i++ )
        {
            if( lGrid.getChildAt( i ) instanceof TextView)
            {
                TextView lTextView = (TextView) lGrid.getChildAt(i);
                lTextView.setText("");
                lTextView.setBackgroundResource(R.drawable.border);
            }
        }

        sPlayer1 = new int[9];
        sPlayer2 = new int[9];

        sBoard = new int[3][3];
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3; j++)
                sBoard[i][j] = i*3 + (j+1);
    }
}
