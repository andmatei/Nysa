package nysa.nysa_20.service.QRService;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.Doctor;
import nysa.nysa_20.service.utilitary.PermissionService;

public class QRReader extends AppCompatActivity {


    private SurfaceView cameraPreview;

    private static String rez = "";
    private BarcodeDetector detector;
    private CameraSource cameraSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_reader);
        cameraPreview = findViewById(R.id.cameraPreview);

        detector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource.Builder(this,detector)
                                        .setRequestedPreviewSize(640,480)
                                        .build();


        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                PermissionService.checkCameraPermission(QRReader.this);
                if(!ActivityCompat.shouldShowRequestPermissionRationale(QRReader.this, Manifest.permission.CAMERA)){
                    try{
                        cameraSource.start(cameraPreview.getHolder());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() != 0)
                {
                    rez = qrcodes.valueAt(0).displayValue;
                    Doctor doctor = new Doctor();
                    String id = rez.substring(rez.indexOf("Id")+2,rez.indexOf("Name"));
                    String name = rez.substring(rez.indexOf("Name")+4,rez.indexOf("Phone"));
                    String phone = rez.substring(rez.indexOf("Phone")+5,rez.indexOf("Email"));
                    String email = rez.substring(rez.indexOf("Email")+5);
                    doctor.setId(id);
                    doctor.setEmail(email);
                    doctor.setNume(name);
                    doctor.setPhone(phone);
                    Account account = AccountHolder.getAccount();
                    account.setDoctor(doctor);

                    finish();

                }
            }
        });

    }

    public static String getRez() {
        return rez;
    }
}
