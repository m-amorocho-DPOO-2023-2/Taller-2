package taller2.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import taller2.modelo.Bebida;
import taller2.modelo.Combo;
import taller2.modelo.Ingrediente;
import taller2.modelo.Producto;
import taller2.modelo.ProductoMenu;
import taller2.modelo.Restaurante;

public class ConsolaAplicacion {

	private Restaurante elCorral;

	public void ejecutarOpcion()
	{
		System.out.println("---- RESTAURANTE HAMBURGUESAS :)----");
		elCorral = new Restaurante();
		elCorral.cargarInformacionRestaurante("src/data/ingredientes.txt", "src/data/menu.txt", "src/data/combos.txt","src/data/bebidas.txt");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					System.out.println("\n-------- MENU --------\n");
					ArrayList<ProductoMenu> lstProductos = elCorral.getMenuBase();
					ArrayList<Combo> lstCombos = elCorral.getLstMenuCombos();
					ArrayList<Bebida> lstBebidas = elCorral.getLstBebidas();
					System.out.println("*Productos base*");
					for(ProductoMenu item: lstProductos) {
						System.out.println(item.getNombre()+" ......" +item.getPrecio()+"$");
					}
					System.out.println("\n*Bebidas*");
					for(Bebida item: lstBebidas){
						System.out.println(item.getNombre()+" ......" +item.getPrecio()+"$");
					}
					System.out.println("\n*Combos*");
					for(Combo item: lstCombos){
						System.out.println(item.getNombre()+" ......" +item.getPrecio()+"$");
					}
					
				}
					

				else if (opcion_seleccionada == 2) {
					System.out.println("\n-------- INICIANDO PEDIDO --------");
					tomarNuevoPedido();
				}


				else if (opcion_seleccionada == 3)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}

				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarMenu()
	{
		System.out.println("\nOPCIONES DE LA APLICACIÓN\n");
		System.out.println("1. MOSTRAR MENU");
		System.out.println("2. INICIAR PEDIDO");
		System.out.println("3. SALIR");

	}

	public void tomarNuevoPedido(){
		System.out.println("\n--- NUEVO PEDIDO ---");
		String nombreCliente= input("\nIntroduzca su nombre");
		String direccionCliente= input("\nIntroduzca su dirección");
		elCorral.iniciarPedido(nombreCliente, direccionCliente);
		System.out.println("\n*PRODUCTOS*\n");

		boolean seguir = true;
		while(seguir) 
		{
			System.out.println("1. Menú general");
			System.out.println("2. Combos");
			System.out.println("3. Bebidas");
			int opcion_seleccionada = Integer.parseInt(input("\nSeleccione el tipo de producto a ordenar"));
			
			if (opcion_seleccionada == 1) {
				pedidoMenuGeneral();}
			
			else if(opcion_seleccionada == 2) {
				pedidoMenuCombo();
				}
			else if(opcion_seleccionada ==3) {
				ArrayList<Bebida> lstBebidas = elCorral.getLstBebidas();
				int numera = 1;
				for(Bebida bebida: lstBebidas) {
					System.out.println(numera+". "+bebida.getNombre()+" ... " + bebida.getPrecio()+"$");
					numera += 1;
				}
				
				int opcionBeida = Integer.parseInt(input("\nSeleccione su producto"));
				elCorral.agregarProductosPedido(opcionBeida, 3);
			}
			
			else {
				System.out.println("Opción inválida");
			}
			System.out.println("Desea agregar otro producto?\n1.Si\n2.No");
			int seg = Integer.parseInt(input("\nMarque su opción"));
			if(seg == 2) {
				seguir = false;
				elCorral.cerrarYGuardarPedido();
				String text = elCorral.returnFactura();
				System.out.println("\n----- FACTURA ------");
				System.out.println("Su pedido: ");
				System.out.println(text);
				System.out.println("\n------- FIN DEL PEDIDO -------");
			}
		}
	
	}
		

	public void pedidoMenuGeneral() 
	{
		ArrayList<Integer> lstIngrIn = new ArrayList<Integer>();
		ArrayList<Integer> lstIngrOut = new ArrayList<Integer>();
		ArrayList<Ingrediente> ingrs = elCorral.getIngrediente();
		ArrayList<ProductoMenu> lstProductosBase = elCorral.getMenuBase();
		int enume = 1;
		for(ProductoMenu prodctB:lstProductosBase) {
			System.out.println(enume+". "+prodctB.getNombre()+" ...... "+prodctB.getPrecio());
			enume+= 1;
		}
		
		
		int productoSelecc = Integer.parseInt(input("\nSeleccione su producto"));
		System.out.println("Desea agregar un ingrediente a su producto?\n1. Si\n2. No");
		
		int modif = Integer.parseInt(input("\nMarque su opción"));
		
		//para productos modificados
		// 1-> productos modificados con ingredientes extra
		if(modif == 1) {
			boolean masIngrs = true;
			while(masIngrs) 
			{
			int i = 1;
			System.out.println("\nIngrediente -----> precio extra");
			for(Ingrediente ingr:ingrs) {
				System.out.println(i+". "+ingr.getNombre()+" ....... "+ingr.getCostoAdicional()+"$");
				i += 1;}
			int ingrIn = Integer.parseInt(input("\nSeleccione el ingrediente a añadir"));
			lstIngrIn.add(ingrIn);
			System.out.println("Desea añadir otro ingrediente?\n1. Si\n2. No");
			int opMasIngrs = Integer.parseInt(input("\nMarque su opción"));
			if(opMasIngrs == 2) {
				masIngrs = false;
				}
			}
			}
			//2-> productos a remover
		System.out.println("Desea remover algun ingrediente a su producto?\n1. Si\n2. No");
		int modifOut = Integer.parseInt(input("\nMarque su opción"));
		if(modifOut == 1) 
			{
				boolean masIngrsOut = true;
				while(masIngrsOut) {
				int i = 1;
				for(Ingrediente ingr:ingrs) {
					System.out.println(i+". "+ingr.getNombre());
					i += 1;}
				int ingrOut = Integer.parseInt(input("\nSeleccione el ingrediente a remover"));
				lstIngrOut.add(ingrOut);
				System.out.println("Desea remover otro ingrediente?\n1. Si\n2. No");
				int opMenosIngrs = Integer.parseInt(input("\nMarque su opción"));
				if(opMenosIngrs == 2) {
					masIngrsOut = false;
				}
				}
			}

		if (modifOut == 2 && modif ==2) {
			elCorral.agregarProductosPedido(productoSelecc, 1);
		}
		else {
			elCorral.addProductModificado(productoSelecc, lstIngrIn, lstIngrOut);
		}

	}

	public void pedidoMenuCombo() {

		ArrayList<Combo> lstCombs = elCorral.getLstMenuCombos();
		int numera = 1;
		for(Combo comb: lstCombs) {
			ArrayList<Producto> componentes = comb.getComponentsCombo();
			String components = "";
			for(Producto item: componentes) {
				components += item.getNombre()+", ";
			}
			System.out.println(numera+". "+comb.getNombre()+": "+components.substring(0, components.length()-2)+" ..... "+comb.getPrecio()+"$");
			numera += 1;
		}
		
		int opcionCombo = Integer.parseInt(input("\nSeleccione su producto"));
		elCorral.agregarProductosPedido(opcionCombo, 2);
	}

	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		ConsolaAplicacion consola = new ConsolaAplicacion();
		consola.ejecutarOpcion();

	}
}
