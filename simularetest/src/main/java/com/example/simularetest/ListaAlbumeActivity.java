package com.example.simularetest;

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

public class ListaAlbumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_albume);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv=findViewById(R.id.lv_albume);
        Intent intent=getIntent();
        ArrayList<Album> lista=(ArrayList<Album>)intent.getSerializableExtra("lista_albume");

        ArrayAdapter<Album> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position,id)->{
            Album selectat=lista.get(position);
            Toast.makeText(this,selectat.toString(),Toast.LENGTH_SHORT).show();
        });

        lv.setOnItemLongClickListener((parent,view,position,id)->{
            lista.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });

    }

}