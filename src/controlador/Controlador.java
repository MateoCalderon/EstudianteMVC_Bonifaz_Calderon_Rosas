package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.TransaccionesBD;
import vista.VistaEstudiante;

public class Controlador implements ActionListener {

    VistaEstudiante vista;
    TransaccionesBD modelo;
    Estudiante estudiante;

    public Controlador(VistaEstudiante vista, TransaccionesBD modelo, Estudiante estudiante) {
        this.vista = vista;
        this.modelo = modelo;
        this.estudiante = estudiante;
        vista.btnInsertar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnSiguiente.addActionListener(this);
        vista.btnAnterior.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Estudiante");
        vista.setLocationRelativeTo(null);
    }

    public void limpiar() {
        vista.txtBuscar.setText(null);
        vista.txtNombre.setText(null);
        vista.cbGenero.setSelectedIndex(0);
        vista.cbMateria.setSelectedIndex(0);
        vista.txtNota.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnInsertar) {
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            if (modelo.insertar(estudiante)) {
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se inserto el registro");
                limpiar();
            }
        }
        if (e.getSource() == vista.btnModificar) {
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            try {
                if (modelo.modificar(estudiante)) {
                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro correctamente");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha modificado el registro");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnEliminar) {
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            try {
                if (modelo.eliminar(estudiante)) {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el registro correctamente");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha eliminado el registro");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnBuscar) {
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            try {

                if (modelo.buscar(estudiante)) {
                    JOptionPane.showMessageDialog(null, "Se ha encontrado el registro correctamente");
                    vista.txtNombre.setText(estudiante.getNombre());
                    vista.cbGenero.setSelectedItem(estudiante.getGenero());
                    vista.cbMateria.setSelectedItem(estudiante.getMateria());
                    vista.txtNota.setText(String.valueOf(estudiante.getNota()));
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el registro");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnSiguiente) {

            if (modelo.siguiente(estudiante)) {
                vista.txtNombre.setText(estudiante.getNombre());
                vista.cbGenero.setSelectedItem(estudiante.getGenero());
                vista.cbMateria.setSelectedItem(estudiante.getMateria());
                vista.txtNota.setText(String.valueOf(estudiante.getNota()));
                vista.txtBuscar.setText(String.valueOf(estudiante.getCodigo()));
            } else {
                JOptionPane.showMessageDialog(null, "No existen registros");
            }
        }
        if (e.getSource() == vista.btnAnterior) {

            if (modelo.anterior(estudiante)) {
                vista.txtNombre.setText(estudiante.getNombre());
                vista.cbGenero.setSelectedItem(estudiante.getGenero());
                vista.cbMateria.setSelectedItem(estudiante.getMateria());
                vista.txtNota.setText(String.valueOf(estudiante.getNota()));
                vista.txtBuscar.setText(String.valueOf(estudiante.getCodigo()));
            } else {
                JOptionPane.showMessageDialog(null, "No existen registros");
            }
        }
        if (e.getSource() == vista.btnLimpiar) {
            vista.txtBuscar.setText(null);
            vista.txtNombre.setText(null);
            vista.cbGenero.setSelectedIndex(0);
            vista.cbMateria.setSelectedIndex(0);
            vista.txtNota.setText(null);
        }
    }
}
