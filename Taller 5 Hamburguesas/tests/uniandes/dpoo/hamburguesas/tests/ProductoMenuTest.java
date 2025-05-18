package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {

	private ProductoMenu proc;
	
	
	@BeforeEach
    public void setUp() {
    	proc = new ProductoMenu("Empanada",200);
	}
	
	@Test
	void Nombre() {
		assertEquals("Empanada",proc.getNombre(),"No es el mismo nombre");
		
	}

	@Test
	void Precio() {
		assertEquals(200,proc.getPrecio(),"No es el mismo precio");
		
	}

	@Test
	void Factura() {
		
		 StringBuffer sb = new StringBuffer( );
	        sb.append( "Empanada" + "\n" );
	        sb.append( "            " + 200 + "\n" );
	     String res = sb.toString();
	     String rta= proc.generarTextoFactura();
		assertEquals(res,rta,"No es el mismo nombre");
		
	}

	
}
