package com.example.ecossistema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantaAdapter extends RecyclerView.Adapter<PlantaAdapter.PlantaViewHolder> {

    private List<Planta> listaDePlantas;

    public PlantaAdapter(List<Planta> listaDePlantas) {
        this.listaDePlantas = listaDePlantas;
    }

    @Override
    public PlantaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemplanta, parent, false);

        return new PlantaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlantaViewHolder holder, int position) {
        Planta planta = listaDePlantas.get(position);

        holder.plantaNome.setText(planta.getNome());
        holder.plantaData.setText(planta.getDataPlantio());
        holder.plantaImage.setImageResource(planta.getImagem());
    }

    @Override
    public int getItemCount() {
        return listaDePlantas.size();
    }

    public static class PlantaViewHolder extends RecyclerView.ViewHolder {
        public TextView plantaNome, plantaData;
        public ImageView plantaImage;

        public PlantaViewHolder(View itemView) {
            super(itemView);
            plantaNome = itemView.findViewById(R.id.plantaNome);
            plantaData = itemView.findViewById(R.id.plantaData);
            plantaImage = itemView.findViewById(R.id.plantaImage);
        }
    }
}
