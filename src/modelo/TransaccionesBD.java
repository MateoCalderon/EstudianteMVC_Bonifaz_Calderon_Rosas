package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TransaccionesBD extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    int codigo = -1;
    int[] datos = null;
    int i = 0, n = 0;
    int nroCodigo = 0;

    public boolean buscar(Estudiante estudiante) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, estudiante.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean insertar(Estudiante estudiante) {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into estudiante (codigoE,nombreE,nota,sexo,materia) values(?,?,?,?,?)");
            ps.setInt(1, estudiante.getCodigo());
            ps.setString(2, estudiante.getNombre());
            ps.setDouble(3, estudiante.getNota());
            ps.setString(4, estudiante.getGenero());
            ps.setString(5, estudiante.getMateria());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean modificar(Estudiante estudiante) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update estudiante set nombreE=?,nota=?,sexo=?,materia=? where codigoE=?");
            ps.setString(1, estudiante.getNombre());
            ps.setDouble(2, estudiante.getNota());
            ps.setString(3, estudiante.getGenero());
            ps.setString(4, estudiante.getMateria());
            ps.setInt(5, estudiante.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean eliminar(Estudiante estudiante) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("delete from estudiante where codigoE=?");
            ps.setInt(1, estudiante.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean siguiente(Estudiante estudiante) {
        try {
            int i = 0, n = 0, aux;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
            datos = new int[n];
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[i] = rs.getInt("codigoE");
                i++;
            }
            aux = n - 1;

            if (codigo >= aux) {
                JOptionPane.showMessageDialog(null, "ULTIMO de la lista");
                codigo = aux - 1;
            }
            codigo++;
            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(datos[codigo])));
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));
                estudiante.setCodigo(datos[codigo]);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean anterior(Estudiante estudiante) {
        try {
            int i = 0, n = 0;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
            datos = new int[n];
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[i] = rs.getInt("codigoE");
                i++;
            }
            if (codigo <= 0) {
                codigo = 1;
                JOptionPane.showMessageDialog(null, "PRIMERO de la lista");
            }
            codigo--;

            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(datos[codigo])));
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));
                estudiante.setCodigo(datos[codigo]);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }
}
