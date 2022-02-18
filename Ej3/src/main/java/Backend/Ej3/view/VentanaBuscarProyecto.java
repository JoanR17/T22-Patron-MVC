package Backend.Ej3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Backend.Ej3.controller.ProyectoController;
import Backend.Ej3.model.dto.Proyecto;
import Backend.Ej3.model.service.ProyectoServ;

public class VentanaBuscarProyecto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private ProyectoController proyectoController; //objeto proyectoController que permite la relacion entre esta clase y la clase proyectoController
	private JLabel labelTitulo;
	private JTextField textCod, textNombre, textHoras;
	private JLabel cod, nombre, horas;
	private JButton botonGuardar,botonCancelar,botonBuscar,botonModificar,botonEliminar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de busqueda
	 */
	public VentanaBuscarProyecto() {

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
		labelTitulo.setText("ADMINISTRAR PROYECTOS");
		labelTitulo.setBounds(80, 21, 313, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod=new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		getContentPane().add(cod);
		
		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(250, 80, 80, 25);
		getContentPane().add(nombre);
		
		horas = new JLabel();
		horas.setText("Horas");
		horas.setBounds(98, 126, 80, 25);
		getContentPane().add(horas);
		
		textCod=new JTextField();
		textCod.setBounds(80, 80, 80, 25);
		getContentPane().add(textCod);
		
		textNombre=new JTextField();
		textNombre.setBounds(310, 80, 140, 25);
		getContentPane().add(textNombre);
		
		textHoras = new JTextField();
		textHoras.setBounds(158, 126, 190, 25);
		getContentPane().add(textHoras);
		
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


	public void setCoordinador(ProyectoController proyectoController) 
	{
		this.proyectoController=proyectoController;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonGuardar)
		{
			try 
			{
				
				Proyecto miProyecto=new Proyecto();
				miProyecto.setId(textCod.getText());
				miProyecto.setNombre(textNombre.getText());
				miProyecto.setHoras(Integer.parseInt(textHoras.getText()));

				proyectoController.modificarProyecto(miProyecto);
				
				if (ProyectoServ.modificaProyecto==true) 
				{
					habilita(true, false, false, true, false, false, false);
				}
			} 
			catch (Exception e2) 
			{
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource()==botonBuscar)
		{
			Proyecto miProyecto = proyectoController.buscarProyecto(textCod.getText());
			
			if (miProyecto!=null)
			{
				muestraProyecto(miProyecto);
			}
			else if(ProyectoServ.consultaProyecto==true)
			{
				JOptionPane.showMessageDialog(null, "La proyecto no Existe","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource()==botonModificar)
		{
			habilita(false, true, true, false, true, false, false);
			
		}
		
		if (e.getSource()==botonEliminar)
		{
			if (!textCod.getText().equals(""))
			{
				int respuesta = JOptionPane.showConfirmDialog(this,
						"Esta seguro de eliminar la Proyecto?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION)
				{
					proyectoController.eliminarProyecto(textCod.getText());
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
	 * permite cargar los datos de la proyecto consultada
	 * @param miProyecto
	 */
	private void muestraProyecto(Proyecto miProyecto) 
	{
		textNombre.setText(miProyecto.getNombre());
		textHoras.setText(miProyecto.getHoras()+"");
		habilita(true, false, false, true, false, true, true);
	}


	/**
	 * Permite limpiar los componentes
	 */
	public void limpiar()
	{
		textCod.setText("");
		textNombre.setText("");
		textHoras.setText("");
		habilita(true, false, false, true, false, false, false);
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
	public void habilita(boolean codigo, boolean nombre, boolean horas, boolean bBuscar, boolean bGuardar, boolean bModificar, boolean bEliminar)
	{
		textCod.setEditable(codigo);
		textNombre.setEditable(nombre);
		textHoras.setEditable(horas);
		botonBuscar.setEnabled(bBuscar);
		botonGuardar.setEnabled(bGuardar);
		botonModificar.setEnabled(bModificar);
		botonEliminar.setEnabled(bEliminar);
	}
}
