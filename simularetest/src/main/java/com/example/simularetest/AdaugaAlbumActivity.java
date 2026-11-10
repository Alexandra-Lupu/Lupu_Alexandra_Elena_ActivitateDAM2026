package com.example.simularetest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText titlu=findViewById(R.id.et_titlu_album);
        EditText an=findViewById(R.id.et_an_album);
        EditText pret=findViewById(R.id.et_pret_album);
        Spinner gen=findViewById(R.id.sp_gen_album);
        Switch vinil=findViewById(R.id.sw_vinil);
        RatingBar rating=findViewById(R.id.rb_rating_album);
        Button btn=findViewById(R.id.btn_salveaza_inf_album);

        btn.setOnClickListener(v->{
            String t=titlu.getText().toString();
            if (t.isEmpty())
            {
                Toast.makeText(this,"Completeaza titlul",Toast.LENGTH_SHORT).show();
                return;
            }
            int ann=Integer.parseInt(an.getText().toString());
            float p=Float.parseFloat(pret.getText().toString());
            String g=gen.getSelectedItem().toString();
            boolean vini=vinil.isChecked();
            float rb=rating.getRating();

            Album obj=new Album(t,ann,p,g,vini,rb);

            Intent intent=new Intent();
            //trimit direct in intent obiectul
            intent.putExtra("obiect",obj);

            //trimit prin bundle obiectul
//            Bundle b = new Bundle();
//            b.putSerializable("obiect", album);
//            intent.putExtras(b);


            setResult(RESULT_OK,intent);
            finish();


        });

    }
}