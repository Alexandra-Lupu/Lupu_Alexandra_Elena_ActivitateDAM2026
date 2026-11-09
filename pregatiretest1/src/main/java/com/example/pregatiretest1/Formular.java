package com.example.pregatiretest1;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class Formular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CheckBox cb=findViewById(R.id.cB_accept_termenii);
        RadioGroup rg=findViewById(R.id.rG_grup);
        Spinner s=findViewById(R.id.spinner);
        RatingBar stele=findViewById(R.id.ratingBar_servicii);
        Switch switchh=findViewById(R.id.switch1);
        Button btn=findViewById(R.id.btn_trimiteForm);

        TextView tv=findViewById(R.id.tv_rezultat_form);

        btn.setOnClickListener(v->{
            boolean accept = cb.isChecked();                         // CheckBox → bifat sau nu

            String regulament;
            if (rg.getCheckedRadioButtonId() == R.id.rB1) {      // RadioGroup → care buton e bifat
                regulament = "da";
            } else {
                regulament = "nu";
            }

            String culoare = s.getSelectedItem().toString();       // Spinner → textul ales
            float nota = stele.getRating();                           // RatingBar → câte stele (ex. 4.0)
            boolean recomanda = switchh.isChecked();                 // Switch → pornit sau nu

            Toast.makeText(this,
                    "Acceptare termeni: "+accept
                        +" Citire regulament: "+regulament
                        +" Culoare: "+culoare
                        +" Apreciere servicii: "+nota
                        +" Oferire recomandare: "+recomanda,
                        Toast.LENGTH_SHORT).show();

            tv.setText("Acceptare termeni: "+accept
                    +" Citire regulament: "+regulament
                    +" Culoare: "+culoare
                    +" Apreciere servicii: "+nota
                    +" Oferire recomandare: "+recomanda);

        });
    }
}