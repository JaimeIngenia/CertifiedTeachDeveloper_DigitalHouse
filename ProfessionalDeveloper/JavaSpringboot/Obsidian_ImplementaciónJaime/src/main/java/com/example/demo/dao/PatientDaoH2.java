package com.example.demo.dao;

import com.example.demo.model.Address;
import com.example.demo.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoH2 implements IDao<Patient>{

    // Variables sequal para método save
    private static final String SQL_INSERT = " INSERT INTO PATIENTS ( NAME, LAST_NAME, EMAIL, CARD_IDENTITY,  "+
            " ADMISSION_OF_DATE, ADDRESS_ID ) VALUES(?,?,?,?,?,?) ";

    // Variables sequal para método findByID
    private final String SQL_SELECT_ID = " SELECT * FROM PATIENTS WHERE ID=? ";

    // Variables sequal para método update
    private final String SQL_UPDATE = "  UPDATE PATIENTS SET NAME=?," +
            " LAST_NAME=? , EMAIL=? , CARD_IDENTITY=? , ADMISSION_OF_DATE=? , ADDRESS_ID=?" +
            " WHERE ID=? ";

    // Variables sequal para método delete
    private final String SQL_DELETE = " DELETE FROM PATIENTS WHERE ID=? ";

    // Variables sequal para método listar todos
    private final String SQL_SELECT_ALL= " SELECT * FROM PATIENTS ";

    //Variables sequal para método listar FINDbYsTRING

    private final String SQL_SELECT_EMAIL= " SELECT * FROM PATIENTS WHERE EMAIL=? ";







    // Clase como tal.....................


    @Override
    public Patient save(Patient patient) {
        Connection connection = null;
        try{
            AddresssDaoH2 addresssDaoH2 = new AddresssDaoH2();// Instanciar la clas
            addresssDaoH2.save(patient.getAddress());//llamar a un método de esa clase

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getName());
            ps.setString(2,patient.getLastName());
            ps.setString(3,patient.getEmail());
            ps.setInt(4, patient.getCardIdentity());
            ps.setDate(5, Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(6, patient.getAddress().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                patient.setId(rs.getInt(1));
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
        return patient;
    }

    @Override
    public Patient findById(Integer id) {
        Connection connection = null;
        //que voy a hacer con el domiculio si lo que busco es un paciente por id
        Patient patient = null;
        try{

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Como voy a buscar por id a un paciente, por algún lado debemos sacar el domicilio
            AddresssDaoH2 addresssDaoH2 = new AddresssDaoH2();

            while(rs.next()){
                Address address = addresssDaoH2.findById(rs.getInt(7));
                //que voy a hacer con el domiculio si lo que busco es un paciente por id// que se guarde el paciente que se recupere

                patient = new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(), address );
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
        return patient;
    }

    @Override
    public void update(Patient patient) {

        Connection connection = null;
        try{

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

            ps.setString(1,patient.getName());
            ps.setString(2,patient.getLastName());
            ps.setString(3,patient.getEmail());
            ps.setInt(4, patient.getCardIdentity());
            ps.setDate(5,Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(6, patient.getAddress().getId());
            ps.setInt(7,patient.getId());
            ps.executeUpdate();


        }catch(Exception e){
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
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1, id);
            ps.execute();

        }catch(Exception e){
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
    public List<Patient> findAll() {
        Connection connection = null;
        Address address;
        List<Patient> patients = new ArrayList<>();


        try{
            AddresssDaoH2 addresssDaoH2 = new AddresssDaoH2();
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                address = addresssDaoH2.findById(rs.getInt(6));
                patients.add(new Patient(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        address));
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
        return patients;
    }

    @Override
    public Patient findByString(String value) {

        Connection connection = null;

        Patient patient = null;
        try{

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_EMAIL);

            ps.setString(1,value);

            ResultSet rs = ps.executeQuery();
            //Instanciar AddresDao para traer el metodo
            AddresssDaoH2 addresssDaoH2 = new AddresssDaoH2();


            while(rs.next()){
                Address address = addresssDaoH2.findById(rs.getInt(7));
                patient = new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        address);
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

        return patient;
    }


}
