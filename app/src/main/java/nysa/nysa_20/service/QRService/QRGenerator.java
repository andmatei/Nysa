package nysa.nysa_20.service.QRService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import nysa.nysa_20.R;

public class QRGenerator extends AppCompatActivity {
    private static ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        qrImage = findViewById(R.id.qrImage);
        String qrValue = "NysaAllegyApp";
     

    }
}
