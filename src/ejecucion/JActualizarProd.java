package ejecucion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JActualizarProd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textActCod;
	private JTextField textActDesc;
	private JTextField textActStock;
	private JTextField textActPrecio;
	private JTextField textActStockMin;
	private boolean actualziar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JActualizarProd dialog = new JActualizarProd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JActualizarProd() {
		setTitle("Actualizar Producto");
		setBounds(100, 100, 315, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textActCod = new JTextField();
		textActCod.setBounds(27, 46, 86, 20);
		contentPanel.add(textActCod);
		textActCod.setColumns(10);
		
		JLabel lblActCod = new JLabel("Codigo de Producto");
		lblActCod.setBounds(26, 28, 106, 14);
		contentPanel.add(lblActCod);
		
		textActDesc = new JTextField();
		textActDesc.setBounds(32, 118, 86, 20);
		contentPanel.add(textActDesc);
		textActDesc.setColumns(10);
		
		textActStock = new JTextField();
		textActStock.setBounds(31, 166, 86, 20);
		contentPanel.add(textActStock);
		textActStock.setColumns(10);
		
		textActPrecio = new JTextField();
		textActPrecio.setBounds(175, 116, 86, 20);
		contentPanel.add(textActPrecio);
		textActPrecio.setColumns(10);
		
		JLabel lblActDesc = new JLabel("Nueva Descripcion");
		lblActDesc.setBounds(31, 102, 101, 14);
		contentPanel.add(lblActDesc);
		
		JLabel lblActStock = new JLabel("Nuevo Stock");
		lblActStock.setBounds(30, 149, 102, 14);
		contentPanel.add(lblActStock);
		
		JLabel lblActPrecio = new JLabel("Nuevo Precio");
		lblActPrecio.setBounds(176, 102, 100, 14);
		contentPanel.add(lblActPrecio);
		
		textActStockMin = new JTextField();
		textActStockMin.setBounds(175, 163, 86, 20);
		contentPanel.add(textActStockMin);
		textActStockMin.setColumns(10);
		
		JLabel lblActualizarStockMin = new JLabel("Nuevo Stock Minimo");
		lblActualizarStockMin.setBounds(175, 147, 101, 14);
		contentPanel.add(lblActualizarStockMin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void aceptar() {
		try {
			actualziar = true;
			getCodigo();
			getDesc();
			getPrecio();
			getStock();
			getStockMin();
			if (Integer.parseInt(textActStock.getText()) <= Integer.parseInt(textActStockMin.getText())) {
				throw new Exception("El stock ingresado no es suficiente");
			} 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this,"ERROR: error en el ingreso de datos");
			actualziar = false;
		}
		this.setVisible(false);
	}
	private void cancelar() {
		this.setVisible(false);
	}
	public int getCodigo() {
		return Integer.parseInt(textActCod.getText());
	}
	public String getDesc() {
		return textActDesc.getText();
	}
	public double getPrecio() {
		return Double.parseDouble(textActPrecio.getText());
	}
	public int getStock() {
		return Integer.parseInt(textActStock.getText());
	}
	public int getStockMin() {
		return Integer.parseInt(textActStockMin.getText());
	}
	public boolean getActualizar() {
		return actualziar;
	}
}
