package com.example.ivanildo.fastpass02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ivanildo on 12/06/2016.
 */

    public class AdapterListView extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<ItemListView> itens;

        public AdapterListView(Context context, ArrayList<ItemListView> itens) {
            //Itens que preencheram o listview
            this.itens = itens;
            //responsavel por pegar o Layout do item.
            mInflater = LayoutInflater.from(context);
        }

        /**
         * Retorna a quantidade de itens
         *
         * @return
         */
        public int getCount() {
            return itens.size();
        }

        /**
         * Retorna o item de acordo com a posicao dele na tela.
         *
         * @param position
         * @return
         */
        public ItemListView getItem(int position) {
            return itens.get(position);
        }

        /**
         * Sem implementação
         *
         * @param position
         * @return
         */
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View view, ViewGroup parent) {
            ItemSuporte itemHolder;
            //se a view estiver nula (nunca criada), inflamos o layout nela.
            if (view == null) {
                //infla o layout para podermos pegar as views
                view = mInflater.inflate(R.layout.item_list, null);

                //cria um item de suporte para não precisarmos sempre
                //inflar as mesmas informacoes
                itemHolder = new ItemSuporte();
                itemHolder.txtTitle = ((TextView) view.findViewById(R.id.textDescr));
                itemHolder.txtpreco = ((TextView) view.findViewById(R.id.textPreco));

                itemHolder.txtTitle.setTextSize(18);
                itemHolder.txtpreco.setTextSize(18);


                //define os itens na view;
                view.setTag(itemHolder);
            } else {
                //se a view já existe pega os itens.
                itemHolder = (ItemSuporte) view.getTag();
            }

            //pega os dados da lista
            //e define os valores nos itens.
            ItemListView item = itens.get(position);
            itemHolder.txtTitle.setText(item.getTexto());
            itemHolder.txtpreco.setText(item.getPreco());

            //retorna a view com as informações
            return view;
        }

        /**
         * Classe de suporte para os itens do layout.
         */
        private class ItemSuporte {

            TextView txtTitle,txtpreco;
        }

    }

