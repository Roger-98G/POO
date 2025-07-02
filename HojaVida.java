package grafica.HojaVida;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class HojaVida extends JFrame {
    private JPanel panel1;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textEmail;
    private JTextField textTelefono;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femeninoRadioButton;
    private JRadioButton otrosRadioButton;
    private JCheckBox leerCheckBox;
    private JCheckBox viajarCheckBox;
    private JCheckBox deportesCheckBox;
    private JCheckBox otrosCheckBox;
    private JTextArea escribaAquiTextArea;
    private JComboBox comboInstruccion;
    private JButton salirButton;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JTable TablaVida;

    private void cargarTabla() {
        String[] columnas = {"Informacion academica"};
        Object[][] datos = {
                {" "}
        };
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        TablaVida.setModel(modelo);
    }

    public HojaVida() {
        setTitle("Curriculum");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        cargarTabla();

        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(masculinoRadioButton);
        grupoGenero.add(femeninoRadioButton);
        grupoGenero.add(otrosRadioButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombre.getText();
                String apellido = textApellido.getText();
                String email = textEmail.getText();
                String telefono = textTelefono.getText();

                String genero = "";
                if (masculinoRadioButton.isSelected()) genero = "Masculino";
                else if (femeninoRadioButton.isSelected()) genero = "Femenino";
                else if (otrosRadioButton.isSelected()) genero = "Otros";

                String hobbies = "";
                if (leerCheckBox.isSelected()) hobbies += "Leer ";
                if (viajarCheckBox.isSelected()) hobbies += "Viajar ";
                if (deportesCheckBox.isSelected()) hobbies += "Deportes ";
                if (otrosCheckBox.isSelected()) hobbies += "Otros ";

                String experiencia = escribaAquiTextArea.getText();
                String nivel = (String) comboInstruccion.getSelectedItem();

                String info = String.format("Nombre: %s %s | Email: %s | Tel: %s | Género: %s | Hobbies: %s | Nivel: %s | Perfil: %s",
                        nombre, apellido, email, telefono, genero, hobbies, nivel, experiencia);

                // Agregar a la tabla
                DefaultTableModel model = (DefaultTableModel) TablaVida.getModel();
                model.addRow(new Object[]{info});

                // Guardar en archivo de texto
                try (FileWriter writer = new FileWriter("C:/Users/TUF F15/Desktop/hoja_vida.txt", true)) { // 'true' para añadir al final
                    writer.write(info + "\n");
                    JOptionPane.showMessageDialog(null, "Información guardada en hoja_vida.txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar en archivo: " + ex.getMessage());
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNombre.setText("");
                textApellido.setText("");
                textEmail.setText("");
                textTelefono.setText("");
                grupoGenero.clearSelection();
                leerCheckBox.setSelected(false);
                viajarCheckBox.setSelected(false);
                deportesCheckBox.setSelected(false);
                otrosCheckBox.setSelected(false);
                escribaAquiTextArea.setText("");
                comboInstruccion.setSelectedIndex(0);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HojaVida().setVisible(true));
    }
}
