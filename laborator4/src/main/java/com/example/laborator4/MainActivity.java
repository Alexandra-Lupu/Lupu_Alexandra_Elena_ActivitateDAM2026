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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Lista pt lab5
    private ArrayList<Lacat> listaLacate = new ArrayList<>();

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



        // (punctul 5): al doilea buton deschide formularul facut cu AI.
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


        //Buton afisare lista cu a treia metoda
        //Trimit lista in a treia activitate
        Button afisareBtn = findViewById(R.id.button_afisare_lista);

        afisareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", listaLacate);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    //Android primeste obiectul trimis prin bundle din a doua activitate si il pune in lista
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // verificam: raspunsul e de la formular
            if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                Lacat lacat = (Lacat) bundle.getSerializable("lacat");

                // adaugam lacatul primit in lista
                listaLacate.add(lacat);

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