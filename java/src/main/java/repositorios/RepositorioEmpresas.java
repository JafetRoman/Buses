/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import modelos.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEmpresas {

    private Connection conneccion;

    public RepositorioEmpresas(Connection conneccion) {
        this.conneccion = conneccion;
    }

    public void insertarEmpresa(Empresa empresa) {
        try {
            PreparedStatement preparedStatement = conneccion.prepareStatement("insert into companias (id, nombre, correo, contrasena, telefono) values (seq.nextval,?,?,?,?)");
            preparedStatement.setString(1, empresa.getNombre());
            preparedStatement.setString(2, empresa.getCorreo());
            preparedStatement.setString(3, empresa.getContrasena());
            preparedStatement.setString(4, empresa.getTelefono());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa obtenerEmpresa(String correo) {
        try {
            PreparedStatement preparedStatement = conneccion.prepareStatement("select * from companias where correo = ?");
            preparedStatement.setNString(1, correo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Empresa(resultSet.getInt(1),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("correo"),
                        resultSet.getString("contrasena"));

            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Empresa> obtenerEmpresas() {
        try {
            List<Empresa> empresas = new ArrayList<>();
            PreparedStatement preparedStatement = conneccion.prepareStatement("select * from companias");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empresas.add(new Empresa(resultSet.getInt(1),
                        resultSet.getString("nombre"),
                        resultSet.getString("correo"),
                        resultSet.getString("contrasena"),
                        resultSet.getString("telefono")
                ));
            }
            return empresas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}