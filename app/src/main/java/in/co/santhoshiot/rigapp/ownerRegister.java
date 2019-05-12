package in.co.santhoshiot.rigapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ownerRegister extends Fragment {
    TextInputEditText name, password, username, email, mobile, address;
    AppCompatButton submit;
    private ProgressDialog pDialog;
    boolean registerstatus = false;
    private LinearLayout layout;
    String emp_id,emp_name;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_addmanager, container, false);
        name = view.findViewById(R.id.mname);
        address = view.findViewById(R.id.maddress);
        email = view.findViewById(R.id.memail);
        mobile = view.findViewById(R.id.mph);
        username = view.findViewById(R.id.muname);
        password = view.findViewById(R.id.mpassword);
        submit = view.findViewById(R.id.addmanager);
        layout = view.findViewById(R.id.reg_layout);
        emp_id=OwnerHome.emp_id;
        emp_name=OwnerHome.emp_name;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Register().execute();
            }
        });
        return view;
    }

    private class Register extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            adduser();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (registerstatus) {
                    showsnackbar("Sucessfully Register");
                    name.setText("");
                    email.setText("");
                    mobile.setText("");
                    address.setText("");
                    username.setText("");
                    password.setText("");


                }
                else {
                    showsnackbar("Not Register");
                }
            }
        }
    }

    private void adduser() {
        try{
            Connection con=null;
              String aa="";

            try{
                con = DBconnection.sqlconn();
                PreparedStatement ps = con.prepareStatement("Insert into tb_manager(Name,Phno,EmailId,Address,Username,Password,AddOwner) Values(?,?,?,?,?,?,?)");
                ps.setString(1,name.getText().toString());
                ps.setString(2,mobile.getText().toString());
                ps.setString(3,email.getText().toString());
                ps.setString(4,address.getText().toString());
                ps.setString(5,username.getText().toString());
                ps.setString(6,password.getText().toString());
                ps.setString(7,OwnerHome.emp_id);

                Log.e("owner id",OwnerHome.emp_id);
                int ig = ps.executeUpdate();
                if (ig > 0) {
                    registerstatus=true;
                }
                else{
                    registerstatus=false;
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Log.e("error",e.toString());
            }finally {
                try {

                    con.close();


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
