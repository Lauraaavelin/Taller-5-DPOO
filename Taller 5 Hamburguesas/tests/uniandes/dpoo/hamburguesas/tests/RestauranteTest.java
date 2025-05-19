package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class RestauranteTest {
	
	
	private ArrayList<Pedido> pedidos;
	private Restaurante resto;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> menuCombos;
	private Pedido pedidoEnCurso;
	private ArrayList<ProductoMenu>  proccombo1;
	private ArrayList<ProductoMenu>  proccombo2;
	private ArrayList<ProductoMenu>  proccombo3;
	private Combo combo1;
	private Combo combo2;
	private Combo combo3;
	 @BeforeEach
	    public void setUp() {
		
		Pedido ped = new Pedido("Laura","Bogotá"); 
		Pedido ped1 = new Pedido("Ari","Kennedy");
		pedidoEnCurso = new Pedido("Lau&Ari","Felicidad");
		pedidos = new ArrayList<Pedido>();
		pedidos.add(ped1);
		pedidos.add(ped);
		resto = new Restaurante();
		
		Ingrediente ing = new Ingrediente("Tomate",20);
		Ingrediente ing1 = new Ingrediente("Tocineta",50);
		Ingrediente ing2 = new Ingrediente("Cebolla Caramelizada",30);
		Ingrediente ing3 = new Ingrediente("Queso",80);
		Ingrediente ing4 = new Ingrediente("Carne",100);
		Ingrediente ing5 = new Ingrediente("Pepinillos",40);
		ingredientes = new ArrayList<Ingrediente>();
		ingredientes.add(ing5);
		ingredientes.add(ing4);
		ingredientes.add(ing3);
		ingredientes.add(ing2);
		ingredientes.add(ing1);
		ingredientes.add(ing);
		
		menuBase= new  ArrayList<ProductoMenu>();
		ProductoMenu proc = new ProductoMenu("Hamburguesa",2000);
		ProductoMenu proc1 = new ProductoMenu("Papas Francesas",200);
		ProductoMenu proc2 = new ProductoMenu("Gaseosa",500);
		ProductoMenu proc3 = new ProductoMenu("Hamburguesa Doble",2600);
		ProductoMenu proc4 = new ProductoMenu("Hamburguesa Costeña",2100);
		menuBase.add(proc4);
		menuBase.add(proc3);
		menuBase.add(proc2);
		menuBase.add(proc1);
		menuBase.add(proc);
		
		
		menuCombos = new ArrayList<Combo>();
		proccombo1= new ArrayList<ProductoMenu>();
		proccombo2= new ArrayList<ProductoMenu>();
		proccombo3= new ArrayList<ProductoMenu>();
		
		proccombo1.add(proc);
		proccombo1.add(proc1);
		proccombo1.add(proc2);
		
		proccombo2.add(proc3);
		proccombo2.add(proc1);
		proccombo2.add(proc2);
		
		proccombo3.add(proc4);
		proccombo3.add(proc1);
		proccombo3.add(proc2);
		
		combo1= new Combo ("Especial",0.7,proccombo1);
		combo2= new Combo ("Doble",0.4,proccombo2);
		combo3= new Combo ("Costeña",0.9,proccombo3);
		
		menuCombos.add(combo1);
		menuCombos.add(combo2);
		menuCombos.add(combo3);
		
	 }
	 
	
	 @Test
	 public void iniciarPedido() throws YaHayUnPedidoEnCursoException{
		 resto.iniciarPedido("Lau&Ari", "Felicidad");
		 assertNotNull(resto.getPedidoEnCurso());
		 assertEquals(resto.getPedidoEnCurso().getNombreCliente(),"Lau&Ari");
		 
	 }
	 
	 @Test
	 public void PedidoEnCurso() throws YaHayUnPedidoEnCursoException{
		 resto.iniciarPedido("Lau&Ari", "Felicidad");
		 assertEquals(resto.getPedidoEnCurso().getNombreCliente(),"Lau&Ari");
		 
	 }
	 
	 
	 @Test
	 public void yaHayUnPedido()  {
		 
		 assertThrows(YaHayUnPedidoEnCursoException.class,()->{resto.iniciarPedido("Lau&Ari", "Felicidad");
		 resto.iniciarPedido("Pepito", "Suba");});
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 @Test
	 public void Pedidos()  throws NoHayPedidoEnCursoException,YaHayUnPedidoEnCursoException, IOException {
		 
		 resto.iniciarPedido("Laura", "Bogotá");
		 resto.cerrarYGuardarPedido();
		 resto.iniciarPedido("Ari","Kennedy");
		 resto.cerrarYGuardarPedido();
		 Pedido ped = new Pedido("Laura","Bogotá"); 
		 Pedido ped1 = new Pedido("Ari","Kennedy");
			
			pedidos = new ArrayList<Pedido>();
			pedidos.add(ped);
			pedidos.add(ped1);

		 assertEquals(resto.getPedidos().get(0).getNombreCliente(),"Laura","No son los mismos pedidos");
		 
		 
	 }
	
	@Test
    public void testCerrarYGuardarPedido() throws Exception {
        resto.iniciarPedido("Lau&Ari", "Felicidad");
        resto.getPedidoEnCurso().agregarProducto(new ProductoMenu("Hamburguesa",2000));

        // Asegurar que exista la carpeta
        File carpetaFacturas = new File("./facturas/");
        if (!carpetaFacturas.exists()) {
            carpetaFacturas.mkdirs();
        }

        int id = resto.getPedidoEnCurso().getIdPedido(); 
        resto.cerrarYGuardarPedido();

        String nombreArchivo = "./facturas/factura_" + id + ".txt";
        File factura = new File(nombreArchivo);

        assertTrue(factura.exists(),"La factura no fue generada");

        factura.delete(); // Limpieza
    }
	
}
