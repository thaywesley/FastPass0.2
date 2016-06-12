package com.example.ivanildo.fastpass02;

/**
 * Created by Ivanildo on 12/06/2016.
 */
public class ItemListView {

        private String texto;
        private String preco;

        public ItemListView() {
        }

        public ItemListView(String texto, String preco) {
            this.texto = texto;
            this.preco = preco;
        }

        public String getPreco() {
            return preco;
        }

        public void setPreco(String preco) {
            this.preco = preco;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }
    }


