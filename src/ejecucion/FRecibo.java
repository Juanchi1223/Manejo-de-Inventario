package ejecucion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class FRecibo extends JDialog {
	private static double monto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FRecibo dialog = new FRecibo(monto);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FRecibo(double monto) {
		setTitle("Recibo");
		setBounds(100, 100, 225, 123);
		getContentPane().setLayout(null);
		{
			JLabel lblTotalDeCompra = new JLabel("Total a Pagar:");
			lblTotalDeCompra.setBounds(58, 23, 93, 14);
			getContentPane().add(lblTotalDeCompra);
		}
		{
			JLabel lblCompra = new JLabel(String.valueOf(monto));
			lblCompra.setBounds(81, 48, 46, 14);
			getContentPane().add(lblCompra);
		}
	}

}
