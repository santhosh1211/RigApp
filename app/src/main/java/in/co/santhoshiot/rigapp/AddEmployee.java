package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import org.angmarch.views.NiceSpinner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddEmployee extends Fragment {
    AppCompatButton add;
    AppCompatSpinner ds;
    TextInputEditText name,mno,eid,address;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    boolean registerstatus = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_employee, container, false);
        add=view.findViewById(R.id.add);
        ds=view.findViewById(R.id.dspinner);
        name=view.findViewById(R.id.name);
        mno=view.findViewById(R.id.mno);
        eid=view.findViewById(R.id.eid);
        address=view.findViewById(R.id.Address);
        layout = view.findViewById(R.id.aelayout);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     //   List<String> dataset = new LinkedList<>(Arrays.asList("Driver", "Support Driver", "Cook", "Labour", "Other"));
        String[] plants = new String[]{
                "Driver", "Support Driver", "Cook", "Labour", "Other"
        };
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinner_itemrigt,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_itemrigt);
        ds.setAdapter(spinnerArrayAdapter);
        //ds.attachDataSource(dataset);
        StrictMode.setThreadPolicy(policy);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().equalsIgnoreCase("")){
                    if(!mno.getText().toString().equalsIgnoreCase("")){
                        new RemoteDataTask().execute();
                    }
                    else{
                        showsnackbar("Please Enter Mobile Number ");
                    }
                }
                else{
                    showsnackbar("Please Enter Name ");
                }
            }
        });

        return view;
    }
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            addemployee();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();
               if(registerstatus){
                   showsnackbar("Sucessfully Added");
               }
               else{
                   showsnackbar("Failed to Added");
               }
            }
        }



    }

    private void addemployee() {
        try {
            Connection con = null;
            ResultSet rs = null;
            Statement statement = null;
            String aa = "";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_employee where Phno='"+mno.getText().toString()+"'  ";
                    rs = statement.executeQuery(queryString);
                    if (rs.isFirst()){
                        showsnackbar("Mobile Number Already Added");
                    }
                    else{
                        PreparedStatement ps = con.prepareStatement("Insert into tb_employee(name,Phno,Address,EmailId,designation,assignby) Values(?,?,?,?,?,?)");
                        ps.setString(1, name.getText().toString());
                        ps.setString(2, mno.getText().toString());
                        ps.setString(3, address.getText().toString());
                        ps.setString(4, eid.getText().toString());
                        ps.setString(5, ds.getSelectedItem().toString());
                        ps.setString(6, ManagerHome.emp_id);
                        Log.e("Manager id", ManagerHome.emp_id);
                        int ig = ps.executeUpdate();
                        if (ig > 0) {
                            registerstatus = true;
                        } else {
                            registerstatus = false;
                        }

                    }
                }
                else{
                    showsnackbar("Connection Null");
                }
            }
            catch (Exception e){
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
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
    }
    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }


}

