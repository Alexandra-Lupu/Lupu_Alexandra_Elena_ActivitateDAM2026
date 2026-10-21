package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// punctul 5: copie a lui AdaugareLacatActivity.
// Codul e identic, se schimba doar macheta: activity_adaugare_lacat_ai (designul facut cu AI).
// Merge acelasi cod pentru ca id-urile din cele doua XML-uri sunt aceleasi.
public class AdaugareLacatAIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_lacat_ai);

        // gasim butonul Salveaza din XML dupa id
        Button salveazaBtn = findViewById(R.id.salveaza_BTN);
        salveazaBtn.setOnClickListener(v -> {
            // gasim controalele din formular dupa id-urile din XML
            EditText marcaEt = findViewById(R.id.marca_ET);
            Spinner culoareSp = findViewById(R.id.culoare_SP);
            EditText pretEt = findViewById(R.id.pret_ET);
            CheckBox rezistentApaCb = findViewById(R.id.rezistentApa_CB);
            RadioGroup tipRg = findViewById(R.id.tip_RG);
            Switch cuCifruSw = findViewById(R.id.cuCifru_SW);
            RatingBar nivelSigurantaRb = findViewById(R.id.nivelSiguranta_RB);

            // citim ce a completat utilizatorul
            String marca = marcaEt.getText().toString();               // EditText -text
            String culoare = culoareSp.getSelectedItem().toString();   // Spinner -elementul ales
            int pret = Integer.parseInt(pretEt.getText().toString());  // text - numar (pretul nu are voie sa fie gol)
            boolean rezistentApa = rezistentApaCb.isChecked();         // CheckBox -bifat sau nu
            RadioButton tipRb = findViewById(tipRg.getCheckedRadioButtonId()); // butonul radio ales
            String tip = tipRb.getText().toString();                   // textul lui
            boolean cuCifru = cuCifruSw.isChecked();                   // Switch - pornit sau nu
            float nivelSiguranta = nivelSigurantaRb.getRating();       // RatingBar - numarul de stele

            // cream obiectul cu constructorul clasei Lacat
            Lacat lacat = new Lacat(marca, culoare, rezistentApa, tip, pret, cuCifru, nivelSiguranta);

            // punem obiectul in Bundle (cheia "lacat"), iar Bundle-ul in Intent
            Bundle bundle = new Bundle();
            bundle.putSerializable("lacat", lacat);
            Intent intent = new Intent();
            intent.putExtras(bundle);

            //  trimitem rezultatul catre MainActivity si inchidem ecranul
            setResult(RESULT_OK, intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}