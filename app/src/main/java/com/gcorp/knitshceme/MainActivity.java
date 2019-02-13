package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Объявляем данные которые мы будем собирать
    EditText rows;
    EditText columns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button patternCreateButton  = findViewById(R.id.patternCreateButton);
        Button openFileButton = findViewById(R.id.patternOpenButton);

        //Собираем данные которые передадим в новое окно
        rows = findViewById(R.id.numOfRows);
        columns = findViewById(R.id.numOfColunms);

        patternCreateButton.setOnClickListener(this);
        openFileButton.setOnClickListener(this);


    }

    public void onClick(View view){
        switch(view.getId())
        {
            case R.id.patternCreateButton:
                Intent intentCreatePattern = new Intent(MainActivity.this, EditField_Activity.class);
                //Отправляем данные котоыре мы собрали на MainActivity
                //передаем значения как string
                intentCreatePattern.putExtra("action", "create");
                intentCreatePattern.putExtra("rows", rows.getText().toString() );
                intentCreatePattern.putExtra("columns", columns.getText().toString());
                startActivity(intentCreatePattern);
                break;
            case R.id.patternOpenButton:
                Intent intentOpenPattern = new Intent(MainActivity.this, OpenFile_Activity.class);
                startActivity(intentOpenPattern);
                break;

        }



    }
}
