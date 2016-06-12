package com.example.ivanildo.fastpass02;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable{

	private long id;
	private String descricao;
	private String preco;
	private String mercado;

	public Produto(){
		this(0,null,null,null);
	}
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public Produto(long id, String descricao, String preco, String mercado) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.mercado = mercado;
	}

	private Produto(Parcel from){
		id = from.readLong();
		descricao = from.readString();
		preco = from.readString();
		mercado = from.readString();

	}
	public static final Creator <Produto>
			CREATOR = new Creator <Produto>(){
		public Produto createFromParcel(Parcel in){
			return new Produto(in);
		}
		public Produto[] newArray(int size){
			return new Produto[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(descricao);
		dest.writeString(String.valueOf(preco));
		dest.writeString(mercado);
	}

	public String toString(){
		return descricao;
	}
}


