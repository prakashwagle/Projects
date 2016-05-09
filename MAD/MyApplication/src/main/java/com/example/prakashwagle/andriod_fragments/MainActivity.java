package com.example.prakashwagle.andriod_fragments;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements firstFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final firstFragment f = (firstFragment) getFragmentManager().findFragmentById(R.id.fragment);
        f.changeBackgroundColor(Color.RED);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.changeBackgroundColor(Color.BLUE);
            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
