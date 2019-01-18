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
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Собираем данные которые передадим в новое окно
        rows = findViewById(R.id.numOfRows);
        columns = findViewById(R.id.numOfColunms);
        btnSubmit = findViewById(R.id.patternCreateButton);
        btnSubmit.setOnClickListener(this);


    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, EditField.class);
        //Отправляем данные котоыре мы собрали на MainActivity
        //передаем значения как string
        intent.putExtra("rows", rows.getText().toString() );
        intent.putExtra("columns", columns.getText().toString());
        startActivity(intent);



    }
}
