package com.example.pregatiretest1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Afisare_Lista_Laptopuri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_lista_laptopuri);

        ArrayList<Laptop> lista = (ArrayList<Laptop>) getIntent().getSerializableExtra("lista");
        ListView lv = findViewById(R.id.lv_lista_laptopuri);

        // dapteru
        ArrayAdapter<Laptop> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);


        //click scurt
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Laptop selectat = lista.get(position);
            Toast.makeText(this, selectat.toString(), Toast.LENGTH_LONG).show();
        });


        //click lung
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);             // 1. îl scoți din ArrayList
            adapter.notifyDataSetChanged();     // 2. îi spui ListView-ului să se redeseneze
            Toast.makeText(this, "Laptop șters", Toast.LENGTH_SHORT).show();
            return true;                        // 3. obligatoriu
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}