package com.geroncio.foodtruck.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geroncio.foodtruck.Domain.FoodDomain;
import com.geroncio.foodtruck.R;
import com.geroncio.foodtruck.ShowDetailsActivity;

import java.util.ArrayList;

/**
 * Assim como o arquiteto pega os objetos de decoração e monta o ambiente de uma maneira customizada e unica,
 * em meio as N possiblidades;
 *
 * A classe Adapter serve para pegar os objetos da arraylist que ja foi criada e organiza-los na sua view*/

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    /**Criando uma lista com os parametros de FoodDomain*/
    ArrayList<FoodDomain> foodDomain;


    /**
     * Passando os parametros da lista em Home, para essa lista aqui*/
    public PopularAdapter(ArrayList<FoodDomain> foodDomain) {
        this.foodDomain = foodDomain;
    }


    /**
     * Chamado quando RecyclerView constroi na view o objeto java organizado segundo o layout disponibilazado
     * nesta secção do código*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);

        /**
         * A classe LayoutInflater é usada para instanciar os atributos ou conteudos do seu objeto java list, no
         * layout XML customizado.
         */
    }

    /**
     * Chamdo quando RecyclerView para exibir os dados do objeto java list, na posição escpecificda da view.*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomain.get(position).getTitle());

        holder.fee.setText(String.valueOf(foodDomain.get(position).getFee()));


        int drawableResourceId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(
                        foodDomain.get(position).getPic(),
                        "drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                /**putExtra vai enviar no ato de ir para a atividade showdetails
                 * a posição do objeto na list FoodDomain para na outra activity recuperarmos o objeto*/
                intent.putExtra("object", foodDomain.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Esse metodo retorna o numero total de objetos na sua lista java, em preparação para a criação
     * da mesma quantidade de objetos na sua view, se tem 10 objetos na list, não pode sair 9 ou 11 na sua view
     * tem que sair 10*/
    @Override
    public int getItemCount() {
        return foodDomain.size();
    }

    /**
     * Abordagem utilizada para guardar um conjunto de views que possam ser acedidas e reutilizadas quando necessário
     * Evitando o uso repetido de findViewById*/
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, fee;
        ImageView pic;
        TextView addBtn;

        /**
         * Setando o endereço de cada atributo variavel do objeto na list com o seu lugar na view*/
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tittle);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.picpic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
