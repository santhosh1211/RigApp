package in.co.santhoshiot.rigapp;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24-02-2018.
 */

public class DBconnection {

    DBconnection(){

    }
    public static Connection sqlconn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                      conn= DriverManager.getConnection(
                    "jdbc:mysql://santhoshiot.co.in:3306/restdb","sandydb1234","sandydb1234");
        }catch (Exception e){
            Log.e("gggg",e.toString());
        }

        return conn;
    }


    public List<String> LoadManager(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_manager where AddOwner='"+OwnerHome.emp_id+"' ";
                    Log.e("query","select * from tb_manager where AddOwner='"+OwnerHome.emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }

    public String getID(String emp_id) {
        String aa="";
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;

            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_manager where name='"+emp_id+"' ";
                    Log.e("select manager","select * from tb_manager where name='"+emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){
                     aa=rs.getString("Id");
                     Log.e("id",aa);
                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        Log.e("id return",aa);
        return aa;
    }
}
