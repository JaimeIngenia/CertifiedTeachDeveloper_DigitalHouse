package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./dc";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    // Método para la conexión de la base de datos
    public static Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    // Metodo para crear la tabla master otra vez rama springboot
    public static void createTables(){
        Connection connection = null;// singleton

        try{
            connection = getConnection();
            Statement statement = connection.createStatement();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
