package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;

class RestauranteTest {
	
	private Pedido ped;
	private Pedido ped1;
	private ArrayList<Producto> productos;

	 @BeforeEach
	    public void setUp() {
		
		ped = new Pedido("Laura","Bogotá"); 
		ped1 = new Pedido("h","e");
		productos = new ArrayList<Producto>();
			
	 }
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
