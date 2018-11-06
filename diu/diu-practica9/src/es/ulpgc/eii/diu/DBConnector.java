/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.*;

/**
 *
 * @author David
 */
public class DBConnector {
    
    private final String service = "localhost";//"mozart.dis.ulpgc.es";
    private final String database = "DIU_2018_19";
    //private String user = "estudiante-DIU";
    //private String password = "DIU1819-aed56-noi";
    
    private Map<String, List<String>> result = new TreeMap<>();
    
    public boolean connect(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + service + "/" + database + "?useSSL=true",
                user,
                password);
            DatabaseMetaData md = con.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = md.getTables(null, null, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                ResultSet rs2 = md.getColumns(null, null, tableName, null);
                List<String> columns = new ArrayList<>();
                while (rs2.next()) {
                    String columnName = rs2.getString("COLUMN_NAME");
                    columns.add(columnName);
                }
                result.put(tableName, columns);
            }
            con.close();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Map<String, List<String>> getResult() {
        return this.result;
    }
}
