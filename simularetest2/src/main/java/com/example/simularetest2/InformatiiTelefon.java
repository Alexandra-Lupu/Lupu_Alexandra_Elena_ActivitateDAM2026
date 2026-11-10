package com.example.simularetest2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformatiiTelefon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informatii_telefon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText model=findViewById(R.id.et_model);
        Spinner ram=findViewById(R.id.sp_rami);
        RadioGroup mem=findViewById(R.id.rg_memorie_interna);
        CheckBox amprenta=findViewById(R.id.cb_amprenta);
        Button btn=findViewById(R.id.btn_salvare_telefon);
        btn.setOnClickListener(v->{
            String m=model.getText().toString();
            int r=Integer.parseInt(ram.getSelectedItem().toString());
            int memorie;
            if (mem.getCheckedRadioButtonId()==R.id.rb_mem1)
            {
                memorie=126;
            }
            else memorie=228;
            boolean am=amprenta.isChecked();

            Telefon t=new Telefon(m,r,memorie,am);

            Intent intent=new Intent();
            Bundle b=new Bundle();
            b.putSerializable("obiect",t);
            intent.putExtras(b);
            setResult(RESULT_OK,intent);
            finish();

        });

    }
}