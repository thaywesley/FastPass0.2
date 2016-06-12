package com.example.ivanildo.fastpass02.facebookloginsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ivanildo.fastpass02.ListaCompras;
import com.example.ivanildo.fastpass02.ListaEmpresa;
import com.example.ivanildo.fastpass02.R;
import com.example.ivanildo.fastpass02.Resultado_Final;

public class perfil extends AppCompatActivity implements View.OnClickListener {

    Button btClie,btEmpre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btClie=(Button)findViewById(R.id.ButtonCliente);
        btEmpre=(Button)findViewById(R.id.ButtonEmpresa);
        btClie.setOnClickListener(this);
        btEmpre.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ButtonCliente){
            Intent homeIntent = new Intent(perfil.this, ListaCompras.class);
            startActivity(homeIntent);
        }
        if(v.getId()==R.id.ButtonEmpresa){
            Intent homeIntent = new Intent(perfil.this, ListaEmpresa.class);
            startActivity(homeIntent);
        }
    }
}
