package com.example.laborator4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Fac adapteru

        //primesc lista
        Bundle bundle = getIntent().getExtras();
        ArrayList<Lacat> lista = (ArrayList<Lacat>) bundle.getSerializable("lista");

        //gasesc listview ul
        ListView listView = findViewById(R.id.lista_LV);

        //adapter
        ArrayAdapter<Lacat> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lista);

        listView.setAdapter(adapter);


        //cerinta 3 lab5: click pe un element din lista si il afisam in toast
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lacat lacatSelectat = lista.get(position);
                Toast.makeText(ListViewActivity.this, lacatSelectat.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        //cerinta 4 lab5: apasare lunga si stergem elementul din lista si din listView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lista.remove(position);              // stergem din lista de obiecte
                adapter.notifyDataSetChanged();      // actualizam listview ul
                Toast.makeText(ListViewActivity.this, "Lacat sters", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}