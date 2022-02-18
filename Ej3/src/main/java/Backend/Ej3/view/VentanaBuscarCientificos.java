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
import Backend.Ej3.model.service.CientificosServ;

public class VentanaBuscarCientificos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private CientificosController cientificosController; //objeto cientificosController que permite la relacion entre esta clase y la clase cientificosController
	private JLabel labelTitulo;
	private JTextField textNombre, textDni;
	private JLabel nombre, dni;
	private JButton botonGuardar,botonCancelar,botonBuscar,botonModificar,botonEliminar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de busqueda
	 */
	public VentanaBuscarCientificos() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(50, 220, 120, 25);
		botonGuardar.setText("Guardar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(190, 250, 120, 25);
		botonCancelar.setText("Cancelar");
		
		botonBuscar = new JButton();
		botonBuscar.setBounds(314, 97, 50, 25);
		botonBuscar.setText("Ok");
		
		botonEliminar = new JButton();
		botonEliminar.setBounds(330, 220, 120, 25);
		botonEliminar.setText("Eliminar");
		
		botonModificar = new JButton();
		botonModificar.setBounds(190, 220, 120, 25);
		botonModificar.setText("Modificar");

		labelTitulo = new JLabel();
		labelTitulo.setText("ADMINISTRAR CIENTIFICOS");
		labelTitulo.setBounds(80, 25, 319, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));
		
		dni = new JLabel();
		dni.setText("DNI");
		dni.setBounds(85, 97, 40, 25);
		getContentPane().add(dni);
		
		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(85, 137, 80, 25);
		getContentPane().add(nombre);
		
		textDni = new JTextField();
		textDni.setText("");
		textDni.setEditable(false);
		textDni.setBounds(145, 97, 140, 25);
		getContentPane().add(textDni);
		
		textNombre=new JTextField();
		textNombre.setBounds(145, 137, 219, 25);
		getContentPane().add(textNombre);
		
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


	public void setCoordinador(CientificosController cientificosController) 
	{
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

				cientificosController.modificarCientificos(miCientificos);
				
				if (CientificosServ.modificaCientificos==true) 
				{
					habilita(true, false, true, false, true, true);	
				}
			} 
			catch (Exception e2) 
			{
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource()==botonBuscar)
		{
			Cientificos miCientificos=cientificosController.buscarCientificos(textDni.getText());
			if (miCientificos!=null)
			{
				muestraCientificos(miCientificos);
			}
			else if(CientificosServ.consultaCientificos==true)
			{
				JOptionPane.showMessageDialog(null, "La cientificos no Existe","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource()==botonModificar)
		{
			habilita(false, true, false, true, false, false);
			
		}
		
		if (e.getSource()==botonEliminar)
		{
			if (!textDni.getText().equals(""))
			{
				int respuesta = JOptionPane.showConfirmDialog(this,
						"Esta seguro de eliminar la Cientificos?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION)
				{
					cientificosController.eliminarCientificos(textDni.getText());
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
	 * permite cargar los datos de la cientificos consultada
	 * @param miCientificos
	 */
	private void muestraCientificos(Cientificos miCientificos) 
	{
		textNombre.setText(miCientificos.getNomApels());
		habilita(true, false, true, false, true, true);
	}


	/**
	 * Permite limpiar los componentes
	 */
	public void limpiar()
	{
		textNombre.setText("");
		textDni.setText("");
		habilita(true, false, true, false, false, false);
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
	public void habilita(boolean dni, boolean nombre, boolean bBuscar, boolean bGuardar, boolean bModificar, boolean bEliminar)
	{
		textDni.setEditable(dni);
		textNombre.setEditable(nombre);
		botonBuscar.setEnabled(bBuscar);
		botonGuardar.setEnabled(bGuardar);
		botonModificar.setEnabled(bModificar);
		botonEliminar.setEnabled(bEliminar);
	}
}

