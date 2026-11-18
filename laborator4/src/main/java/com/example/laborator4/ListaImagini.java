package com.example.laborator4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImagini extends AppCompatActivity {
    private List<ImaginiDomeniu> lista = new ArrayList<>();
    //in lista am imagine,text,link, practic randul
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);

        //lista cu adresele pozelor
        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://cdn.dedeman.ro/media/catalog/product/6/0/6063629_lacat_cu_discuri_4_chei_50_mm_nichel.jpg");
        linkuriImagini.add("https://media.hornbach.ro/hb/packshot/as.47353978.jpg");
        linkuriImagini.add("https://www.econstrukt.ro/userfiles/5d8783b2-75bd-49f2-8e71-eff6975d7234/products/11317173_big.jpg");
        linkuriImagini.add("https://navigroup.ro/1465-medium_default/lacat-blindat-de-inalta-securitate-cisa-28055-dimensiune-55-mm-cu-2-chei-patentate-astral-tekno.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains/safemag.ro/files/product/original/Lacat-aluminiu-Alutitan-DUO-770-30-SB-inchidere-cheie-www.safemag.ro-8.jpg-116-3241.jpg");

        //lista cu pozele, dupa ce sunt descaracte imaginile ajung aici
        List<Bitmap> imagini = new ArrayList<>();


        //firele de exec
        Executor executor= Executors.newSingleThreadExecutor() ;
        Handler handler= new Handler (Looper.myLooper ());

        //firu paralel
        executor.execute(new Runnable ()
                         {
                             @Override
                             public void run() {
                                 for(String link: linkuriImagini) {
                                     HttpURLConnection con = null;

                                     try{
                                         URL url = new URL(link);
                                         con = (HttpURLConnection)url.openConnection();
                                         InputStream is= con.getInputStream();
                                         imagini. add(BitmapFactory.decodeStream(is));
                                     } catch (Exception e) {
                                         throw new RuntimeException(e);
                                     }
                                 }
                                 handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         // lista de obiecte
                                         lista = new ArrayList<>();
                                         lista.add(new ImaginiDomeniu("Lacat cu cifru", imagini.get(0), "https://www.dedeman.ro/ro?srsltid=AYCX9vJwc7iJPWrICI3Q26RU7fAR08S_XrCqL_4VliGz0pWBwbdxqCiJ"));
                                         lista.add(new ImaginiDomeniu("Lacat de bicicleta", imagini.get(1), "https://www.hornbach.ro/"));
                                         lista.add(new ImaginiDomeniu("Lacat clasic", imagini.get(2), "https://www.hornbach.ro/"));
                                         lista.add(new ImaginiDomeniu("Lacat inteligent", imagini.get(3), "https://altex.ro/?cq_src=google&cq_medium=cpc&cq_cmp=23826705784&cq_con=193185020581&cq_term=altex&cq_med=&cq_plac=&cq_net=g&cq_pos=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=23826705784&utm_content=193185020581&utm_term=altex&gad_source=1&gad_campaignid=23826705784&gclid=Cj0KCQiAtvXXBhC2ARIsAJc8SUT6hnc5e7OfALDMkdZEvWx3goryQjoi1060orPZvJcQatfJXLKXv8IaAgHREALw_wcB"));
                                         lista.add(new ImaginiDomeniu("Lacat cu amprenta", imagini.get(4), "https://altex.ro/?cq_src=google&cq_medium=cpc&cq_cmp=23826705784&cq_con=193185020581&cq_term=altex&cq_med=&cq_plac=&cq_net=g&cq_pos=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=23826705784&utm_content=193185020581&utm_term=altex&gad_source=1&gad_campaignid=23826705784&gclid=Cj0KCQiAtvXXBhC2ARIsAJc8SUT6hnc5e7OfALDMkdZEvWx3goryQjoi1060orPZvJcQatfJXLKXv8IaAgHREALw_wcB"));

                                         // adapterul
                                         ListView lv = findViewById(R.id.lv_lista_imagini);
                                         ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(),
                                                 R.layout.imagine_layout, lista);
                                         lv.setAdapter(adapter);

                                         // click pe un item -> deschidem WebViewActivity cu link-ul obiectului
                                         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                 Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                                                 it.putExtra("link", lista.get(i).getLink());   // trimitem DOAR link-ul
                                                 startActivity(it);
                                             }
                                         });
                                     }
                                 });
                             }

                         });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


}
}