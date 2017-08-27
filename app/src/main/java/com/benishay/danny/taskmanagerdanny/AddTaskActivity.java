package com.benishay.danny.taskmanagerdanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddTaskActivity extends AppCompatActivity {
    private ImageButton ibtnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ibtnMap = (ImageButton) findViewById(R.id.ibtnMap);
        ibtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
