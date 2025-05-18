package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.*;

class ProductoAjustadoTest {
	
	private ProductoMenu productoBase;

    private ProductoAjustado proc;
    private ArrayList<Ingrediente> agregados;
    private ArrayList<Ingrediente> eliminados;
    
    
    @BeforeEach
    public void setUp() {
    	productoBase = new ProductoMenu("Empanada",200);
    	proc= new ProductoAjustado(productoBase);
    	Ingrediente ing1= new Ingrediente("ExtraQueso",10);
    	Ingrediente ing2 = new Ingrediente("Cebolla",20);
    	Ingrediente ing3 = new Ingrediente("Lechuga",40);
    	agregados=new ArrayList<Ingrediente>();
    	
    	eliminados = new ArrayList<Ingrediente>();
    	
    }
    
	@Test
	void Nombre() {
		assertEquals("Empanada",proc.getNombre(),"No es el mismo nombre");
		
	}
	

	@Test
	void agregar() {
		Ingrediente ing1= new Ingrediente("ExtraQueso",10);
    	Ingrediente ing2 = new Ingrediente("Cebolla",20);
    	proc.agregarIngrediente(ing1);
    	proc.agregarIngrediente(ing2);
    	agregados.add(ing1);
    	agregados.add(ing2);
    	assertEquals(agregados,proc.getAgregados(),"No se agregaron los ingredientes deseados");
    	
	}
	@Test
	void eliminar() {
		Ingrediente ing3 = new Ingrediente("Lechuga",40);
    	proc.eliminarIngrediente(ing3);
    	eliminados.add(ing3);
    	assertEquals(eliminados,proc.getEliminados(),"No se agregaron los ingredientes deseados");
    	
	}
	
	@Test
	void precio() {
		Ingrediente ing1= new Ingrediente("ExtraQueso",10);
    	Ingrediente ing2 = new Ingrediente("Cebolla",20);
    	proc.agregarIngrediente(ing1);
    	proc.agregarIngrediente(ing2);
    	Ingrediente ing3 = new Ingrediente("Lechuga",40);
    	proc.eliminarIngrediente(ing3);
		assertEquals(230,proc.getPrecio(),"No es el mismo precio");
		
	}
	
	@Test
	void Factura() {
		Ingrediente ing1= new Ingrediente("ExtraQueso",10);
    	Ingrediente ing2 = new Ingrediente("Cebolla",20);
    	proc.agregarIngrediente(ing1);
    	proc.agregarIngrediente(ing2);
    	agregados.add(ing1);
    	agregados.add(ing2);
    	Ingrediente ing3 = new Ingrediente("Lechuga",40);
    	proc.eliminarIngrediente(ing3);
    	eliminados.add(ing3);
		StringBuffer sb = new StringBuffer( );
        sb.append( productoBase );
        for( Ingrediente ing : agregados )
        {
            sb.append( "    +" + ing.getNombre( ) );
            sb.append( "                " + ing.getCostoAdicional( ) );
        }
        for( Ingrediente ing : eliminados )
        {
            sb.append( "    -" + ing.getNombre( ) );
        }

        sb.append( "            " + 230 + "\n" );

        String res= sb.toString();
        
        String rta = proc.generarTextoFactura();
        assertEquals(res,rta,"No se generó bien la factura");
    }
	
	
}
