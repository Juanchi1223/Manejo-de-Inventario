package empresa;

import java.util.Comparator;

public class Producto {
	private int codigo;
    private String desc;
    private double precio;
    private int stock;
    private int stockMin;
    private int indiceV = 0;

    public Producto(int codigo, String desc, double precio, int stock, int stockMin){
        this.codigo = codigo;
        this.desc = desc;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
    }

    public int getCodigo() {
        return codigo;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMin() {
        return stockMin;
    }
    public int getIndice() {
    	return indiceV;
    }
    public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void venta(){
    	this.indiceV++;
    }
    public String toString(){
		return desc + " (" + codigo + ") " + stock + "/" + stockMin ;
	}
}
