package com.example.demo.dao;

import com.example.demo.model.Address;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddresssDaoH2 implements IDao<Address> {

    // Variables para el método "save"
    private static final String SQL_INSERT = "INSERT INTO ADDRESSES ( STREET, " +
            " NUMBER, LOCATION, PROVINCE) VALUES( ?,?,?,? ) ";

    //Variables para el método "findById"
    private static final String SQL_SELECT_ID = " SELECT * FROM ADDRESSES WHERE ID=?";

    //Variables para el método update
    private static final String SQL_UPDATE = " UPDATE ADDRESSES SET STREET=?, NUMBER=?,  " +
            " LOCATION=?, PROVINCE=? WHERE ID=?";

    //Variables para el método delete
    private static final String SQL_DELETE = " DELETE FROM ADDRESSES WHERE ID=? " ;

    //Variables para el método findAll
    private static final String SQL_SELECT_ALL = " SELECT * FROM ADDRESSES ";

    // Metodos heredados

    @Override
    public Address save(Address address) {
        Connection connection = null;
        try {

            // Escribir la logica que tendrá el método save

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,address.getStreet());
            ps.setInt(2, address.getNumber());
            ps.setString(3, address.getLocation());
            ps.setString(4,address.getProvince());

            ResultSet rs = ps.getGeneratedKeys();
            while ( rs.next() ){
                address.setId(rs.getInt(1));
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return address;
    }

    @Override
    public Address findById(Integer id) {

        Connection connection = null;
        //Antes crear el domicilio
        Address address = null;

        try{

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);  //   Que orden squal le vamos a pasar
            ps.setInt(1, id ); //Setearle el id

            ResultSet rs = ps.executeQuery();//Hacer uso de la clase de java
            while ( rs.next() ) {//Ejecute ese result
                //completar el domicilio con los datos que vengan de la base de datos
                address = new Address(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5)); //crear un nuevo domicilio, llamando el objeto java de toda la orden que se ejecuto en la base, primero el id, luego...
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return address;
    }

    @Override
    public void update(Address address) {

        Connection connection = null;

        try{
            connection = DB.getConnection();//conectarnos con el metodo estadico que habíamos creado-
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);// vamos a trabajar con un prepate statement, donde usaremos la conexón para darle una orden Sequal
            ps.setString(1, address.getStreet());
            ps.setInt(2,address.getNumber());
            ps.setString(3,address.getLocation());
            ps.setString(4,address.getProvince());
            ps.setInt(5, address.getId());
            ps.execute();


        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Integer id) {

        Connection connection = null;

        try{

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE); // Pasarle la orden sequal
            ps.setInt(1,id);
            ps.execute();

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    @Override
    public List<Address> findAll() {

        Connection connection = null;

        Address address = null; //Crear una instancia del domicilio

        List<Address> addresses = new ArrayList<>(); //vamos a crear una lista de domicilios

        try{
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                // Completar el domicilio
                address = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
                // Vamos a incorporar el domicilio a la lista
                addresses.add(address);
            }


        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return addresses;
    }

    @Override
    public Address findByString(String value) {
        return null;
    }


}
