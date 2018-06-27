/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortomiercoles_poo;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;
/**
 *
 * @author LN710Q
 */
public class Cinepolix extends JFrame{
    public JLabel lblNombre, lblDirector, lblPais,lblClasificacion,lblaño,lblestado;
    public JTextField nombre, director,año,clasificacion,pais,estado;
    public JComboBox Clasificacion;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;
    public JButton buscar, eliminar, insertar,actualizar;

    private static final int ANCHOC = 175, ALTOC = 20;

    DefaultTableModel tm;

    public Cinepolix() {
        super("Cinepolix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        
        //AGREGANDO TODOS LOS COMPONENTES A LA PANTALLA
        //LABELS
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblaño);
        container.add(lblestado);
        //JTEXTFIELDS
        container.add(Clasificacion);
        container.add(pais);
        container.add(nombre);
        container.add(director);
        container.add(año);
        
        //CHECKBOX
        container.add(si);
        container.add(no);
        //JBUTTONS
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        
        setSize(900, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("País");
        lblClasificacion = new JLabel("Clasificación");
        lblaño = new JLabel("Año");
        lblestado = new JLabel("Estado");

        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 100, ANCHOC, ALTOC);
        lblPais.setBounds(400, 60, ANCHOC, ALTOC);
        lblClasificacion.setBounds(10,140, ANCHOC, ALTOC);
        lblaño.setBounds(400, 100, ANCHOC, ALTOC);
        lblestado.setBounds(10, 170, ANCHOC, ALTOC);
    }

    public final void formulario() {
        //CREACION DE TEXT FIELDS
        
        Clasificacion = new JComboBox();
        pais = new JTextField();
        nombre = new JTextField();
        director = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        año = new JTextField();
        

        table = new JPanel();
        Clasificacion.addItem("18A");
        Clasificacion.addItem("14A");
        Clasificacion.addItem("R");
        Clasificacion.addItem("G");
        Clasificacion.addItem("PG");
        Clasificacion.addItem("A");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        //TAMAÑO Y LUGAR DE LOS TEXTFIELDS
        Clasificacion.setBounds(140,140,ANCHOC,ALTOC);
        año.setBounds(500,100, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        pais.setBounds(500, 60, ANCHOC, ALTOC);
        director.setBounds(140, 100, 80, ALTOC);
        si.setBounds(140, 180, 50, ALTOC);
        no.setBounds(210, 180, 50, ALTOC);

        //TAMAÑO Y LUGAR DE LOS BOTONES 
        buscar.setBounds(350, 10, 100, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        //JTABLE
        resultados = new JTable();
        table.setBounds(100, 250, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
          
                    default:
                        return Boolean.class;
                }
            }
        };
        //tm.addColumn("idMovie");
        tm.addColumn("Nombre");
        tm.addColumn("Año");
        tm.addColumn("Director");
        tm.addColumn("pais");
        tm.addColumn("clasificacion");
       
       tm.addColumn("en_proyeccion");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            System.out.println(fi);
            tm.addRow(new Object[]{fi.getNombre(), fi.getAño(), fi.getDirector(), fi.getPais(),fi.getClasificacion(),fi.getExistencia()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                
                Filtro  f=new Filtro(nombre.getText(), director.getText(), pais.getText(),
                        Clasificacion.getSelectedItem().toString(),Integer.parseInt(pais.getText()), true);
                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro  f=new Filtro(nombre.getText(), director.getText(), pais.getText(),Clasificacion.getSelectedItem().toString(),Integer.parseInt(pais.getText()), true);
                if (no.isSelected()) {
                    f.setExistencia(false);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito");
                    
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito");
                    
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(nombre.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro buscado no se ha encontrado");
                } else {
                    nombre.setText(f.getNombre());
                    Clasificacion.setSelectedItem(f.getClasificacion());
                    pais.setText(Integer.toString(f.getAño()));

                    if (f.getExistencia()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        
    }
    
    
    public static void main(String [] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cinepolix().setVisible(true);
            }
        });
    }
 
}
