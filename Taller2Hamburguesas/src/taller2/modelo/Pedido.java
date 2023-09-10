package taller2.modelo;

import java.util.ArrayList;
import java.io.*;

public class Pedido {
	//Atributos
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente; 
	private ArrayList<Producto> productosPedido = new ArrayList<Producto>();
	
	
	//constructor
	public Pedido(String nombreClient,String direccionCliente,int idPedido,int numPedidos) {
		this.nombreCliente = nombreClient;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		Pedido.numeroPedidos = numPedidos;
	}
	
	//Atributos
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		productosPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int precioNeto =0;
		for(Producto item: productosPedido ) {
			precioNeto += item.getPrecio();
		}
		return precioNeto;
	}
	
	private int getPrecioTotalPedido() {
		int neto = getPrecioNetoPedido();
		int iva = getPrecioIVAPedido();
		return neto+iva;
	}
	
	private int getPrecioIVAPedido() {
		int precioTotal = getPrecioNetoPedido();
		return (int)(precioTotal*0.19);
	}
	
	private int getCaloriasPedido() {
		int calorias =0;
		for(Producto item: productosPedido ) {
			calorias += item.getCalorias();
		}
		return calorias;
	}
	
	private String generarTextoFactura() {
		String facturas = "";
		for(Producto item: productosPedido ) {
			facturas += item.generarTextoFactura()+"$ - "+item.getCalorias()+" cal\n";
		}
		String encabezado = "\nNombre del cliente: " +nombreCliente+"\nDireccion: "+direccionCliente;
		String infoped = "\nNumero pedido: " + idPedido;
		String iva = "\nIva: " + getPrecioIVAPedido()+"$";
		String contenido = "\nPrecio neto: " + getPrecioNetoPedido()+"$";
		String total = "\nPrecio total: "+getPrecioTotalPedido()+"$\n";
		String calorias = "\nCalorías de su orden: "+getCaloriasPedido()+" Cal";
		String concat = infoped+ encabezado +"\n\nProductos\n"+facturas + contenido+iva+total+calorias;
		return concat;
	}
	
	public void guardarFactura(File archivo) {
		String text = generarTextoFactura();
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(archivo, true));
		    out.write("\n"+numeroPedidos+"\n" +text);
		    out.close();
		    
		} catch (IOException e) {
			System.out.println("exception occurred" + e);
		}
	}
	
	public String printFactura() {
		String text = generarTextoFactura();
		return text;
	}
	
	public void encontrarPedidoIdentico(File archivo) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			String linea;
			while((linea=br.readLine()) != null) {
				if(linea.equals("Productos")) {
					linea=br.readLine();
					String[] partes = linea.split(" .");
					String firstW = partes[0];
					System.out.println("primera pal->"+firstW);
					while( firstW.equals("Precio")!= false) {
						System.out.println("linea"+linea);
					}
				}
			}
			br.close();}
			
		 catch (Exception e) {
			System.out.println("no se pudo leer el archivo de facturas");
		}
	}
}
