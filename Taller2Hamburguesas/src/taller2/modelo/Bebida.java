package taller2.modelo;

public class Bebida implements Producto {
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public Bebida(String nombre, int precioBase,int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		String factura = getNombre() +" ..... "+ getPrecio();
		return factura;
	}
	@Override
	public int getCalorias() {
		return calorias;
	}

}
