package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.*;
import java.util.*;

class ComboTest {
	private ProductoMenu ing1;
	private ProductoMenu ing2;
	private ProductoMenu ing3;
	private Combo com;
	

    @BeforeEach
    public void setUp() {
    	ing1 = new ProductoMenu("Hmburguesa Sencilla",1000);
		ing2 = new ProductoMenu("Papas Medianas",500);
		ing3 = new ProductoMenu("Gaseosa",700);
		
		ArrayList<ProductoMenu> lista = new ArrayList<>();
		lista.add(ing3);
		lista.add(ing2);
		lista.add(ing1);
 		
		com = new Combo("Especial",0.07,lista);
		  
    }
	
	@Test
	void generarFactura() {
	String nom = com.getNombre();
	StringBuffer sb = new StringBuffer( );
    sb.append( "Combo " + nom + "\n" );
    sb.append( " Descuento: " + "0.07" + "\n" );
    sb.append( "            " + "154" + "\n" );
    String esperado = sb.toString();
	String resp = com.generarTextoFactura();
	
	assertEquals(resp,esperado,	"No esta dando bien el texto de la factura");
	
		
		
	}

	
	@Test
	void Precio() {
		assertEquals(154,com.getPrecio(),"No calcula bien el descuento");
	}
	
	
	@Test
	void getNombre() {
		assertEquals("Especial",com.getNombre(),"No devuelve el nombre como debe ser");
	}
}
