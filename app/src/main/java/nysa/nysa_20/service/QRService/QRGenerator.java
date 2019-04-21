package nysa.nysa_20.service.QRService;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import nysa.nysa_20.R;

public class QRGenerator extends AppCompatActivity {
    private static ImageView qrImage;
    private static BitMatrix bitmatrix;
    private static MultiFormatWriter formatWriter;
    private static BarcodeEncoder encoder;
    private static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        formatWriter = new MultiFormatWriter();
        qrImage = findViewById(R.id.qrImage);
        String qrValue = "NysaAllegyApp";

        try {
            bitmatrix = formatWriter.encode(qrValue, BarcodeFormat.QR_CODE,300,300);
            encoder = new BarcodeEncoder();
            bitmap = encoder.createBitmap(bitmatrix);
            qrImage.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
