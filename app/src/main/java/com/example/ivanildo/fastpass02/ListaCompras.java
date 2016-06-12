package com.example.ivanildo.fastpass02;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivanildo.fastpass02.android.IntentIntegrator;
import com.example.ivanildo.fastpass02.android.IntentResult;
import com.example.ivanildo.fastpass02.facebookloginsample.LoginActivity;
import com.example.ivanildo.fastpass02.facebookloginsample.PrefUtils;
import com.example.ivanildo.fastpass02.facebookloginsample.User;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListaCompras extends AppCompatActivity implements View.OnClickListener {

    Button BtCarrinho, BtMasterpass, BtCheckout;
    //private TextView formatTxt, contentTxt;
    int Codigo;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private ArrayAdapter<Produto> adapter;
    private ListView listaprodutos;
    private ProdutoDAO dao;
    private TextView txtQuant, txtValorTotal;
    double ValorTotal = 0.00;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;
    long codigoo;
    private TextView btnLogout;
    private User user;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);


        BtCarrinho = (Button) findViewById(R.id.AdicionarItem);
        //BtCheckout = (Button) findViewById(R.id.CheckoutBT);
        //BtMasterpass = (Button) findViewById(R.id.MasterPassBt);
        txtQuant = (TextView) findViewById(R.id.Itens_Select);
        txtValorTotal = (TextView) findViewById(R.id.ValorTotal);

        BtCarrinho.setOnClickListener(this);
        // BtCheckout.setOnClickListener(this);
        //BtMasterpass.setOnClickListener(this);
        listaprodutos = (ListView) findViewById(R.id.listCompras);
        itens = new ArrayList<ItemListView>();
        gerarLista();
        InsereTabela(78907355, "Choc Talento: Amendoas", "5.20", "Facebook");
        InsereTabela(78907485, "Choc Talento: Cerais e Passas", "2.40", "Facebook");
        InsereTabela(78932586, "Chiclets Adans: laranja", "0.50", "Facebook");


        /*InsereTabela(7893333289210,"Clube Social",3.40);
        InsereTabela("7891962030661","Bauduco Roll",2.10);
        InsereTabela("7894900010015","Coca-Lata",4.50);
*/
        btnLogout = (TextView) findViewById(R.id.btLogoff);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.clearCurrentUser(ListaCompras.this);


                // We can logout from facebook by calling following method
                LoginManager.getInstance().logOut();


                Intent i = new Intent(ListaCompras.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        user = PrefUtils.getCurrentUser(ListaCompras.this);

        // fetching facebook's profile picture
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                URL imageURL = null;
                try {
                    imageURL = new URL("https://graph.facebook.com/" + "415313148637614" + "/picture?type=large");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.AdicionarItem) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
       /* if (v.getId() == R.id.CheckoutBT) {
            Intent i = new Intent(ListaCompras.this, Checkout_Actibity.class);
            i.putExtra(Checkout_Actibity.Dados, Checkout_Actibity.Dados);
        }
        if (v.getId() == R.id.MasterPassBt) {

        }*/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            // String scanFormat = scanningResult.getFormatName();
            //    formatTxt.setText("Padrão: " + scanFormat);
            //contentTxt.setText("Código: " + scanContent);
            Codigo = Integer.parseInt(scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nenhum código identificado!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void onStart() {
        super.onStart();
        gerarLista();
        txtQuant.setText(produtos.size() + " Itens");
        txtValorTotal.setText("R$ " + ValorTotal);
    }


    public void gerarLista() {
        dao = new ProdutoDAO(ListaCompras.this);
        for (int i = 0; i < dao.listarprodutos().size(); i++) {
            if (Codigo == dao.listarprodutos().get(i).getId()) {
                ValorTotal += Double.parseDouble(dao.listarprodutos().get(i).getPreco());
                ItemListView item = new ItemListView(dao.listarprodutos().get(i).getDescricao(), dao.listarprodutos().get(i).getPreco());
                produtos.add(dao.listarprodutos().get(i));
                itens.add(item);
                adapterListView = new AdapterListView(this, itens);

                //Define o Adapter
                listaprodutos.setAdapter(adapterListView);
                //           adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);
                //listaprodutos.setAdapter(adapter);
            }
        }
        Codigo = 0;
    }

    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    public void InsereTabela(long id, String descr, String preco, String mercado) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setDescricao(descr);
        produto.setPreco(preco);
        produto.setMercado(mercado);
        dao.inserirProduto(produto);
    }

}

