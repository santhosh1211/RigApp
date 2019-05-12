package in.co.santhoshiot.rigapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isloggedin;
   public static String emp_id,emp_name,user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = getSharedPreferences("rigapp", MODE_PRIVATE);
        editor = preferences.edit();
        isloggedin = preferences.getBoolean("islogged", false);
        emp_id = preferences.getString("emp_id", "0");
        emp_name = preferences.getString("emp_name", "0");
        user_type = preferences.getString("user_type", "user_type");
        Log.d("user Status", "emp name: " + emp_name + " emp id: " + emp_id + " type: " + user_type);
        Log.d("user isloggedin", "emp status: " + isloggedin);
        if (isloggedin) {
            Log.d("isloggedin", ""+isloggedin);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("Build.VERSION", ""+Build.VERSION.SDK_INT );
                    if (Build.VERSION.SDK_INT < 23) {
                        startactivity();
                        Log.d("startactivity", "go" );
                    } else {
                        if (checkPermission()) {
                            startactivity();
                            Log.d("checkPermission", "checkPermission" );
                        }
                    }
                }
            }, 2000);
        }
        if (!isloggedin) {
            Intent intent = new Intent(SplashScreen.this, Login.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            finish();
        }
    }


    private void startactivity() {
        Log.d("startactivity", "come" );
         if(user_type.equalsIgnoreCase("manager")){

             editor.putString("emp_id", emp_id);
             editor.putString("emp_name", emp_name);
             editor.putString("user_type",user_type);
             editor.commit();
             Intent intent = new Intent(SplashScreen.this, ManagerHome.class);
             startActivity(intent);
             overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
             Log.d("user Status","emp name: "+emp_name+" emp id: "+emp_id+" type: "+user_type);
             finish();
         }
           else if(user_type.equalsIgnoreCase("owner")){
             editor.putString("emp_id", emp_id);
             editor.putString("emp_name", emp_name);
             editor.putString("user_type",user_type);
             editor.commit();
             Intent intent = new Intent(SplashScreen.this, OwnerHome.class);
             startActivity(intent);
             overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
             Log.d("user Status","emp name: "+emp_name+" emp id: "+emp_id+" type: "+user_type);
             finish();
            }

    }

    private boolean checkPermission()
    {
        int call = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE);

        int ACCESS_COARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int ACCESS_FINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_NETWORK = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int Read_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (call != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (ACCESS_COARSE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ACCESS_FINE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ACCESS_NETWORK != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (Read_storage!=PackageManager.PERMISSION_GRANTED){
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }



        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 1:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    startactivity();
                } else {

                }
                break;
        }
    }
}

