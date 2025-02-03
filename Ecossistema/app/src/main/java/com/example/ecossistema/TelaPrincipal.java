package com.example.ecossistema;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial); // Associa o layout da tela inicial

        // Referencia os botões do layout
        Button buttonAdicionarPlantas = findViewById(R.id.button1);
        Button buttonVerMinhasPlantas = findViewById(R.id.button2);
        Button buttonDicasDeCuidado = findViewById(R.id.button3);

        // Configura o botão "Adicionar Plantas" para navegar para a tela de adicionar plantas
        buttonAdicionarPlantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrincipal.this, AdicionarPlanta.class);
                startActivity(intent);
            }

        });
        buttonVerMinhasPlantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrincipal.this, ListaPlantas.class);
                startActivity(intent);
            }
        });

    }
}

