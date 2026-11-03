package com.example.pregatiretest1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_activ3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //C2
        // 1. gasesc controalele după id
        EditText etNume = findViewById(R.id.et_nume);
        Button btnSalut = findViewById(R.id.btn_apasa);

        // 2. aleg ce se va intampla click
        btnSalut.setOnClickListener(v ->
        {
            // 3. citeste textul si il afisez
            String nume = etNume.getText().toString();
            Toast.makeText(this, "Salut, " + nume + "!", Toast.LENGTH_SHORT).show();
        });
        // /////////////////


        //C3
        EditText etNr1=findViewById(R.id.et_nr1);
        EditText etNr2=findViewById(R.id.et_nr2);
        Button btnCalcul=findViewById(R.id.btn_aduna);
        TextView rezultat=findViewById(R.id.tv_rezultat);

        btnCalcul.setOnClickListener(v->{
            String nr1 = etNr1.getText().toString();
            String nr2=etNr2.getText().toString();

            if (nr1.isEmpty()) {
                Toast.makeText(this, "Completeaza primul numar", Toast.LENGTH_SHORT).show();
                return;      // iese din toata functia
            }
            if(nr2.isEmpty())
            {
                Toast.makeText(this,"Completeaza al doilea numar",Toast.LENGTH_SHORT).show();
                return;
            }
            int rez=Integer.parseInt(nr1)+Integer.parseInt(nr2);
            rezultat.setText("Rezultat: " + rez);
        });
        // ////////////
    }

    //C1
    //CTRL+O
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("exercitiu", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("exercitiu", "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("exercitiu", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("exercitiu", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("exercitiu", "onResume");
    }
    // ////////////////


}