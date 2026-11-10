package com.example.simularetest2;

import android.content.Intent;
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

public class ListaTelefoane extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_telefoane);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv=findViewById(R.id.lv_lista_telefon);
        ArrayList<Telefon> lista=(ArrayList<Telefon>)getIntent().getSerializableExtra("lista");
        ArrayAdapter<Telefon> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter); //lista apare pe ecran

        lv.setOnItemClickListener((parent,view,position,id)->{
            Telefon select=lista.get(position);
            Toast.makeText(this,select.toString(),Toast.LENGTH_SHORT).show();
        });

        lv.setOnItemLongClickListener((parent,view,position,id)->{
            Telefon t=lista.get(position);
            t.setMemorie_interna((int)(t.getMemorie_interna()+(float)56/100*t.getMemorie_interna()));
            adapter.notifyDataSetChanged();
            return true;
        });


    }
}