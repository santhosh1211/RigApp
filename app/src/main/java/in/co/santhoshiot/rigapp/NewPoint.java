package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class NewPoint extends Fragment {
    AppCompatButton submit;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    boolean registerstatus = false;
    String[] array1, array2;
    int[] a1, a2;
    String s1, s2,s3;
    int v1=0,v2=0;
    long  result = 0,result1 = 0,rr=55;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_point, container, false);
        layout = view.findViewById(R.id.nplayout);
        e1=view.findViewById(R.id.tfeet);
        e2=view.findViewById(R.id.srpm);
        e3=view.findViewById(R.id.crpm);
        e4=view.findViewById(R.id.bb);
        e5=view.findViewById(R.id.paid);
        e6=view.findViewById(R.id.wld);
        e7=view.findViewById(R.id.cpe);
        e8=view.findViewById(R.id.dst);


        t1=(TextView) view.findViewById(R.id.ttamout);
        t2=view.findViewById(R.id.trpm);
        t3=view.findViewById(R.id.arpm);
        t4=view.findViewById(R.id.td);
        t5=view.findViewById(R.id.tda);
        t6=view.findViewById(R.id.bh11);
        t7=view.findViewById(R.id.c80);
        t8=view.findViewById(R.id.ctotal);
        t9=view.findViewById(R.id.mar);
        t10=view.findViewById(R.id.crdt);
        submit=view.findViewById(R.id.nrsummit);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteDataTask1().execute();
            }
        });


        return view;

    }

    private class RemoteDataTask1 extends AsyncTask<Void, Void, Void> {
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

            readdata();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();

            }
        }
    }

    private void readdata() {
        try {
            Connection con1 = null;
            ResultSet rs1 = null;
            Statement statement1 = null;
            String aa = "";
            try{
                con1=DBconnection.sqlconn();
                if (con1!=null) {
                    statement1 = con1.createStatement();

                    String queryString = "select * from tbl_rigpoint where id='"+ManagerHome.emp_id+"'  ";
                    rs1 = statement1.executeQuery(queryString);
                    if (rs1.next()){
                        s1=rs1.getString("distance");
                        s2=rs1.getString("rate");
                        s3=rs1.getString("deiselrate");
                        Log.e("s1:",s1);
                        Log.e("s2:",s2);

                    }
                    if(s1 != null && s2 != null) {
                        array1 = s1.split(",");
                        array2 = s2.split(",");
                        Log.e("array1:", Arrays.deepToString(array1));
                        Log.e("array2:", Arrays.deepToString(array2));
                    }


                    for (int i = 1; i < array1.length; i++){

                            if( i == 1){
                                if(Integer.parseInt(e1.getText().toString()) > Integer.parseInt(array1[1] )){
                                    result = Integer.parseInt(array1[1]) * Integer.parseInt(array2[1]);
                                    result1 = result ;
                                   // Log.e("result4: ",""+result1);
                                }else{
                                    result = Integer.parseInt(e1.getText().toString()) * Integer.parseInt(array2[1]);
                                    result1 = result ;
                               //     Log.e("result5: ",""+result1);
                                    break;
                                }
                            }else{

                                //Log.e("result1:",result1);
                              //  Log.e("result1: ",""+result1);
                                if(Integer.parseInt(e1.getText().toString()) > Integer.parseInt(array1[i] )) {

                                    result = (Integer.parseInt(array1[i]) - Integer.parseInt(array1[i-1])) * Integer.parseInt(array2[i]);

                                    result1 = result + result1;
                                   // Log.e("result2: ",""+result1);

                                }else{

                                    result = (Integer.parseInt(e1.getText().toString()) - Integer.parseInt(array1[i-1])) * Integer.parseInt(array2[i]);
                                    result1 = result + result1;
                                   // Log.e("result3: ",""+result1);
                                    break;
                                }


                            }
                    }




                    t1.setText("");
                    t1.setText(result1+"");
                    result = 0;
                    result1 = 0;

                    double trpm=Double.parseDouble(e3.getText().toString())-Double.parseDouble(e2.getText().toString());
                    t2.setText("");
                    t2.setText(trpm+"");
                    double avg=Double.parseDouble(e1.getText().toString())/trpm;
                    t3.setText("");
                    t3.setText((int)avg+"");

                    double ttd=trpm*Double.parseDouble(s3);
                    t4.setText("");
                    t4.setText(ttd+"");

                    double ttda=trpm*Double.parseDouble(s3)*65;
                    t5.setText("");
                    t5.setText(ttda+"");
                    double tbh11=Double.parseDouble(e1.getText().toString())*14;
                    t6.setText("");
                    t6.setText((int)tbh11+"");
                    t7.setText("0");
                    t8.setText(""+((tbh11+ttda)));

                    t9.setText("");
                    t9.setText(""+(Double.parseDouble(e5.getText().toString())-(tbh11+ttda)));

                    t10.setText("");
                    t10.setText("0");



                }
                else{
                    showsnackbar("Connection Null");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    statement1.close();
                    con1.close();
                    rs1.close();

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