package com.geroncio.foodtruck.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geroncio.foodtruck.Domain.FoodDomain;
import com.geroncio.foodtruck.Helper.ManagementCard;
import com.geroncio.foodtruck.Interface.ChangeNumberItemsListener;
import com.geroncio.foodtruck.R;
import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCard managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CardListAdapter(ArrayList<FoodDomain> FoodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {

        this.foodDomains = FoodDomains;
        managementCart = new ManagementCard(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    /**
     * Chamado quando RecyclerView constroi na view o objeto java organizado segundo o layout disponibilazado
     * nesta secção do código*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        /**
         * A classe LayoutInflater é usada para instanciar os atributos ou conteudos do seu objeto java list, no
         * layout XML customizado.
         */
        return new ViewHolder(inflate);
    }

    /**
     * Chamdo quando RecyclerView para exibir os dados do objeto java list, na posição escpecificda da view.*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCard() * foodDomains.get(position).getFee()) * 100.0) / 100.0));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCard()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.MinusNumerFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }

    /**
     * Esse metodo retorna o numero total de objetos na sua lista java, em preparação para a criação
     * da mesma quantidade de objetos na sua view, se tem 10 objetos na list, não pode sair 9 ou 11 na sua view
     * tem que sair 10*/
    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    /**
     * Abordagem utilizada para guardar um conjunto de views que possam ser acedidas e reutilizadas quando necessário
     * Evitando o uso repetido de findViewById*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        /**
         * Setando o endereço de cada atributo variavel do objeto na list com o seu lugar na view*/
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCard);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
