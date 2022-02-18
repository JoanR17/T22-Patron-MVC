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
import Backend.Ej2.model.service.ClienteServ;

public class VentanaBuscarCliente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private ClienteController clienteController; //objeto clienteController que permite la relacion entre esta clase y la clase clienteController
	private JLabel labelTitulo;
	private JTextField textCod, textNombre, textApellido, textFecha, textDireccion, textDni;
	private JLabel cod, nombre, apellido, direccion, dni, fecha;
	private JButton botonGuardar,botonCancelar,botonBuscar,botonModificar,botonEliminar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de busqueda
	 */
	public VentanaBuscarCliente() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(50, 220, 120, 25);
		botonGuardar.setText("Guardar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(190, 250, 120, 25);
		botonCancelar.setText("Cancelar");
		
		botonBuscar = new JButton();
		botonBuscar.setBounds(170, 80, 50, 25);
		botonBuscar.setText("Ok");
		
		botonEliminar = new JButton();
		botonEliminar.setBounds(330, 220, 120, 25);
		botonEliminar.setText("Eliminar");
		
		botonModificar = new JButton();
		botonModificar.setBounds(190, 220, 120, 25);
		botonModificar.setText("Modificar");

		labelTitulo = new JLabel();
		labelTitulo.setText("ADMINISTRAR CLIENTES");
		labelTitulo.setBounds(98, 21, 272, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod=new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		getContentPane().add(cod);
		
		dni = new JLabel();
		dni.setText("DNI");
		dni.setBounds(242, 80, 40, 25);
		getContentPane().add(dni);
		
		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(20, 120, 80, 25);
		getContentPane().add(nombre);

		fecha = new JLabel();
		fecha.setText("Fecha");
		fecha.setBounds(290, 160, 80, 25);
		getContentPane().add(fecha);
		
		direccion = new JLabel();
		direccion.setText("Dirección");
		direccion.setBounds(20, 160, 80, 25);
		getContentPane().add(direccion);
		
		apellido = new JLabel();
		apellido.setText("Apellido");
		apellido.setBounds(230, 120, 69, 25);
		getContentPane().add(apellido);
		
		textCod=new JTextField();
		textCod.setBounds(80, 80, 80, 25);
		getContentPane().add(textCod);
		
		textDni = new JTextField();
		textDni.setText("");
		textDni.setEditable(false);
		textDni.setBounds(280, 80, 140, 25);
		getContentPane().add(textDni);
		
		textNombre=new JTextField();
		textNombre.setBounds(80, 120, 140, 25);
		getContentPane().add(textNombre);

		textFecha = new JTextField();
		textFecha.setToolTipText("yyyy-MM-dd");
		textFecha.setBounds(340, 160, 80, 25);
		getContentPane().add(textFecha);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(80, 160, 190, 25);
		getContentPane().add(textDireccion);
		
		textApellido = new JTextField();
		textApellido.setBounds(280, 120, 140, 25);
		getContentPane().add(textApellido);
		
		botonModificar.addActionListener(this);
		botonEliminar.addActionListener(this);
		botonBuscar.addActionListener(this);
		botonGuardar.addActionListener(this);
		botonCancelar.addActionListener(this);

		getContentPane().add(botonCancelar);
		getContentPane().add(botonBuscar);
		getContentPane().add(botonModificar);
		getContentPane().add(botonEliminar);
		getContentPane().add(botonGuardar);
		getContentPane().add(labelTitulo);
		limpiar();
				
		setSize(480, 320);
		setTitle("Patron de Diseño/MVC");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

	}


	public void setCoordinador(ClienteController clienteController) 
	{
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

				clienteController.modificarCliente(miCliente);
				
				if (ClienteServ.modificaCliente==true) 
				{
					habilita(true, false, false, false, false, false, true, false, false, false);
				}
			} 
			catch (Exception e2) 
			{
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource()==botonBuscar)
		{
			Cliente miCliente=clienteController.buscarCliente(textCod.getText());
			if (miCliente!=null)
			{
				muestraCliente(miCliente);
			}
			else if(ClienteServ.consultaCliente==true)
			{
				JOptionPane.showMessageDialog(null, "La cliente no Existe","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource()==botonModificar)
		{
			habilita(false, true, true, true, true, true, false, true, false, false);
			
		}
		
		if (e.getSource()==botonEliminar)
		{
			if (!textCod.getText().equals(""))
			{
				int respuesta = JOptionPane.showConfirmDialog(this,
						"Esta seguro de eliminar la Cliente?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION)
				{
					clienteController.eliminarCliente(textCod.getText());
					limpiar();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Ingrese un numero de Documento", "Información",JOptionPane.WARNING_MESSAGE);
			}
			
		}
		if (e.getSource()==botonCancelar)
		{
			this.dispose();
		}

	}



	/**
	 * permite cargar los datos de la cliente consultada
	 * @param miCliente
	 */
	private void muestraCliente(Cliente miCliente) 
	{
		textNombre.setText(miCliente.getNombre());
		textApellido.setText(miCliente.getApellido()+"");
		textDireccion.setText(miCliente.getDireccion()+"");
		textDni.setText(miCliente.getDni()+"");
		textFecha.setText(miCliente.getFecha()+"");
		habilita(true, false, false, false, false, false, true, false, true, true);
	}


	/**
	 * Permite limpiar los componentes
	 */
	public void limpiar()
	{
		textCod.setText("");
		textNombre.setText("");
		textApellido.setText("");
		textDireccion.setText("");
		textDni.setText("");
		textFecha.setText("");
		habilita(true, false, false, false, false, false, true, false, false, false);
	}


	/**
	 * Permite habilitar los componentes para establecer una modificacion
	 * @param codigo
	 * @param nombre
	 * @param edad
	 * @param tel
	 * @param profesion
	 * @param cargo
	 * @param bBuscar
	 * @param bGuardar
	 * @param bModificar
	 * @param bEliminar
	 */
	public void habilita(boolean codigo, boolean nombre, boolean apellido, boolean direccion, boolean dni, boolean fecha, boolean bBuscar, boolean bGuardar, boolean bModificar, boolean bEliminar)
	{
		textCod.setEditable(codigo);
		textNombre.setEditable(nombre);
		textApellido.setEditable(apellido);
		textDireccion.setEditable(direccion);
		textDni.setEditable(dni);
		textFecha.setEditable(fecha);
		botonBuscar.setEnabled(bBuscar);
		botonGuardar.setEnabled(bGuardar);
		botonModificar.setEnabled(bModificar);
		botonEliminar.setEnabled(bEliminar);
	}
}

