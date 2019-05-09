package in.co.santhoshiot.rigapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
