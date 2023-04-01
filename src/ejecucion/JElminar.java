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

public class JElminar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textEliminarCodigo;
	private boolean eliminar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JElminar dialog = new JElminar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JElminar() {
		setTitle("Eliminar Producto");
		setBounds(100, 100, 311, 176);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textEliminarCodigo = new JTextField();
			textEliminarCodigo.setBounds(134, 42, 106, 20);
			contentPanel.add(textEliminarCodigo);
			textEliminarCodigo.setColumns(10);
		}
		{
			JLabel lblEliminarCodigo = new JLabel("Ingresar Codigo");
			lblEliminarCodigo.setBounds(32, 45, 84, 14);
			contentPanel.add(lblEliminarCodigo);
		}
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
			eliminar = true;
			getCodigo();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this,"ERROR: error en el ingreso de datos");
			eliminar = false;
		}
		this.setVisible(false);
	}
	private void cancelar() {
		this.setVisible(false);
	}
	public int getCodigo() {
		return Integer.parseInt(textEliminarCodigo.getText());
	}
	public boolean getEliminar() {
		return eliminar;
	}
}
