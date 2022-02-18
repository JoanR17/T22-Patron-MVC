package Backend.Ej3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Backend.Ej3.controller.CientificosController;
import Backend.Ej3.model.dto.Cientificos;

public class VentanaRegistroCientificos extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private CientificosController cientificosController; //objeto cientificosController que permite la relacion entre esta clase y la clase CientificosController
	private JLabel labelTitulo;
	private JTextField textNombre, textDni;
	private JLabel nombre, dni;
	private JButton botonGuardar, botonCancelar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de registro
	 */
	public VentanaRegistroCientificos() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 220, 120, 25);
		botonGuardar.setText("Registrar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 220, 120, 25);
		botonCancelar.setText("Cancelar");

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE CIENTIFICOS");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));
		
		dni = new JLabel();
		dni.setText("DNI");
		dni.setBounds(120, 84, 52, 25);
		getContentPane().add(dni);
		
		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(120, 121, 80, 25);
		getContentPane().add(nombre);
		
		textDni = new JTextField();
		textDni.setText("");
		textDni.setBounds(182, 84, 160, 25);
		getContentPane().add(textDni);
		
		textNombre=new JTextField();
		textNombre.setBounds(182, 121, 160, 25);
		getContentPane().add(textNombre);
		
		botonGuardar.addActionListener(this);
		botonCancelar.addActionListener(this);
		getContentPane().add(botonCancelar);
		getContentPane().add(botonGuardar);
		getContentPane().add(labelTitulo);
		limpiar();
		setSize(480, 300);
		setTitle("Patron de Diseño/MVC");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

	}


	private void limpiar() 
	{
		textDni.setText("");
		textNombre.setText("");
	}


	public void setCoordinador(CientificosController cientificosController) {
		this.cientificosController=cientificosController;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonGuardar)
		{
			try 
			{				
				Cientificos miCientificos=new Cientificos();
				miCientificos.setDni(textDni.getText());
				miCientificos.setNomApels(textNombre.getText());
				
				cientificosController.registrarCientificos(miCientificos);	
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos", "Error",JOptionPane.ERROR_MESSAGE);
				System.out.println(ex);
			}
		}
		if (e.getSource()==botonCancelar)
		{
			this.dispose();
		}
	}
}

