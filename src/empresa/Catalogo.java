package empresa;
import java.util.*;

public class Catalogo {
    private ArrayList<Producto> listaProductos;
    public Catalogo(){
        listaProductos = new ArrayList<Producto>();
    }
    
    public void agrProd(Producto temp_Prod) {
        listaProductos.add(temp_Prod);
    }
    
    public ArrayList<Producto> getLista(){
    	return this.listaProductos;
    }
    
    public Producto buscarProducto(int codigo) {
    	for(Producto i :  listaProductos){
			if (i.getCodigo() == codigo)
				return i;
		}
		return null;
    }
	
    public void actualizarProd(int codigo, String desc, double precio, int stock, int stockMin) {
    	for(Producto i :  listaProductos){
			if (i.getCodigo() == codigo) {
				i.setDesc(desc);
				i.setPrecio(precio);
				i.setStock(stock);
				i.setStockMin(stockMin);
				break;
			}	
    	}
    }
    
    public void eliminarProd(int codigo) {
    	for(Producto i :  listaProductos){
			if (i.getCodigo() == codigo) {
				listaProductos.remove(i);
				break;
			}	
    	}
    }
    public void indiceVProd(int codigo) {
    	for(Producto i :  listaProductos){
			if (i.getCodigo() == codigo) {
				i.venta();
				break;
			}	
    	}
    }
    public ArrayList<Producto> getMasVendidos(){
    	ArrayList<Producto> retorno = new ArrayList<Producto>();
    	ArrayList<Producto> top10 = new ArrayList<Producto>();
    	
    	
    	int tope = 0;
    	for(Producto i :  listaProductos){
    		if (i.getIndice() > 0) {
    			retorno.add(i);
    		}
    	}
    	Collections.sort(retorno, new Comparator<Producto>(){			
    		public int compare(Producto p1, Producto p2) {
    			return p2.getIndice() - p1.getIndice();
    		}
    	});
    	for(Producto i :  retorno) {	// aca solo da los primero 10
    		top10.add(i);
    		tope++;
    		if (tope == 10)
    			break;
    	}
    		
    	return top10;
    }
}

    
    