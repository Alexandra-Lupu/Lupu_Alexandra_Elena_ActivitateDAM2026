package com.example.pregatiretest1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        Bundle bundle = getIntent().getExtras();
        String mesaj = bundle.getString("mesaj");
        int nr1 = bundle.getInt("nr1");
        int nr2 = bundle.getInt("nr2");
        Toast.makeText(this, mesaj + " " + nr1 + " " + nr2, Toast.LENGTH_LONG).show();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_afisare_lista_laptopuri), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btn = findViewById(R.id.btn_Apasa);
        btn.setOnClickListener(v -> {
            Intent raspuns = new Intent();
            Bundle bundle2 = new Bundle();
            bundle2.putString("mesaj", "Suma numerelor este: ");
            bundle2.putInt("suma", nr1 + nr2);
            raspuns.putExtras(bundle2);

            setResult(RESULT_OK, raspuns);  //inchide thirdActiv si merge automat in SecondActiv
            finish();
        });

    }
}