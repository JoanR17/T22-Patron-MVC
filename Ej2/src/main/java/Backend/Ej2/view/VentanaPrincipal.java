package Backend.Ej2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Backend.Ej2.controller.ClienteController;
import Backend.Ej2.controller.VideosController;

public class VentanaPrincipal extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private VideosController videosController;
	private ClienteController clienteController; //objeto ClienteController que permite la relacion entre esta clase y la clase ClienteController
	private JButton botonRegistrarClientes, botonBuscarClientes, botonBuscarVideos, botonRegistrarVideos;

	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonRegistrarClientes = new JButton();
		botonRegistrarClientes.setBounds(66, 75, 154, 25);
		botonRegistrarClientes.setText("Registrar Clientes");
		
		botonBuscarClientes = new JButton();
		botonBuscarClientes.setBounds(240, 75, 154, 25);
		botonBuscarClientes.setText("Buscar Clientes");
		
		botonRegistrarVideos = new JButton();
		botonRegistrarVideos.setText("Registrar Videos");
		botonRegistrarVideos.setBounds(66, 200, 154, 25);
		
		botonBuscarVideos = new JButton();
		botonBuscarVideos.setText("Buscar Videos");
		botonBuscarVideos.setBounds(240, 200, 154, 25);


		botonRegistrarClientes.addActionListener(this);
		botonBuscarClientes.addActionListener(this);
		botonRegistrarVideos.addActionListener(this);
		botonBuscarVideos.addActionListener(this);
		getContentPane().add(botonBuscarClientes);
		getContentPane().add(botonRegistrarClientes);
		getContentPane().add(botonBuscarVideos);
		getContentPane().add(botonRegistrarVideos);
	
		setSize(480, 350);
		setTitle("Patron de Diseño/MVC");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		

	}


	public void setCoordinadores(ClienteController clienteController, VideosController videosController) 
	{
		this.clienteController = clienteController;
		this.videosController = videosController;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonRegistrarClientes) 
		{
			clienteController.mostrarVentanaRegistroCliente();			
		}
		else if (e.getSource()==botonBuscarClientes) 
		{
			clienteController.mostrarVentanaConsultaCliente();			
		}
		else if (e.getSource()==botonRegistrarVideos) 
		{
			videosController.mostrarVentanaRegistroVideos();			
		}
		else if (e.getSource()==botonBuscarVideos) 
		{
			videosController.mostrarVentanaConsultaVideos();			
		}
	}
}
