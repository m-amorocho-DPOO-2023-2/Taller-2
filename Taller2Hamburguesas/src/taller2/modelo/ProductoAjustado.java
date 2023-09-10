package taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {
	private ProductoMenu prodBase;
	private ArrayList<Ingrediente> lstIngrOut = new ArrayList<>();
	private ArrayList<Ingrediente> lstIngrIn = new ArrayList<>();
	
	//Constructor
	public ProductoAjustado(ProductoMenu prodBase,ArrayList<Ingrediente> lstIngrOut,ArrayList<Ingrediente> lstIngrIn) {
		this.prodBase = prodBase;
		this.lstIngrOut = lstIngrOut;
		this.lstIngrIn = lstIngrIn;
		}
	
	//métodos

	@Override
	public int getPrecio() {
		int precio = prodBase.getPrecio();
		
		for(Ingrediente ingr:lstIngrIn) {
			precio += ingr.getCostoAdicional();
		}
		return precio;
	}

	@Override
	public String getNombre() {
		String nombreBase = prodBase.getNombre();
		String lstStr1 = "";
		String lstStr2 = "";
		String str1 = "";
		String str2 = "";
		
		if(lstIngrIn.size() != 0){
		for(Ingrediente ingr:lstIngrIn) {
			lstStr1+= ingr.getNombre()+ ", ";
		}
		str1 =  " con " + lstStr1.substring(0, (lstStr1.length() - 2));}
		
		if(lstIngrOut.size() != 0){
			for(Ingrediente ingr:lstIngrOut) {
				lstStr2+= ingr.getNombre()+ ", sin ";
			}
		str2 =  "sin "+lstStr2.substring(0, (lstStr2.length() - 6));}
		String concat = "";
		if(str1 != "") {
			 concat = ", ";
		}
		return nombreBase + str1 + concat +str2;
	}

	@Override
	public String generarTextoFactura() {
		String factura = getNombre() + " ..... "+getPrecio();
		return factura;
	}

	@Override
	public int getCalorias() {
		int calorias = prodBase.getCalorias();
		if(lstIngrIn.size() != 0){
			for(Ingrediente ingr:lstIngrIn) {
				calorias+= ingr.getCalorias();
			}
		}
		return calorias;
	}

	
}
