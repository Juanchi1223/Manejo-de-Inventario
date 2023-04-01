package ejecucion;

import empresa.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JLabel;

public class Home {

	private JFrame FHome;
	public static Catalogo listaProd; 
	private JList listRankingProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.FHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FHome = new JFrame();
		FHome.setTitle("TPO(Aplicacion)");
		FHome.setBounds(100, 100, 568, 360);
		FHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FHome.getContentPane().setLayout(null);
		
		listaProd = new Catalogo();
		pruebaDeCatalogo();
		
		JButton btnCatalogo = new JButton("Catalogo");
		btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCatalogo();
			}
		});
		btnCatalogo.setBounds(330, 92, 164, 54);
		FHome.getContentPane().add(btnCatalogo);
		
		JButton btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVenta();
			}
		});
		btnVenta.setBounds(330, 185, 164, 54);
		FHome.getContentPane().add(btnVenta);
		
		listRankingProd = new JList();
		listRankingProd.setBounds(54, 70, 230, 204);
		FHome.getContentPane().add(listRankingProd);
		listRankingProd.setListData(listaProd.getMasVendidos().toArray());
		
		JLabel lblRanking = new JLabel("Ranking Productos:");
		lblRanking.setBounds(117, 45, 142, 14);
		FHome.getContentPane().add(lblRanking);
	}

	public void abrirCatalogo() {
		FCatalogo cata = new FCatalogo();
		cata.setModal(true);
		cata.setVisible(true);
		
		listRankingProd.setListData(listaProd.getMasVendidos().toArray());
	}
	public void abrirVenta() {
		FVenta vent =new FVenta();
		vent.setModal(true);
		vent.setVisible(true);
		
		// eliminar la venta por cantiadad y sumar el indice
		if (vent.getCodigoVenta() != -999999) {
			Producto x = listaProd.buscarProducto(vent.getCodigoVenta());
			
			listaProd.actualizarProd(vent.getCodigoVenta(), x.getDesc(), x.getPrecio(), x.getStock() - vent.getCantidad(), x.getStockMin());
			listaProd.indiceVProd(vent.getCodigoVenta());
		}
		listRankingProd.setListData(listaProd.getMasVendidos().toArray());
	}
	private void pruebaDeCatalogo() { // rellenar catalogo con cosas para saber el funcionamiento de ventas
		Producto x = new Producto(1, "Mesa", 120, 100, 50);
		Producto y = new Producto(2, "Silla", 50, 20, 15);
		Producto z = new Producto(3, "Platos", 10, 150, 12);
		Producto x2 = new Producto(4, "Manteles", 120, 100, 50);
		Producto y2 = new Producto(5, "Vasos", 50, 20, 15);
		Producto z2 = new Producto(6, "Tenedores", 10, 150, 12);
		Producto x3 = new Producto(7, "Cuchillos", 120, 100, 50);
		Producto y3 = new Producto(8, "Cucharas", 50, 20, 15);
		Producto z3 = new Producto(9, "Ollas", 10, 150, 12);
		Producto x4 = new Producto(10, "Sartenes", 120, 100, 50);
		Producto y4 = new Producto(11, "Coladores", 50, 20, 15);
		Producto z4 = new Producto(12, "Tuppers", 10, 150, 12);
		
		listaProd.agrProd(x);listaProd.agrProd(y);listaProd.agrProd(z);
		listaProd.agrProd(x2);listaProd.agrProd(y2);listaProd.agrProd(z2);
		listaProd.agrProd(x3);listaProd.agrProd(y3);listaProd.agrProd(z3);
		listaProd.agrProd(x4);listaProd.agrProd(y4);listaProd.agrProd(z4);
		}
}
