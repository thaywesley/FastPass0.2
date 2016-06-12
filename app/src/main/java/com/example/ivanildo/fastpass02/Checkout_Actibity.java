package com.example.ivanildo.fastpass02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simplify.android.sdk.Card;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class Checkout_Actibity extends AppCompatActivity {

    CardEditor mCardEditor;
    Button mPayButton;
    public static final String Dados = ListaCompras.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout__actibity);

        mPayButton = (Button) findViewById(R.id.simplify_zip);
        mCardEditor.setEnabled(false);
        mPayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                requestCardToken();
            }
        });

        mCardEditor = (CardEditor) findViewById(R.id.card_editor);
        mCardEditor.getCard().setAddressZip("55728");
        mCardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                mPayButton.setEnabled(cardEditor.isValid());
            }
        });
    }

    public void requestCardToken() {

        mPayButton.setEnabled(false);

        Card card = mCardEditor.getCard();

        Simplify.createCardToken(card, new CardToken.Callback() {
            @Override
            public void onSuccess(CardToken cardToken) {
                mPayButton.setEnabled(true);

                Intent i = new Intent(Checkout_Actibity.this, Resultado_Final.class);
                i.putExtra(Resultado_Final.EXTRA_PAGE, Resultado_Final.PAGE_SUCCESS);
                startActivity(i);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                mPayButton.setEnabled(true);

                Intent i = new Intent(Checkout_Actibity.this, Resultado_Final.class);
                i.putExtra(Resultado_Final.EXTRA_PAGE, Resultado_Final.PAGE_FAIL);
                startActivity(i);
            }
        });
    }
}

