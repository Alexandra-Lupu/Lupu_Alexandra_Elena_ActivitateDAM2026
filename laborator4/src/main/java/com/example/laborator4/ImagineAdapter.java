package com.example.laborator4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private int resursaLayout;
    private List<ImaginiDomeniu> lista;

    public ImagineAdapter(Context ctx, int resursaLayout, List<ImaginiDomeniu> lista) {
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
        this.lista = lista;
    }

    // cate randuri are listviewu
    @Override
    public int getCount() {
        return lista.size();
    }

    // elementul de pe pozitia i
    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // construiesc randul
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView textTV = v.findViewById(R.id.textTV);

        ImaginiDomeniu element = (ImaginiDomeniu) getItem(i);

        // pun info- imag si textul
        imagineIV.setImageBitmap(element.getImagine());
        textTV.setText(element.getTextAfisat());

        return v;
    }
}