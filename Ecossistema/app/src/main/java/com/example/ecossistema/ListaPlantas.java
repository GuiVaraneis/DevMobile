package com.example.ecossistema;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListaPlantas extends Activity {

    private static final String PREFS_NAME = "DiarioPlantasPrefs";
    private static final String PLANTAS_KEY = "ListaDePlantas";

    private RecyclerView recyclerView;
    private PlantaAdapter plantaAdapter;
    private List<Planta> listaDePlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaplantas); // Layout para listar plantas

        // Inicializando RecyclerView e configurando o LayoutManager
        recyclerView = findViewById(R.id.recyclerViewPlantas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Carregar as plantas salvas
        listaDePlantas = carregarPlantas();

        if (listaDePlantas.isEmpty()) {
            Toast.makeText(this, "Nenhuma planta salva", Toast.LENGTH_SHORT).show();
        }

        // Configurar o Adapter para o RecyclerView
        plantaAdapter = new PlantaAdapter(listaDePlantas);
        recyclerView.setAdapter(plantaAdapter);
    }

    // Carregar as plantas salvas no SharedPreferences
    private List<Planta> carregarPlantas() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = preferences.getString(PLANTAS_KEY, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Planta>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }
}
