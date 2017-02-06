package com.project.erc.energyestimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserMenuActivity extends AppCompatActivity {

    ImageView updateEntry, createEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu_3);
        createEntry=(ImageView)findViewById(R.id.CreateEntry);
        updateEntry=(ImageView)findViewById(R.id.UpdateEntry);


        createEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCreateActivity =new Intent(UserMenuActivity.this, Trial.class);
                startActivity(startCreateActivity);
            }
        }
        );

    }
}
