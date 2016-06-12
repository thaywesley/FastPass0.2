package com.example.ivanildo.fastpass02;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

public class Resultado_Final extends AppCompatActivity {

    public static final String EXTRA_PAGE = Checkout_Actibity.class.getName() + ".PAGE";
    public static final int PAGE_SUCCESS = 0;
    public static final int PAGE_FAIL = 1;

    ImageView tnsd_iv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado__final);

        QRCodeWriter writer = new QRCodeWriter();

         tnsd_iv_qr = (ImageView) findViewById(R.id.ImageQR);
        try {
            ByteMatrix bitMatrix = writer.encode("S1D1", BarcodeFormat.QR_CODE, 512, 512);
            int width = 512;
            int height = 512;
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (bitMatrix.get(x, y) == 0)
                        bmp.setPixel(x, y, Color.BLACK);
                    else
                        bmp.setPixel(x, y, Color.WHITE);
                }
            }
            tnsd_iv_qr.setImageBitmap(bmp);
        } catch (WriterException e) {
            //Log.e("QR ERROR", ""+e);

        }
    }
}
