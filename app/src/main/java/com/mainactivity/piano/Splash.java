package com.mainactivity.piano;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class Splash extends AppCompatActivity {
   private static final int PERMISSION_CONSTANT=100;
    public static final int REQUEST_PERMISSION_SETTING=101;

    String[] PermissionRequired=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    private SharedPreferences permissionStatus;
    private boolean sentToSetting=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        permissionStatus=getSharedPreferences("permissionStatus",MODE_PRIVATE);

        if(ActivityCompat.checkSelfPermission(Splash.this,PermissionRequired[0])
                !=  PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Splash.this,
                PermissionRequired[1]) != PERMISSION_GRANTED){
          if(ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,PermissionRequired[0])
          ||ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,PermissionRequired[1]))
            {

                AlertDialog.Builder builder=new AlertDialog.Builder(Splash.this);
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app need Storage Permission");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Splash.this,PermissionRequired,PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
          else if(permissionStatus.getBoolean(PermissionRequired[0],false)){
              AlertDialog.Builder builder=new AlertDialog.Builder(Splash.this);
              builder.setTitle("Need Multiple Permission");
              builder.setMessage("This app need Storage Permission");
              builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    sentToSetting=true;
                      Intent i=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                      Uri uri=Uri.fromParts("package",getPackageName(),null);
                      i.setData(uri);
                     startActivityForResult(i,REQUEST_PERMISSION_SETTING);
                  }
              });

              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.cancel();
                  }
              });
              builder.show();

          }
          else{
              ActivityCompat.requestPermissions(Splash.this,PermissionRequired,PERMISSION_CONSTANT);
          }

        }
        else {
            proceedAfterPermission();
        }


        }

    private void proceedAfterPermission() {

        Toast.makeText(this,"Got All Permssion",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(requestCode == PERMISSION_CONSTANT){
            boolean allgranted=true;
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] == PERMISSION_GRANTED){
                    allgranted=true;
                }
                else {
                    allgranted=false;
                }
            }
            if (allgranted){
                proceedAfterPermission();
            }
            else if(ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                    PermissionRequired[0])|| ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                    PermissionRequired[1])){

                AlertDialog.Builder builder=new AlertDialog.Builder(Splash.this);
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app need Storage Permission");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Splash.this,PermissionRequired,PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else {
                Toast.makeText(this, "Unanle to get Permission", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "App will not work Properly", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PERMISSION_SETTING){
            if(ActivityCompat.checkSelfPermission(Splash.this,PermissionRequired[0])
            == PERMISSION_GRANTED ){
                proceedAfterPermission();
            }
        }
    }
}



