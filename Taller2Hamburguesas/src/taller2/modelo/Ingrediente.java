package taller2.modelo;

public class Ingrediente{
	//Atributos
	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	//constructor
	public Ingrediente(String nombre, int costoAdicional,int calorias) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias = calorias;
	}

	//métodos

	public String getNombre() {
		return nombre;
	}
	
	public int getCostoAdicional() {
		return costoAdicional;
	}
	
	public int getCalorias() {
		return calorias;
	}
}
