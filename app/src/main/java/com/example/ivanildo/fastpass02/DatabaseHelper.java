package com.example.ivanildo.fastpass02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String BANCO_DADOS = "Fast Pass";
	private static int VERSAO = 1;
	
	//inclusao de constantes - tabelas e colunas do BD
	public static class Produtos{
		public static final String TABELA="Produtos";
		public static final String _ID="Cod_Prod";
		public static final String Descricao ="descricao";
		public static final String Preco ="preco";
		public static final String Mercado="mercado";

		public static final String [] COLUNAS =  new String[]{_ID, Descricao, Preco, Mercado};
	}

	public DatabaseHelper(Context context){
		super(context,BANCO_DADOS,null,VERSAO);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//criacao das tabelas
		db.execSQL("Create table Produtos" +
				"(Cod_Prod int primary key , " +
				"descricao text not null, preco text not null,mercado text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		//atualizacao da estrutura do BD
	}

}
