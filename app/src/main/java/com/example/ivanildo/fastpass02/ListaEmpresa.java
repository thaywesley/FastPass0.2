package com.example.ivanildo.fastpass02;

        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
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

        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;

public class ListaEmpresa extends AppCompatActivity implements View.OnClickListener{

    Button confirma,verifica,deslogar;
    TextView btLogOff;
    String Codigo="";
    private User user;
    private ProdutoDAO dao;
    double ValorTotal;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;
    private ListView listaprodutos;
    private TextView txtQuant, txtValorTotal;
    Button btComf;
    int quant=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresa);
        verifica = (Button) findViewById(R.id.VerifyCompr);
        verifica.setOnClickListener(this);

        txtQuant = (TextView) findViewById(R.id.Itens_Select2);
        txtValorTotal = (TextView) findViewById(R.id.ValorTotal2);
        listaprodutos = (ListView) findViewById(R.id.listconferencia);
        itens = new ArrayList<ItemListView>();

        btComf = (Button) findViewById(R.id.btConfirmaLista);
        btComf.setOnClickListener(this);




        btLogOff = (TextView) findViewById(R.id.btLogoff2);
        btLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.clearCurrentUser(ListaEmpresa.this);


                // We can logout from facebook by calling following method
                LoginManager.getInstance().logOut();


                Intent i = new Intent(ListaEmpresa.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        user = PrefUtils.getCurrentUser(ListaEmpresa.this);

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
        if (v.getId() == R.id.VerifyCompr) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        if (v.getId() == R.id.btConfirmaLista){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Compra Confirmada", Toast.LENGTH_SHORT);
            toast.show();
            listaprodutos.setAdapter(null);
            ValorTotal=0;
            quant=0;
            txtQuant.setText(0 + " Itens");
            txtValorTotal.setText("R$ " + ValorTotal);

        }


    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            // String scanFormat = scanningResult.getFormatName();
            //    formatTxt.setText("Padrão: " + scanFormat);
            //contentTxt.setText("Código: " + scanContent);
            Codigo = scanContent;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nenhum código identificado!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    protected void onStart() {
        super.onStart();
        gerarLista();

    }
    public void gerarLista() {
        dao = new ProdutoDAO(ListaEmpresa.this);
        if (Codigo.equals("S1D1") ){
            Toast.makeText(ListaEmpresa.this,"Entrou no Bagulho",Toast.LENGTH_LONG).show();
            ItemListView item = new ItemListView("Choc Talento: Amendoas", "5.20");
            ItemListView item2 = new ItemListView("Chiclets Adans: laranja", "0.50");
            itens.add(item);
            itens.add(item2);
            adapterListView = new AdapterListView(this, itens);
            ValorTotal=Double.parseDouble(item.getPreco())+Double.parseDouble(item2.getPreco());
            quant=itens.size();
            txtQuant.setText(quant + " Itens");
            txtValorTotal.setText("R$ " + ValorTotal);

            //Define o Adapter
            listaprodutos.setAdapter(adapterListView);
            //           adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);
            //listaprodutos.setAdapter(adapter);
        }
        Codigo=null;

    }
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

}
