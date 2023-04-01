package ejecucion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class FCoutas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int coutas = 0;
	private JRadioButton rdbtn2, rdbtn3, rdbtn6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FCoutas dialog = new FCoutas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FCoutas() {
		setTitle("Elegir Cuotas");
		setBounds(100, 100, 229, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		rdbtn2 = new JRadioButton("2");
		rdbtn2.setBounds(32, 50, 41, 23);
		//contentPanel.add(rdbtn2);
		
		rdbtn3 = new JRadioButton("3");
		rdbtn3.setBounds(86, 50, 41, 23);
		//contentPanel.add(rdbtn3);
		
		rdbtn6 = new JRadioButton("6");
		rdbtn6.setBounds(146, 50, 41, 23);
		//contentPanel.add(rdbtn6);
		
		ButtonGroup coutas = new ButtonGroup(); 
		coutas.add(rdbtn2);coutas.add(rdbtn3);coutas.add(rdbtn6);
		contentPanel.add(rdbtn2);contentPanel.add(rdbtn3);contentPanel.add(rdbtn6);
		
		JLabel lblCoutas = new JLabel("Cantidad de cuotas:");
		lblCoutas.setBounds(10, 22, 156, 14);
		contentPanel.add(lblCoutas);
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
		if (rdbtn2.isSelected())
			coutas = 2;
		else if(rdbtn3.isSelected())
			coutas = 3;
		else if(rdbtn6.isSelected())
			coutas = 6;
		
		this.setVisible(false);
	}
	private void cancelar() {
		this.setVisible(false);
	}
	public int getCouta() {
		return coutas;
	}
}
