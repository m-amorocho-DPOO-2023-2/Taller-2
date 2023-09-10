package taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	//Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> lstItemsCombo = new ArrayList<Producto>();

	//constructor
	public Combo(String nombreCombo,double descuento) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		
	}
	
	public void agregarItemACombo(Producto itemCombo){
		lstItemsCombo.add(itemCombo);
	}		
				
	@Override
	public int getPrecio() {
		int precioBase = 0;
		for(Producto item: lstItemsCombo) {
			precioBase += item.getPrecio();
		}

		return (int)(precioBase*(1-descuento));
	}

	@Override
	public String getNombre() {
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		String factura = getNombre()+" ..... "+getPrecio();
		return factura;
	}
	
	//metodo adicional
	public  ArrayList<Producto> getComponentsCombo(){
		return lstItemsCombo;
	}

	@Override
	public int getCalorias() {
		int calorias = 0;
		for(Producto item: lstItemsCombo) {
			calorias += item.getCalorias();
		}

		return calorias;
	}



}
