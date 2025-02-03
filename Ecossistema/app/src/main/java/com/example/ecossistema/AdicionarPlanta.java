package com.example.ecossistema;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdicionarPlanta extends Activity {

    private static final String PREFS_NAME = "DiarioPlantasPrefs";
    private static final String PLANTAS_KEY = "ListaDePlantas";

    private EditText campoNome, campoData;
    private Spinner campoTipo;
    private ImageView categoryImage;
    private Button buttonSalvar;

    private List<Planta> listaDePlantas;

    private int imagemSelecionadaResId = 0; // ID do recurso da imagem selecionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_plantas);

        // Inicializando os componentes do layout
        campoNome = findViewById(R.id.campoNome);
        campoData = findViewById(R.id.campoData);
        campoTipo = findViewById(R.id.campoTipo);
        categoryImage = findViewById(R.id.categoryImage);
        buttonSalvar = findViewById(R.id.buttonsalvar);

        // Carregar a lista de plantas existente
        listaDePlantas = carregarPlantas();

        // Configurar o Spinner com os tipos de plantas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipo_plantas,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoTipo.setAdapter(adapter);

        // Configurar o evento de seleção do Spinner
        campoTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoSelecionado = parent.getItemAtPosition(position).toString();

                // Alterar a imagem de acordo com o tipo selecionado
                switch (tipoSelecionado) {
                    case "-------":
                        categoryImage.setImageResource(android.R.color.transparent);
                        imagemSelecionadaResId = android.R.color.transparent;
                        break;
                    case "Hortaliças":
                        categoryImage.setImageResource(R.drawable.img_hortalica);
                        imagemSelecionadaResId = R.drawable.img_hortalica;
                        break;
                    case "Ervas":
                        categoryImage.setImageResource(R.drawable.img_ervas);
                        imagemSelecionadaResId = R.drawable.img_ervas;
                        break;
                    case "Flores":
                        categoryImage.setImageResource(R.drawable.img_flores);
                        imagemSelecionadaResId = R.drawable.img_flores;
                        break;
                    case "Suculentas":
                        categoryImage.setImageResource(R.drawable.img_suculenta);
                        imagemSelecionadaResId = R.drawable.img_suculenta;
                        break;
                    default:
                        categoryImage.setImageResource(android.R.color.transparent);
                        imagemSelecionadaResId = android.R.color.transparent;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não faz nada se nada for selecionado
            }
        });

        campoData.setOnClickListener(v -> {
            // Obter a data atual
            Calendar calendar = Calendar.getInstance();
            int ano = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            // Criar e exibir o DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        // Formatar a data selecionada (ex.: 12/12/2024)
                        String dataSelecionada = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        campoData.setText(dataSelecionada);
                    },
                    ano, mes, dia
            );
            datePickerDialog.show();
        });

        buttonSalvar.setOnClickListener(v -> salvarPlanta());
    }

    private void salvarPlanta() {
        String nome = campoNome.getText().toString();
        String data = campoData.getText().toString();
        String tipo = campoTipo.getSelectedItem().toString();

        if (nome.isEmpty() || data.isEmpty() || tipo.equals("-------")) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Planta novaPlanta = new Planta(nome, data, tipo, imagemSelecionadaResId);
        listaDePlantas.add(novaPlanta);
        salvarPlantas();

        Toast.makeText(this, "Planta salva com sucesso!", Toast.LENGTH_SHORT).show();
        finish(); // Voltar para a tela anterior
    }

    private void salvarPlantas() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(listaDePlantas);

        editor.putString(PLANTAS_KEY, json);
        editor.apply();
    }

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
