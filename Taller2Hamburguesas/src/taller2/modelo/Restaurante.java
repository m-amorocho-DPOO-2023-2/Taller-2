package taller2.modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurante {

	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	
	public  ArrayList<Combo> lstMenuCombos = new ArrayList<>();
	public  ArrayList<ProductoMenu> LstProducto = new ArrayList<>();
	public  ArrayList<Ingrediente> LstIngredientes = new ArrayList<>();
	public  ArrayList<Bebida> lstBebidas = new ArrayList<>();
	private ArrayList<Pedido> LstPedidos = new ArrayList<>();
	private int numPedido = 0;
	private File archivo = new File("archivoFacturas.txt");
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************
	public Restaurante() {
	}

	// ************************************************************************
	// Métodos
	// ************************************************************************
	
	public void cargarInformacionRestaurante(String archivoIngredientes,String archivoMenu,String archivoCombos,String archivoBebidas){
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarBebidas(archivoBebidas);
	}

	private void cargarIngredientes(String archivoIngredientes) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
			String linea;
			while((linea=br.readLine()) != null) {
				String[] partes = linea.split(";");
				String nombreIngrediente = partes[0];
				int precioIngrediente = Integer.parseInt(partes[1]);
				int caloriasIngrediente = Integer.parseInt(partes[2]);
				Ingrediente elIngrediente = new Ingrediente(nombreIngrediente,precioIngrediente,caloriasIngrediente);
				LstIngredientes.add(elIngrediente);
				}
			br.close();
			
		} catch (Exception e) {
			System.out.println("no se pudo leer el archivo Ingredientes");
		}

	}
	private void cargarMenu(String archivoMenu) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
			String linea;
			while((linea=br.readLine()) != null) {
				String[] partes = linea.split(";");
				String nombreProducto = partes[0];
				int precioProducto = Integer.parseInt(partes[1]);
				int caloriasProducto = Integer.parseInt(partes[2]);
				ProductoMenu elProducto = new ProductoMenu(nombreProducto,precioProducto,caloriasProducto);
				LstProducto.add(elProducto);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("no se pudo leer el archivo menu");
		}

	}
	private void cargarCombos(String archivoCombos) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
			String linea;
			while((linea=br.readLine()) != null) {
				ArrayList<String> lstItemsCombo = new ArrayList<>();
				String[] partes = linea.split(";");
				String nombreCombo = partes[0];
				double desc = Double.parseDouble(partes[1].replaceFirst("%", ""))/100;
				String productoBase1 = partes[2];
				String productoBase2 = partes[3];
				String productoBase3 = partes[4];
				lstItemsCombo.add(nombreCombo);
				lstItemsCombo.add(productoBase1);
				lstItemsCombo.add(productoBase2);
				lstItemsCombo.add(productoBase3);
				Combo elCombo = new Combo(nombreCombo,desc);
				lstMenuCombos.add(elCombo);
				for(String itemCombo:lstItemsCombo ) {
					for(ProductoMenu itemMenu: LstProducto) {
						if(itemMenu.getNombre().equals(itemCombo)) {
							elCombo.agregarItemACombo(itemMenu);
						}
					}
				}
			}
			
			br.close();
		} catch (Exception e) {
			System.out.println("no se pudo leer el archivo combos");
		}

	}
	
	private void cargarBebidas(String archivoBebidas){
		try {
	
		BufferedReader br = new BufferedReader(new FileReader(archivoBebidas));
		String linea;
		while((linea=br.readLine()) != null) {
			String[] partes = linea.split(";");
			String nombreBebida = partes[0];
			int precioBebida = Integer.parseInt(partes[1]);
			int caloriasBeb = Integer.parseInt(partes[2]);
			Bebida laBebida = new Bebida(nombreBebida,precioBebida,caloriasBeb);
			lstBebidas.add(laBebida);
			}
		br.close();
		
	} catch (Exception e) {
		System.out.println("no se pudo leer el archivo bebidas");}
	}
	public  ArrayList<Ingrediente> getIngrediente(){
		return LstIngredientes;
	}
	
	public ArrayList<ProductoMenu> getMenuBase(){
		return LstProducto;
	}
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		numPedido+= 1;
		Pedido elPedido = new Pedido(nombreCliente,direccionCliente,numPedido,numPedido);
		LstPedidos.add(elPedido);
		}
	
	public void cerrarYGuardarPedido() {
		Pedido orden = getPedidoEnCurso();
		orden.encontrarPedidoIdentico(archivo);
		orden.guardarFactura(archivo);
	}
	
	public Pedido getPedidoEnCurso() {
		Pedido ordenActual = LstPedidos.get(numPedido-1);
		return ordenActual;
	}
	
	// METODOS ADICIONALES
	
	public void agregarProductosPedido(int numeroProd,int opcionTipoMenu) {
		Pedido orden = getPedidoEnCurso();
		if (opcionTipoMenu == 1) {
			ProductoMenu prodBaseSel = LstProducto.get(numeroProd-1);
			orden.agregarProducto(prodBaseSel);
		}
		else if (opcionTipoMenu == 2) {
			Combo combSel = lstMenuCombos.get(numeroProd-1);
			orden.agregarProducto(combSel);
		}
		else if(opcionTipoMenu == 3) {
			Bebida bebidaSel = lstBebidas.get(numeroProd-1);
			orden.agregarProducto(bebidaSel);
		}
	}
	
	public void addProductModificado(int numeroProd,ArrayList<Integer> lstIngrsIn,ArrayList<Integer> lstIngrsOut) {
		ArrayList<Ingrediente> ingrsIn = new ArrayList<Ingrediente>();
		ArrayList<Ingrediente> ingrsOut = new ArrayList<Ingrediente>();
		ProductoMenu prodBase = LstProducto.get(numeroProd-1);
		if(lstIngrsIn.size() != 0) {
			for(int numeroIngr:lstIngrsIn) 
			{
				Ingrediente ingre = LstIngredientes.get(numeroIngr-1);
				ingrsIn.add(ingre);
			}
		}
		if(lstIngrsOut.size() != 0) {
			for(int numeroIngr:lstIngrsOut) 
			{
				Ingrediente ingre = LstIngredientes.get(numeroIngr-1);
				ingrsOut.add(ingre);
			}
		}
		ProductoAjustado prodAd = new ProductoAjustado(prodBase, ingrsOut, ingrsIn);
		Pedido orden = getPedidoEnCurso();
		orden.agregarProducto(prodAd);
	}
	
	
	// METODOS PARA IMPRIMIR
	
	public ArrayList<Combo> getLstMenuCombos(){
		return lstMenuCombos;
	}
	
	public ArrayList<Bebida> getLstBebidas(){
		return lstBebidas;
	}
	
	
	public String returnFactura() {
		Pedido orden = getPedidoEnCurso();
		String printFact = orden.printFactura();
		return printFact;
	}
}



	