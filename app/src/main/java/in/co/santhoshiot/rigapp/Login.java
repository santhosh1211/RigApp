package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.angmarch.views.NiceSpinner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Login extends AppCompatActivity {
    NiceSpinner roll;
    private AppCompatButton bt_login;
    private EditText et_username, et_password;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    String password = "", username = "";
    boolean loginstatus = false;
    String emp_id = "0";
    String emp_name = "", user_type = "";

    private boolean iscomplete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        roll = (NiceSpinner) findViewById(R.id.rollspinner);
        layout = (LinearLayout) findViewById(R.id.login_layout);
        bt_login = (AppCompatButton) findViewById(R.id.login_btn_login);
        et_username = (EditText) findViewById(R.id.login_et_username);
        et_password = (EditText) findViewById(R.id.login_et_password);

        sharedPreference = getSharedPreferences("rigapp", MODE_PRIVATE);//rigapp
        editor = sharedPreference.edit();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        List<String> dataset = new LinkedList<>(Arrays.asList("Owner", "Manager"));
        roll.attachDataSource(dataset);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString().trim().replaceAll("\\s+", "");
                password = et_password.getText().toString().trim().replaceAll("\\s+", "");
                new LoginAuthantication().execute();
            }
        });
    }
    private class LoginAuthantication extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            String type;
            type=roll.getSelectedItem().toString();
            if(type.equalsIgnoreCase("Manager")) {
                checkpassword();
            }
            else if(type.equalsIgnoreCase("Owner")) {
                checkpassword1();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (loginstatus) {
                    showsnackbar("Sucessfully Login");
                    user_type=roll.getSelectedItem().toString();
                    if(user_type.equalsIgnoreCase("Manager")) {
                        editor.putString("emp_id", emp_id);
                        editor.putBoolean("islogged", true);
                        editor.putString("emp_name", emp_name);
                        editor.putString("user_type",user_type);
                        editor.commit();
                        Intent intent = new Intent(Login.this, ManagerHome.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        finish();
                    }
                    else if(user_type.equalsIgnoreCase("Owner")) {

                        editor.putString("emp_id", emp_id);
                        editor.putBoolean("islogged", true);
                        editor.putString("emp_name", emp_name);
                        editor.putString("user_type",user_type);
                        editor.commit();
                        Intent intent = new Intent(Login.this, OwnerHome.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        finish();
                    }




                } else {
                    showsnackbar("Invalid Password");
                }
            }
        }
    }
    public void checkpassword(){
        try{
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_manager where Password='"+password+"' and Username='" + username + "'  ";
                    rs = statement.executeQuery(queryString);
                    Log.e("ggg",rs.isFirst()+"-"+rs.first());
                    if (rs.isFirst()){
                        loginstatus = true;
                        emp_id = rs.getString("id");
                        emp_name = rs.getString("name");
                        Log.d("Login Status","emp name: "+emp_name+" emp id: "+emp_id);

                    }
                    else{
                        loginstatus=false;
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    statement.close();
                    con.close();
                    rs.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            Log.e("ggggg",e.toString());

        }
    }

    public void checkpassword1() {
        try {
            Connection con = null;
            ResultSet rs = null;
            Statement statement = null;
            String aa = "";
            try {
                con = DBconnection.sqlconn();
                if (con != null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_owner where Password='" + password + "' and Username='" + username + "'  ";
                    rs = statement.executeQuery(queryString);
                    Log.e("ggg", rs.isFirst() + "-" + rs.first());
                    if (rs.isFirst()) {
                        loginstatus = true;
                        emp_id = rs.getString("id");
                        emp_name = rs.getString("EmailId");
                        Log.d("Login Status", "emp name: " + emp_name + " emp id: " + emp_id);

                    } else {
                        loginstatus = false;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    con.close();
                    rs.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e("ggggg", e.toString());

        }
    }
    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }
}
