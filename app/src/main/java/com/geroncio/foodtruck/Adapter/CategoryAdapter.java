package com.geroncio.foodtruck.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geroncio.foodtruck.Domain.CategoryDomain;
import com.geroncio.foodtruck.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    /**Criando uma lista com os parametros de FoodDomain*/
    ArrayList<CategoryDomain> categoryDomains;

    /**
     * Passando os parametros da lista em Home, para essa lista aqui*/
    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    /**
     * Chamado quando RecyclerView constroi na view o objeto java organizado segundo o layout disponibilazado
     * nesta secção do código*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat, parent, false);
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
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0 :{
                picUrl = "cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }

            case 1:{
                picUrl = "cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }

            case 2:{
                picUrl = "cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }

            case 3:{
                picUrl = "cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }

            case 4:{
                picUrl = "cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }

        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    /**
     * Esse metodo retorna o numero total de objetos na sua lista java, em preparação para a criação
     * da mesma quantidade de objetos na sua view, se tem 10 objetos na list, não pode sair 9 ou 11 na sua view
     * tem que sair 10*/

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    /**
     * Abordagem utilizada para guardar um conjunto de views que possam ser acedidas e reutilizadas quando necessário
     * Evitando o uso repetido de findViewById*/

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        /**
         * Setando o endereço de cada atributo variavel do objeto na list com o seu lugar na view*/
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
