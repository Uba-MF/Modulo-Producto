
package Producto;


public class Producto {
    
int idproducto;
	String nombre;
	int precio;
	int cantidad;
	String proveedor;
	
	public Producto() {
		
	}
	
	public Producto(int idproducto, String nombre, int precio, int cantidad, String proveedor) {
		super();
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.proveedor = proveedor;
	}
	
	public Producto(String nombre, int precio, int cantidad, String proveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.proveedor = proveedor;
	}



	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
	    return "Producto [idproducto=" + idproducto + ", nombre=" + nombre + ", precio=" + precio + ", cantidad="
	    + cantidad + ", proveedor=" + proveedor + "]";
	}
}

