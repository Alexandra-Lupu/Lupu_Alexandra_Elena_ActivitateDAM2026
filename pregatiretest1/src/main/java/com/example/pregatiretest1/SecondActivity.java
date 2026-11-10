package com.example.pregatiretest1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);


        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Bundle b = result.getData().getExtras();
                        String mesaj = b.getString("mesaj");
                        int suma = b.getInt("suma");
                        Toast.makeText(this, mesaj + suma, Toast.LENGTH_LONG).show();
                    }
                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_afisare_lista_laptopuri), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //C4
        Button btnDeschide = findViewById(R.id.btn_spre_activ3);
        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

            //C5
            Bundle bundle = new Bundle();
            bundle.putString("mesaj", "Numerele sunt: ");
            bundle.putInt("nr1", 7);
            bundle.putInt("nr2", 5);
            intent.putExtras(bundle);

            //startActivity(intent);
            launcher.launch(intent); //stiu ca astept un rasp
        });
    }
}