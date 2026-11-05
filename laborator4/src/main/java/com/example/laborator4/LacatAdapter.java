package com.example.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

//lab5
// import android.widget.CheckBox;
// import android.widget.EditText;
// import android.widget.RadioGroup;
// import android.widget.RatingBar;
// import android.widget.Spinner;
// import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class LacatAdapter extends BaseAdapter {
    // cele 3 atribute: lista cu date, contextul si layout-ul unui rand
    private List<Lacat> lacate=null;
    private Context ctx;
    private int resursaLayout;

    public LacatAdapter(List<Lacat> lacate, Context ctx, int resursLayout) {
        this.lacate = lacate;
        this.ctx = ctx;
        this.resursaLayout = resursLayout;
    }

    // cate randuri are ListView-ul = cate lacate sunt in lista
    @Override
    public int getCount()
    {
        return lacate.size();
    }

    // lacatul de pe pozitia i
    @Override
    public Object getItem(int i){
        return lacate.get(i);
    }

    // id-ul unui rand, folosim pozitia
    @Override
    public long getItemId(int position) {
        // return 0;
        return position;
    }

    // construieste UN rand,este apelata pentru fiecare lacat din lista
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // 1. inflater-ul incarca layout-ul randului
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,parent,false);

        // 2. gasim TextView-ul din rand
        TextView lacatTV = v.findViewById(R.id.lacatTV);

        // 3. luam lacatul de pe pozitia i
        Lacat lacat = (Lacat) getItem(i);

        // 4. punem informatiile in TextView, cu getterii din clasa Lacat
        lacatTV.setText(lacat.getMarca() + "\n"
                + "Culoare: " + lacat.getCuloare() + "\n"
                + "Tip: " + lacat.getTip() + "\n"
                + "Pret: " + lacat.getPret() + " lei");

        return v;  //afisez randul
    }
}
