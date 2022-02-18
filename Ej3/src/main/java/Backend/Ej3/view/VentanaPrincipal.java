package Backend.Ej3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Backend.Ej3.controller.Asignado_AController;
import Backend.Ej3.controller.CientificosController;
import Backend.Ej3.controller.ProyectoController;

public class VentanaPrincipal extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private ProyectoController proyectoController;
	private Asignado_AController asignado_aController;
	private CientificosController cientificosController; //objeto CientificosController que permite la relacion entre esta clase y la clase CientificosController
	private JButton botonRegistrarCientificoss, botonBuscarCientificoss, botonBuscarProyecto, botonRegistrarProyecto, botonAsignar;

	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonRegistrarCientificoss = new JButton();
		botonRegistrarCientificoss.setBounds(66, 75, 154, 25);
		botonRegistrarCientificoss.setText("Registrar Cientificos");
		
		botonBuscarCientificoss = new JButton();
		botonBuscarCientificoss.setBounds(240, 75, 154, 25);
		botonBuscarCientificoss.setText("Buscar Cientificos");
		
		botonRegistrarProyecto = new JButton();
		botonRegistrarProyecto.setText("Registrar Proyecto");
		botonRegistrarProyecto.setBounds(66, 200, 154, 25);
		
		botonBuscarProyecto = new JButton();
		botonBuscarProyecto.setText("Buscar Proyecto");
		botonBuscarProyecto.setBounds(240, 200, 154, 25);
		
		botonAsignar = new JButton();
		botonAsignar.setText("Asignar Proyecto a Cientifico");
		botonAsignar.setBounds(135, 140, 200, 25);


		botonRegistrarCientificoss.addActionListener(this);
		botonBuscarCientificoss.addActionListener(this);
		botonRegistrarProyecto.addActionListener(this);
		botonBuscarProyecto.addActionListener(this);
		botonAsignar.addActionListener(this);
		getContentPane().add(botonBuscarCientificoss);
		getContentPane().add(botonRegistrarCientificoss);
		getContentPane().add(botonBuscarProyecto);
		getContentPane().add(botonRegistrarProyecto);
		getContentPane().add(botonAsignar);
	
		setSize(480, 350);
		setTitle("Patron de Diseño/MVC");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		

	}


	public void setCoordinadores(CientificosController cientificosController, ProyectoController proyectoController, Asignado_AController asignado_aController) 
	{
		this.cientificosController = cientificosController;
		this.proyectoController = proyectoController;
		this.asignado_aController = asignado_aController;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonRegistrarCientificoss) 
		{
			cientificosController.mostrarVentanaRegistroCientificos();			
		}
		else if (e.getSource()==botonBuscarCientificoss) 
		{
			cientificosController.mostrarVentanaConsultaCientificos();			
		}
		else if (e.getSource()==botonRegistrarProyecto) 
		{
			proyectoController.mostrarVentanaRegistroProyecto();			
		}
		else if (e.getSource()==botonBuscarProyecto) 
		{
			proyectoController.mostrarVentanaConsultaProyecto();			
		}
		else if (e.getSource()==botonAsignar) 
		{
			asignado_aController.mostrarVentanaAsignarCientificos();	
		}
	}
}
