package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

        Button adaugaBtn = findViewById(R.id.adauga_BTN);
        adaugaBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugareLacatActivity.class);
            // startActivityForResult deschide ecranul SI asteapta un raspuns de la el
            // 200 = cod ales de noi, ca sa stim de la ce ecran vine raspunsul
            startActivityForResult(intent, 200);
        });

        // NOU (punctul 5): al doilea buton deschide formularul facut cu AI.
        // Folosim tot codul 200, pentru ca raspunsul e acelasi (un lacat in Bundle),
        // deci onActivityResult il afiseaza la fel in Toast
        Button adaugaAIBtn = findViewById(R.id.adaugaAI_BTN);
        adaugaAIBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugareLacatAIActivity.class);
            startActivityForResult(intent, 200);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // NOU: Android apeleaza singur aceasta metoda cand AdaugareLacatActivity se inchide cu finish()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // verificam: raspunsul e de la formular (200), s-a apasat Salveaza (RESULT_OK) si avem date
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            // scoatem Bundle-ul din Intent, apoi lacatul dupa cheia "lacat"
            Bundle bundle = data.getExtras();
            Lacat lacat = (Lacat) bundle.getSerializable("lacat");
            // afisam lacatul intr-un Toast
            Toast.makeText(this, lacat.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void extrage_informatii(View view) {
        EditText et=findViewById(R.id.nume_ET);
        CheckBox cb=findViewById(R.id.eStudent_CB);

        String nume=et.getText().toString();
        Boolean eStudent=cb.isChecked();
    }
}