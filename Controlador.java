
package Producto;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Controlador implements ActionListener,MouseListener  {
    Vista v;
    Producto p,p1;
    daoProducto dao;
    int id = 0;
    ArrayList<Producto> lista = null;
    
    public Controlador(){
        
        v = new Vista();
        dao = new daoProducto();
        p1 = new Producto();
        v.btnAgregar.addActionListener(this);
        v.btnEliminar.addActionListener(this);
        v.btnGuardar.addActionListener(this);
        v.btnLimpiar.addActionListener(this);
        v.btnPDF.addActionListener(this);
        v.tblDatos.addMouseListener(this);
        refresh();
    }
    
    public static void main(String[]ar){
        Controlador con = new Controlador();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == v.btnAgregar){
            
            p = new Producto();
            p1 = new Producto();
            p.setNombre(v.txtNombre.getText());
            p.setPrecio(Integer.parseInt(v.spnPrecio.getValue().toString()));
            p.setCantidad(Integer.parseInt(v.spnCantidad.getValue().toString()));
            p.setProveedor(v.cboProveedor.getSelectedItem().toString());
            
            if(!dao.create(p)){
                JOptionPane.showMessageDialog(this.v, "No se insertó registro");
            }
            
            LimpiarCampos();
        }
        
        
        if (e.getSource() == v.btnEliminar){
            int x = JOptionPane.showConfirmDialog(this.v, "¿Estás seguro de eliminar este registro?");
            if(x == 0 && id > 0){
                if(!dao.delete(id)){
                    JOptionPane.showMessageDialog(this.v, "No se eliminó el registro");
                }
            }
        }
        
        if (e.getSource() == v.btnGuardar){
            p1.setNombre(v.txtNombre.getText());
            p1.setPrecio(Integer.parseInt(v.spnPrecio.getValue().toString()));
            p1.setCantidad(Integer.parseInt(v.spnCantidad.getValue().toString()));
            p1.setProveedor(v.cboProveedor.getSelectedItem().toString());
            if(!dao.update(p1)){
                JOptionPane.showMessageDialog(this.v, "No se actualizó el registro");
            }
        }
        
        if (e.getSource() == v.btnLimpiar){
            LimpiarCampos();
        }
        
        if (e.getSource() == v.btnPDF){

        try {
            FileOutputStream archivo;
            File file = new File("C:\\Users\\esperanza\\Documents\\NetBeansProjects\\Proyecto1\\src\\pdf\\reporte.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            // Agregar imagen al Pdf
            Image img = Image.getInstance("C:\\Users\\esperanza\\Documents\\NetBeansProjects\\Proyecto1\\src\\img\\img.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(100,100);
            doc.add(img);
            
            // Agregar titulo al Pdf
            Paragraph pa = new Paragraph(10);
            Font negrita= new Font(FontFamily.TIMES_ROMAN,16,Font.BOLD, BaseColor.BLACK);
            pa.add(Chunk.NEWLINE);
            pa.add(Chunk.NEWLINE);
            pa.add("CATALOGO DE PRODUCTOS");
            pa.add(Chunk.NEWLINE);
            pa.add(Chunk.NEWLINE);
            pa.add(Chunk.NEWLINE);
            pa.setAlignment(Element.ALIGN_CENTER);
            doc.add(pa);
            
            // Encabezado de la tabla de datos
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            PdfPCell c1 = new PdfPCell(new Phrase("Id producto", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell c5 = new PdfPCell(new Phrase("Proveedor", negrita));
            
            // Alineación de los encabezados
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            // Agregar color al encabezado
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            // Agregar la celdas a la tabla
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            tabla.addCell(c5);
            
            
            // Agregar los registros 
            for(Producto pro : lista){
                tabla.addCell(""+pro.getIdproducto());
                tabla.addCell(pro.getNombre());
                tabla.addCell(""+pro.getPrecio());
                tabla.addCell(""+pro.getCantidad());
                tabla.addCell(pro.getProveedor());
            }
            doc.add(tabla);
            
            Paragraph pa2 = new Paragraph(10);
            pa2.add(Chunk.NEWLINE);
            pa2.add(Chunk.NEWLINE);
            pa2.add("Número de registros: "+lista.size());
            pa2.add(Chunk.NEWLINE);
            pa2.add(Chunk.NEWLINE);
            pa2.add(Chunk.NEWLINE);
            pa2.add(Chunk.NEWLINE);
            pa2.add("Creado por: Ubadel Mendoza");
            pa2.setAlignment(Element.ALIGN_LEFT);
            doc.add(pa2);
            
            // Cerrar el documento
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
            
            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(this.v, "Error al crear el documento Pdf");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this.v, "Error al crear el documento Pdf");
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this.v, "Error al crear el documento Pdf");
            }
        }
        refresh();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == v.tblDatos){
            int fila = v.tblDatos.getSelectedRow();
            id = Integer.parseInt(v.tblDatos.getValueAt(fila,0).toString());
            p1 = dao.read(id);
            v.lblIdproducto.setText(""+p1.getIdproducto());
            v.txtNombre.setText(p1.getNombre());
            v.spnPrecio.setValue(p1.getPrecio());
            v.spnCantidad.setValue(p1.getCantidad());
            v.cboProveedor.setSelectedItem(p1.getProveedor());
        }
    }
    
    public void refresh(){
        
        while(v.model.getRowCount()>0){
            v.model.removeRow(0);
        }
        lista = dao.read();
        for(Producto p: lista){
            Object item[] = new Object[5];
            item[0] = p.getIdproducto();
            item[1] = p.getNombre();
            item[2] = p.getPrecio();
            item[3] = p.getCantidad();
            item[4] = p.getProveedor();
            v.model.addRow(item);
        }
        v.tblDatos.setModel(v.model);
    }
    
    public void LimpiarCampos(){
        
        v.txtNombre.setText("");
        v.spnPrecio.setValue(0);
        v.spnCantidad.setValue(0);
        v.cboProveedor.setSelectedIndex(0);
        v.lblIdproducto.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
