package Backend.Ej3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Backend.Ej3.controller.Asignado_AController;
import Backend.Ej3.model.dao.CientificosDao;
import Backend.Ej3.model.dao.ProyectoDao;
import Backend.Ej3.model.dto.Asignado_A;

public class VentanaAsignar_a extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Asignado_AController asignar_aController; //objeto clienteController que permite la relacion entre esta clase y la clase ClienteController
	private JLabel labelTitulo;
	private JComboBox<Object> textDniCientifico, textProyecto;
	private JLabel dniCientifico, proyecto;
	private JButton botonGuardar, botonCancelar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de registro
	 */
	public VentanaAsignar_a() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 220, 120, 25);
		botonGuardar.setText("Registrar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 220, 120, 25);
		botonCancelar.setText("Cancelar");

		labelTitulo = new JLabel();
		labelTitulo.setText("ASIGNAR CIENTIFICO A PROYECTO");
		labelTitulo.setBounds(47, 22, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));
		
		dniCientifico = new JLabel();
		dniCientifico.setText("DNI Cientifico");
		dniCientifico.setBounds(110, 79, 103, 25);
		getContentPane().add(dniCientifico);
		
		proyecto = new JLabel();
		proyecto.setText("Codigo Proyecto");
		proyecto.setBounds(110, 135, 103, 25);
		getContentPane().add(proyecto);
		
		textDniCientifico = new JComboBox<Object>();
		textDniCientifico.setBounds(218, 79, 140, 25);
		textDniCientifico.setModel(new DefaultComboBoxModel<Object>((new CientificosDao()).recogerDnisCientificos()));
		getContentPane().add(textDniCientifico);
		
		textProyecto = new JComboBox<Object>();
		textProyecto.setBounds(218, 135, 140, 25);
		textProyecto.setModel(new DefaultComboBoxModel<Object>((new ProyectoDao()).recogerIdsProyectos()));
		getContentPane().add(textProyecto);
		
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
		if(textDniCientifico.getItemCount() > 0)
		{
			textDniCientifico.setSelectedIndex(0);
		}
		if(textProyecto.getItemCount() > 0)
		{
			textProyecto.setSelectedIndex(0);
		}
	}


	public void setCoordinador(Asignado_AController asignar_aController) {
		this.asignar_aController = asignar_aController;
	}


	public JComboBox<Object> getTextDniCientifico() 
	{
		return textDniCientifico;
	}


	public JComboBox<Object> getTextProyecto() 
	{
		return textProyecto;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonGuardar)
		{
			try 
			{				
				Asignado_A miAsignado_A = new Asignado_A();
				miAsignado_A.setCientifico(""+textDniCientifico.getSelectedItem());
				miAsignado_A.setProyecto(""+textProyecto.getSelectedItem());
				
				asignar_aController.asignarCientificoProyecto(miAsignado_A);	
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
