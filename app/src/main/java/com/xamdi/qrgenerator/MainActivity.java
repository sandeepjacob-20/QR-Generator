package com.xamdi.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText data;
    private ImageView output;
    private Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.QRtext);
        go = findViewById(R.id.generate);
        output = findViewById(R.id.qr_output);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrvalue = data.getText().toString().trim();

                MultiFormatWriter writer = new MultiFormatWriter();

                try{
                    BitMatrix matrix = writer.encode(qrvalue, BarcodeFormat.QR_CODE, 350, 350);

                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);

                    output.setImageBitmap(bitmap);

                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    manager.hideSoftInputFromWindow(data.getApplicationWindowToken(),0);
                } catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }
}