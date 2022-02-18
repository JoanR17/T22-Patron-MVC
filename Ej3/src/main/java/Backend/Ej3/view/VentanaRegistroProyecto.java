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

public class VentanaRegistroProyecto extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private ProyectoController proyectoController; //objeto proyectoController que permite la relacion entre esta clase y la clase ProyectoController
	private JLabel labelTitulo;
	private JTextField textCod, textTitle, textHoras;
	private JLabel cod, title, horas;
	private JButton botonGuardar, botonCancelar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de registro
	 */
	public VentanaRegistroProyecto() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 220, 120, 25);
		botonGuardar.setText("Registrar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 220, 120, 25);
		botonCancelar.setText("Cancelar");

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE PROYECTO");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(138, 79, 80, 25);
		getContentPane().add(cod);
		
		title = new JLabel();
		title.setText("Nombre");
		title.setBounds(138, 119, 80, 25);
		getContentPane().add(title);
		
		horas = new JLabel();
		horas.setText("Horas");
		horas.setBounds(138, 155, 80, 25);
		getContentPane().add(horas);
		
		textCod=new JTextField();
		textCod.setBounds(198, 79, 80, 25);
		getContentPane().add(textCod);
		
		textTitle = new JTextField();
		textTitle.setBounds(198, 119, 130, 25);
		getContentPane().add(textTitle);
		
		textHoras = new JTextField();
		textHoras.setBounds(198, 155, 140, 25);
		getContentPane().add(textHoras);
		
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
		textTitle.setText("");
		textHoras.setText("");
	}


	public void setCoordinador(ProyectoController proyectoController) {
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
				miProyecto.setNombre(textTitle.getText());
				miProyecto.setHoras(Integer.parseInt(textHoras.getText()));
				
				proyectoController.registrarProyecto(miProyecto);	
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
