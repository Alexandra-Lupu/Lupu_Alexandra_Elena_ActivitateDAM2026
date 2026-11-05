package com.example.laborator4;

import android.content.Intent;
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

    // Lab6: lista si adapterul devin ATRIBUTE ale clasei (inainte erau variabile locale in onCreate),
    // ca sa le putem folosi si in alte metode (onActivityResult, la modificare)
    private ArrayList<Lacat> lista = null;
    private LacatAdapter adapter = null;
    // Lab6, cerinta 3: pozitia lacatului pe care il modificam
    private int idModificat = 0;

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
        //ArrayList<Lacat> lista = (ArrayList<Lacat>) bundle.getSerializable("lista");   //lab5
        lista = bundle.getParcelableArrayList("lista");

        //gasesc listview ul
        ListView listView = findViewById(R.id.lista_LV);


        // ArrayAdapter<Lacat> adapter = new ArrayAdapter<>(this,
        //         android.R.layout.simple_list_item_1, lista);

        // adapterul nostru, cu layout-ul nostru de rand
        adapter = new LacatAdapter(lista, getApplicationContext(), R.layout.linie_listview);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lab5: afisam lacatul in Toast
                // Lacat lacatSelectat = lista.get(position);
                // Toast.makeText(ListViewActivity.this, lacatSelectat.toString(), Toast.LENGTH_SHORT).show();

                // Lab6,deschidem formularul pentru MODIFICAREA lacatului selectat
                idModificat = position;   // tinem minte pozitia, ca sa stim ce lacat inlocuim
                Intent intent = new Intent(ListViewActivity.this, AdaugareLacatActivity.class);
                intent.putExtra("lacat", lista.get(position));   // trimitem lacatul
                startActivityForResult(intent, 209);             // 209 = cod pentru modificare
            }
        });


        //lab5: apasare lunga si stergem elementul din lista si din listView
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

    // primim lacatul modificat din formular
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 209 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Lacat lacatModificat = bundle.getParcelable("lacat");

            //inlocuim lacatul de pe pozitia idModificat, nu adaugam unul nou
            lista.set(idModificat, lacatModificat);
            adapter.notifyDataSetChanged();   // actualizam ListView-ul
        }
    }
}
