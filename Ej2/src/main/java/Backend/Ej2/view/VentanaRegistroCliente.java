package Backend.Ej2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Backend.Ej2.controller.ClienteController;
import Backend.Ej2.model.dto.Cliente;

public class VentanaRegistroCliente extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private ClienteController clienteController; //objeto clienteController que permite la relacion entre esta clase y la clase ClienteController
	private JLabel labelTitulo;
	private JTextField textCod, textNombre, textApellido, textFecha, textDireccion, textDni;
	private JLabel cod, nombre, apellido, direccion, dni, fecha;
	private JButton botonGuardar, botonCancelar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de registro
	 */
	public VentanaRegistroCliente() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 220, 120, 25);
		botonGuardar.setText("Registrar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 220, 120, 25);
		botonCancelar.setText("Cancelar");

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE CLIENTES");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		getContentPane().add(cod);
		
		dni = new JLabel();
		dni.setText("DNI");
		dni.setBounds(198, 80, 52, 25);
		getContentPane().add(dni);
		
		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(20, 120, 80, 25);
		getContentPane().add(nombre);

		fecha = new JLabel();
		fecha.setText("Fecha");
		fecha.setBounds(290, 160, 80, 25);
		getContentPane().add(fecha);
		
		apellido = new JLabel();
		apellido.setText("Apellido");
		apellido.setBounds(220, 120, 64, 25);
		getContentPane().add(apellido);
		
		direccion = new JLabel();
		direccion.setText("Direccion");
		direccion.setBounds(20, 160, 80, 25);
		getContentPane().add(direccion);
		
		textCod=new JTextField();
		textCod.setBounds(80, 80, 80, 25);
		getContentPane().add(textCod);
		
		textDni = new JTextField();
		textDni.setText("");
		textDni.setBounds(260, 80, 160, 25);
		getContentPane().add(textDni);
		
		textNombre=new JTextField();
		textNombre.setBounds(80, 120, 130, 25);
		getContentPane().add(textNombre);

		textFecha = new JTextField();
		textFecha.setToolTipText("yyyy-MM-dd");
		textFecha.setBounds(340, 160, 80, 25);
		getContentPane().add(textFecha);
		
		textApellido = new JTextField();
		textApellido.setBounds(280, 120, 140, 25);
		getContentPane().add(textApellido);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(80, 160, 190, 25);
		getContentPane().add(textDireccion);
		
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
		textCod.setText("");
		textDni.setText("");
		textNombre.setText("");
		textApellido.setText("");
		textDireccion.setText("");
		textFecha.setText("");
	}


	public void setCoordinador(ClienteController clienteController) {
		this.clienteController=clienteController;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonGuardar)
		{
			try 
			{				
				Cliente miCliente=new Cliente();
				miCliente.setId(Integer.parseInt(textCod.getText()));
				miCliente.setNombre(textNombre.getText());
				miCliente.setApellido(textApellido.getText());
				miCliente.setDireccion(textDireccion.getText());
				miCliente.setDni(Integer.parseInt(textDni.getText()));
				miCliente.setFecha(textFecha.getText());
				
				clienteController.registrarCliente(miCliente);	
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

