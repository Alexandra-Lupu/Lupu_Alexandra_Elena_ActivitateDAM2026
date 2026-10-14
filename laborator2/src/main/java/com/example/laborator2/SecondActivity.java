package com.example.laborator2;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.btnOpenThird);
        btn.setOnClickListener(v -> deschideActivitate(v));
    }

    public void deschideActivitate(View view) {
        Intent it = new Intent(this, ThirdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Mesaj din prima activitate");
        bundle.putInt("numar1", 5);
        bundle.putInt("numar2", 7);
        it.putExtras(bundle);
        //startActivity(it);
        startActivityForResult(it,70);

    }

    //Can activ revine in varful stivei se apeleaza metoda asta, call back
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==70){
            if(resultCode==RESULT_OK){
                String mesaj=data.getStringExtra("mesaj");
                int suma=data.getIntExtra("suma",0);
                StringBuilder builder=new StringBuilder(mesaj);
                builder.append(" ").append(suma);
                Toast.makeText(this,builder.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}