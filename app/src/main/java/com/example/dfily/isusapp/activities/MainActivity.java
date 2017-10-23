package com.example.dfily.isusapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dfily.isusapp.R;
import com.example.dfily.isusapp.location.LocationActivityExample;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = (TextView) findViewById(R.id.counterTextView);

        // Вешаем на кнопку слушатель непосредственно в коде
        Button mbutton = (Button) findViewById(R.id.mbutton);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterDecrement();
            }
        });

        counter = 0;
    }

    // Слушатель вешается через XML
    public void counterIncrement(View view) {
        counter++;
        counterTextView.setText(String.valueOf(counter));
    }

    public void counterDecrement() {
        counter--;
        counterTextView.setText(String.valueOf(counter));
    }

    public void openActivity(View view) {
        //Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show();
        //Log.d("mylogs", String.valueOf(counter));

        Intent intent = new Intent(this, LocationActivityExample.class);
        startActivity(intent);
    }
}
