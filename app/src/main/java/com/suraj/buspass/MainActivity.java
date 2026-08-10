package com.suraj.buspass;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout qrLayout;
    private LinearLayout detailsLayout;
	private ImageView imgQR;
	

    private Button btnDetails;
    private Button btnQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		if (getSupportActionBar() != null) {
    getSupportActionBar().hide();
}

        qrLayout = findViewById(R.id.qrLayout);
        detailsLayout = findViewById(R.id.detailsLayout);

        btnDetails = findViewById(R.id.btnDetails);
        btnQR = findViewById(R.id.btnQR);
		imgQR = findViewById(R.id.imgQR);

        generateQR("EMPID=2919053;NAME=SURAJ GUPTA;ROUTE=THANE STN E KOPARI TO TCS OLYMPUS;VALID=01-07-2026 TO 31-07-2026;TYPE=BOTH;PASS=TCSBUSPASS202607012919053ABCDEFGHJKLMNOPQRSTUVWXYZ123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");

        btnDetails.setOnClickListener(v -> {
            qrLayout.setVisibility(View.GONE);
            detailsLayout.setVisibility(View.VISIBLE);
        });

        btnQR.setOnClickListener(v -> {
            detailsLayout.setVisibility(View.GONE);
            qrLayout.setVisibility(View.VISIBLE);
        });
    }
	
	private void generateQR(String text) {
    try {

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                600,
                600
        );

        Bitmap bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.RGB_565);

        for (int x = 0; x < 600; x++) {
            for (int y = 0; y < 600; y++) {
                bitmap.setPixel(x, y,
                        bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        imgQR.setImageBitmap(bitmap);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}