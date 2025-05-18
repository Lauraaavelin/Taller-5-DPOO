package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import uniandes.dpoo.hamburguesas.excepciones.*;


class PedidoTest {
	
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
	void id() {
		
		assertEquals(ped1.getIdPedido()-1,ped.getIdPedido(),"No es el id esperado");
	}

	@Test
	void nombre() {
		
		assertEquals("Laura",ped.getNombreCliente(),"No es el nombre esperado");
	}
	
	@Test
	void agregar() {
		Producto proc = new ProductoMenu("Salsa",45);
		Producto proc2= new ProductoMenu("Agua",2000);
		productos.add(proc);
		productos.add(proc2);
		ped.agregarProducto(proc);
		ped.agregarProducto(proc2);
		
		assertEquals(productos,ped.getProductos(),"No se agrega bien el producto");
	}
	
	
	@Test
	void PrecioTotal() {
		Producto proc = new ProductoMenu("Salsa",45);
		Producto proc2= new ProductoMenu("Agua",2000);
		ped.agregarProducto(proc);
		ped.agregarProducto(proc2);
		double precio = 2045+2045*0.19;
		int precioint = (int)precio;
		assertEquals(precioint,ped.getPrecioTotalPedido(),"No es el nombre esperado");
	}
	
	
	
	@Test
	void generarFactura() {
	
	Producto proc = new ProductoMenu("Salsa",45);
	Producto proc2= new ProductoMenu("Agua",2000);
	productos.add(proc);
	productos.add(proc2);
	ped.agregarProducto(proc);
	ped.agregarProducto(proc2);
	
	StringBuffer sb = new StringBuffer( );
	sb.append( "Cliente: " + "Laura" + "\n" );
    sb.append( "Dirección: " + "Bogotá" + "\n" );
    sb.append( "----------------\n" );

    for( Producto item : productos )
    {
        sb.append( item.generarTextoFactura( ) );
    }

    sb.append( "----------------\n" );
    sb.append( "Precio Neto:  " + 2045 + "\n" );
    sb.append( "IVA:          " + 388+ "\n" );
    sb.append( "Precio Total: " + 2433 + "\n" );

    String esperado= sb.toString( );
    
	String resp = ped.generarTextoFactura();
	
	assertEquals(resp,esperado,	"No esta dando bien el texto de la factura");
	
		
		
	}
	
	
	
	//TODO TENGO QUE IMPLPEMENTAR ESTE ULTIMO TEST QUE ES EL QUE NO ME CORRE
	@Test
    public void testGuardarFactura() throws FileNotFoundException {
		Producto proc = new ProductoMenu("Salsa",45);
		Producto proc2= new ProductoMenu("Agua",2000);
        ped.agregarProducto(proc);
        ped.agregarProducto(proc2);

        File archivo = new File("factura_test.txt");
        ped.guardarFactura(archivo);

        Scanner sc = new Scanner(archivo);
        String contenido = "";
        while (sc.hasNextLine()) {
            contenido += sc.nextLine() + "\n";
        }
        sc.close();

        assertTrue(contenido.contains("Cliente: Laura"));
        assertTrue(contenido.contains("Salsa"));
        assertTrue(contenido.contains("Agua"));
        archivo.delete(); 
    }
	
	
	
	
	
	
}
