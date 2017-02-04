package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int TURN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout lGrid = (GridLayout) findViewById( R.id.grid);

        for( int i = 0; i < lGrid.getChildCount(); i++ )
        {
            if( lGrid.getChildAt( i ) instanceof TextView)
            {
                TextView lTextView = (TextView) lGrid.getChildAt(i);
                lTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click(v);
                    }
                });
            }
        }
    }

    public void click(View pView)
    {
        TURN++;
        if(TURN > 9)
        {
            clearAll();
            return;
        }

        String lTic = "X";

        if(TURN % 2 == 0)
        {
            lTic = "O";
        }

        ((TextView)pView).setText(lTic);
    }

    public void clearAll()
    {
        TURN = 0;
        GridLayout lGrid = (GridLayout) findViewById( R.id.grid);

        for( int i = 0; i < lGrid.getChildCount(); i++ )
        {
            if( lGrid.getChildAt( i ) instanceof TextView)
            {
                TextView lTextView = (TextView) lGrid.getChildAt(i);
                lTextView.setText("");
            }
        }
    }
}
