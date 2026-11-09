package com.example.pregatiretest1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.TuplesKt;

public class AdaugaLaptop_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_laptop_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    EditText marca=findViewById(R.id.et_marca_laptop);
    EditText pret=findViewById(R.id.et_pret_laptop);
    EditText nuclee=findViewById(R.id.et_nr_nuclee);
    Switch touch=findViewById(R.id.switch_touchscreen);
    Button btn=findViewById(R.id.btn_trimite_laptop);

    btn.setOnClickListener(v->{
        String m=marca.getText().toString();
        float p=Float.parseFloat(pret.getText().toString());
        int nr=Integer.parseInt(nuclee.getText().toString());
        boolean t=touch.isChecked();

        //Toast.makeText(this, "Marca: "+m+" pret: "+p+" numar nuclee: "+nr+" touchscreen: "+t,Toast.LENGTH_SHORT).show();

        Laptop laptop = new Laptop(m,p,nr,t);
        Toast.makeText(this, laptop.toString(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("laptop", laptop);
        setResult(RESULT_OK, intent);
        finish();
    });
}
}