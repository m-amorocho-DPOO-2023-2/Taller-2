package taller2.modelo;

public class ProductoMenu implements Producto{

	//Atributos
	private String nombre;
	private int precioBase;
	private int calorias;
	
	//constructor
	public ProductoMenu(String nombre, int precioBase,int calorias) {
		// TODO Auto-generated constructor stub
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
