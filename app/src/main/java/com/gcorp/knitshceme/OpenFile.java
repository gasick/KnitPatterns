package com.gcorp.knitshceme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OpenFile extends AppCompatActivity implements View.OnClickListener{

    private static final int PICKFILE_RESULT_CODE = 1;
    String filePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        Button openFile = findViewById(R.id.openFileBTN);
        openFile.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intentFileOpen = new Intent(Intent.ACTION_GET_CONTENT);
        intentFileOpen.setType("file/*");
        startActivityForResult(intentFileOpen, PICKFILE_RESULT_CODE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Intent intentCreatePattern = new Intent(OpenFile.this, EditField.class);
                    intentCreatePattern.putExtra("action", "open");
                    intentCreatePattern.putExtra("uri", data.getData().getPath());
                    startActivity(intentCreatePattern);
                    Log.i("File path", filePath);
                }
                break;
        }
    }
}
