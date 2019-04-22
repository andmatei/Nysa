package nysa.nysa_20.service.utilitary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;



public final class PermissionService {

    private PermissionService(){}

    private static final int READ_STORAGE_PERMISSION_CODE = 1;
    private static final int  CAMERA_PERMISSION_CODE = 2;
    private static final int FINE_LOCATION_PERMISSION_CODE = 3;
    private static final int WRITE_STORAGE_PERMISSION_CODE = 4;
    private static final int INTERNET_PERMISSION_CODE = 5;
    private static final int RECORD_AUDIO_PERMISSION_CODE = 6;
    private static final int COARSE_LOCATION_PERMISSION_CODE = 7;


    public static void checkReadStoragePermission(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestReadStoragePermission(context);

        }
    }

    private static void requestReadStoragePermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for storage")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},READ_STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},READ_STORAGE_PERMISSION_CODE);
        }

    }

    public static void checkWriteStoragePermission(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestWriteStoragePermission(context);

        }
    }

    private static void requestWriteStoragePermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for storage")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_STORAGE_PERMISSION_CODE);
        }

    }


    public static void checkCameraPermission(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestCameraPermission(context);

        }
    }

    private static void requestCameraPermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for taking photos")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }

    }



    public static void checkFineLocationPermission(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestFineLocationPermission(context);

        }
    }

    private static void requestFineLocationPermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for getting location")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_PERMISSION_CODE);
        }

    }
    public static void checkInternetPermission(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestInternetPermission(context);

        }
    }

    private static void requestInternetPermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.INTERNET)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for getting internet access")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.INTERNET},INTERNET_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.INTERNET},INTERNET_PERMISSION_CODE);
        }

    }

    public static void checkRecordAudio(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestInternetPermission(context);

        }
    }

    private static void requestRecordAudioPermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.RECORD_AUDIO)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for getting audio input")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.RECORD_AUDIO},RECORD_AUDIO_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.RECORD_AUDIO},RECORD_AUDIO_PERMISSION_CODE);
        }

    }
    public static void checkCoarseLocation(Activity context ){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(context,"You have already granted this permission",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestInternetPermission(context);

        }
    }
    private static void requestCoarseLocationPermission(final Activity  context) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.ACCESS_COARSE_LOCATION)){
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for getting location")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},COARSE_LOCATION_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        }else{

            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},COARSE_LOCATION_PERMISSION_CODE);

        }

    }
}

