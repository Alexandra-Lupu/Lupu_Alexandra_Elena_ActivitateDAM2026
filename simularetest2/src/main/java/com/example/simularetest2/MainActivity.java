package com.example.simularetest2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    ArrayList<Telefon> lista=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ce se intoarce, lista
        launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode()==RESULT_OK)
                    {
                        Telefon tel= (Telefon)result.getData().getSerializableExtra("obiect");
                        lista.add(tel);
                    }
                });

        Button btn=findViewById(R.id.btn_adaugare_telefon);
        btn.setOnClickListener(v->
        {
            Intent intent=new Intent(this,InformatiiTelefon.class);
            launcher.launch(intent);
        });

        Button btn2=findViewById(R.id.btn_afiseaza_lista_tel);
        btn2.setOnClickListener(v->{
            Intent inte=new Intent(this, ListaTelefoane.class);
            inte.putExtra("lista", lista);
            startActivity(inte);
        });

    }
}