package com.clinicaOdontologica.repository.impl;

import com.clinicaOdontologica.repository.IDao;
import com.clinicaOdontologica.model.Odontologo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:E:/Usuarios/Cecilia Suárez/Desktop/Back End 1/Librerias/H2/bin;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            pstm = con.prepareStatement("INSERT INTO odontologos(matricula, nombre, apellido) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, odontologo.getMatricula());
            pstm.setString(2, odontologo.getNombre());
            pstm.setString(3, odontologo.getApellido());

            pstm.executeUpdate();
            ResultSet keys = pstm.getGeneratedKeys();
            if(keys.next()) {
                odontologo.setId(keys.getInt("id"));
            }
            pstm.close();
            con.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public Optional<Odontologo> buscar(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,matricula,nombre,apellido FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                odontologo = new Odontologo();
                odontologo.setId(result.getInt("id"));
                odontologo.setMatricula(result.getInt("matricula"));
                odontologo.setNombre(result.getString("nombre"));
                odontologo.setApellido(result.getString("apellido"));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo != null ? Optional.of(odontologo) : Optional.empty();
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Odontologo> listarTodos() {
        Connection con = null;
        PreparedStatement pstm = null;
        ArrayList<Odontologo> listadoOdontologos = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            pstm = con.prepareStatement("SELECT *  FROM odontologos");

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Odontologo odontologo = new Odontologo(id, matricula, nombre, apellido);
                listadoOdontologos.add(odontologo);
            }

            pstm.close();
            con.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return listadoOdontologos;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("UPDATE odontologos SET nombre = ?, apellido = ?,matricula = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());


            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;
    }
}
