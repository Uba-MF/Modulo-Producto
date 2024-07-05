
package Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.*;

public class Vista extends JFrame {
    
    JLabel lblId,lblNombre,lblPrecio,lblCantidad,lblProveedor,lblIdproducto;
    JTextField txtNombre;
    JSpinner spnPrecio, spnCantidad;
    JComboBox cboProveedor;
    JScrollPane scroll;
    DefaultTableModel model;
    JTable tblDatos;
    JButton btnAgregar,btnEliminar,btnGuardar,btnLimpiar,btnPDF;
    
    public Vista(){
        
        this.setTitle("Formulario Producto");
        this.setSize(470, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new AbsoluteLayout());
        
        //Agrega los labels al formulario
        lblId = new JLabel("ID Producto");
        this.getContentPane().add(lblId, new AbsoluteConstraints(10,20,100,20));
        lblNombre = new JLabel("Nombre");
        this.getContentPane().add(lblNombre, new AbsoluteConstraints(10,50,100,20));
        lblPrecio = new JLabel("Precio");
        this.getContentPane().add(lblPrecio, new AbsoluteConstraints(10,80,100,20));
        lblCantidad = new JLabel("Cantidad");
        this.getContentPane().add(lblCantidad, new AbsoluteConstraints(10,110,100,20));
        lblProveedor = new JLabel("Proveedor");
        this.getContentPane().add(lblProveedor, new AbsoluteConstraints(10,140,100,20));
        lblIdproducto = new JLabel("ID");
        this.getContentPane().add(lblIdproducto, new AbsoluteConstraints(120,20,100,20));
        
        
        //Agrega la caja de texto//
        txtNombre = new JTextField();
        this.getContentPane().add(txtNombre, new AbsoluteConstraints(120,50,100,20));
        
        //Agrega las dos cajas para sumar o restar valores, se conoce como Spinner.
        spnPrecio = new JSpinner();
        this.getContentPane().add(spnPrecio, new AbsoluteConstraints(120,80,100,20));
        spnCantidad = new JSpinner();
        this.getContentPane().add(spnCantidad, new AbsoluteConstraints(120,110,100,20));
        
        //Agrega una lista de selección al formulario, se conoce como ComboBox.
        
        Object items[] = new Object[7]; //Primero crea un vector con el contenido de la lista
        items[0] = "Yupi";
        items[1] = "Coca-cola";
        items[2] = "Colombina";
        items[3] = "Bimbo";
        items[4] = "Ramo";
        items[5] = "Alqueria";
        items[6] = "Aldor";
        
        cboProveedor = new JComboBox(items);
        this.getContentPane().add(cboProveedor, new AbsoluteConstraints(120,140,100,20));
        
        //Agrega los botones al formulario
        btnAgregar = new JButton("Agregar");
        this.getContentPane().add(btnAgregar, new AbsoluteConstraints(300,20,100,20));
        btnEliminar = new JButton("Eliminar");
        this.getContentPane().add(btnEliminar, new AbsoluteConstraints(300,50,100,20));
        btnGuardar = new JButton("Guardar");
        this.getContentPane().add(btnGuardar, new AbsoluteConstraints(300,80,100,20));
        btnLimpiar = new JButton("Limpiar");
        this.getContentPane().add(btnLimpiar, new AbsoluteConstraints(300,110,100,20));
        btnPDF = new JButton("Ver PDF");
        this.getContentPane().add(btnPDF, new AbsoluteConstraints(300,140,100,20));
        
        //Crear la tabla donde se mostrarán los registros
        tblDatos = new JTable();
        scroll = new JScrollPane();
        model = new DefaultTableModel();
        model.addColumn("Id Producto");
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Proveedor");
        tblDatos.setModel(model);
        scroll.setViewportView(tblDatos);
        this.getContentPane().add(scroll, new AbsoluteConstraints(10,200,430,250));
        this.setVisible(true);
    }
}

