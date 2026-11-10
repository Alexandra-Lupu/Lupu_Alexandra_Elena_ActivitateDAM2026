package com.example.simularetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ArrayList<Album> listaAlbume=new ArrayList<>();

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


        TextView tv=findViewById(R.id.tv_lista_albume);
        launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if  (result.getResultCode() == RESULT_OK) {
                        Album album = (Album) result.getData().getSerializableExtra("obiect");
                        listaAlbume.add(album);
                        tv.setText(listaAlbume.toString());
                    }
                });

        Button btn=findViewById(R.id.btn_adauga_album_la_lista);
        btn.setOnClickListener(v->
        {
            Intent intent=new Intent(this, AdaugaAlbumActivity.class);
            launcher.launch(intent);
        });

        Button btn2=findViewById(R.id.btn_vezi_albumele);
        btn2.setOnClickListener(v->{
            Intent inte=new Intent(this,ListaAlbumeActivity.class);
            inte.putExtra("lista_albume",listaAlbume);
            startActivity(inte);
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("filtru","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("filtru","onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("filtru","onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("filtru","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("filtru","onResume");
    }
}