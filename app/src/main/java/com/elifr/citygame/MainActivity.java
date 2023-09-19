package com.elifr.citygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btnNormal, btnSureli, btnCikis;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormal = (Button) findViewById(R.id.btnNormalOyun);
        btnSureli = (Button) findViewById(R.id.btnSureliOyun);
        btnCikis = (Button) findViewById(R.id.btnCikis);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aktiviteyeGec("NormalOyun");
            }
        });

        btnSureli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aktiviteyeGec("SureliOyun");
            }
        });

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CikisYap();
            }
        });
    }

    public void aktiviteyeGec(String aktivite){
        if (aktivite.equals("NormalOyun"))
            intent = new Intent(this, NormalOyunActivity.class);
        else
            intent = new Intent(this, SureliOyunActivity.class);

        startActivity(intent);
    }

    public void CikisYap(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        CikisYap();
    }
}