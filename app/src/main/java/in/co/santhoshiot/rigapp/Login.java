package in.co.santhoshiot.rigapp;

import android.app.ProgressDialog;
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
    String emp_name = "", user_status = "";
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

        sharedPreference = getSharedPreferences("rigapp", MODE_PRIVATE);
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

        @Override
        protected Void doInBackground(Void... voids) {
            checkpassword();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (loginstatus) {
                    showsnackbar("Sucessfully Login");
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
    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }
}
