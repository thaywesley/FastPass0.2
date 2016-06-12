package com.example.ivanildo.fastpass02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProdutoDAO {
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public ProdutoDAO(Context context){
		helper = new DatabaseHelper(context);
	}
	//obtem uma instancia de SQLiteDatabase, criando-a se necessario
	public SQLiteDatabase getDb(){
		if(db==null){
			db = helper.getWritableDatabase();
		}
		return db;
	}
	
	//inserir Contatos
		public long inserirProduto(Produto produto){
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.Produtos._ID,
					   produto.getId());
			values.put(DatabaseHelper.Produtos.Descricao,
					   produto.getDescricao());
			values.put(DatabaseHelper.Produtos.Preco,
					   produto.getPreco());
			values.put(DatabaseHelper.Produtos.Mercado,
					produto.getPreco());

			return getDb().insert(DatabaseHelper.Produtos.TABELA,null,values);
					
		}
	//listar Contatos

	public ArrayList<Produto> listarprodutos(){
		Cursor cursor = getDb().query(DatabaseHelper.Produtos.TABELA,
				DatabaseHelper.Produtos.COLUNAS,null,null,null,null,null);
		ArrayList <Produto> produtos = new ArrayList<Produto>();
		while(cursor.moveToNext()){
			Produto produto = new Produto();
			produto.setId(cursor.getInt(0));
			produto.setDescricao(cursor.getString(1));
			produto.setPreco(cursor.getString(2));
			produto.setMercado(cursor.getString(3));
			produtos.add(produto);
		}
		cursor.close();
		return produtos;
	}
	

	//atualizar Contato
		public boolean atualizarProduto(Produto produto){
			String whereClause = DatabaseHelper.Produtos._ID + " = ?";
			String[] whereArgs = new String[]{String.valueOf(produto.getId())};
			System.out.println(whereClause+whereArgs);
			
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.Produtos._ID,
					   produto.getId());
			values.put(DatabaseHelper.Produtos.Descricao,
					   produto.getDescricao());
			values.put(DatabaseHelper.Produtos.Preco,
					   produto.getPreco());
			values.put(DatabaseHelper.Produtos.Mercado,
					   produto.getMercado());
			int atualizados = getDb().update(DatabaseHelper.Produtos.TABELA, values,
										   whereClause, whereArgs);
			return atualizados > 0;
		}
		
	//excluir Contato por _id
	public boolean removerProduto(Long id){
		String whereClause = DatabaseHelper.Produtos._ID + " = ?";
		String[] whereArgs = new String[]{id.toString()};
		int removidos = getDb().delete(DatabaseHelper.Produtos.TABELA,
									   whereClause, whereArgs);
		return removidos > 0;
	}
	
	
	//fechar
	public void close(){
		helper.close();
	}
}
