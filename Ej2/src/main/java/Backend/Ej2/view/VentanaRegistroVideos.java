package Backend.Ej2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Backend.Ej2.controller.VideosController;
import Backend.Ej2.model.dao.ClienteDao;
import Backend.Ej2.model.dto.Videos;

public class VentanaRegistroVideos extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private VideosController videosController; //objeto videosController que permite la relacion entre esta clase y la clase VideosController
	private JLabel labelTitulo;
	private JTextField textCod, textTitle, textDirector;
	private JComboBox<Object> textCli_id;
	private JLabel cod, title, cli_id, director;
	private JButton botonGuardar, botonCancelar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de registro
	 */
	public VentanaRegistroVideos() {

		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 220, 120, 25);
		botonGuardar.setText("Registrar");
		
		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 220, 120, 25);
		botonCancelar.setText("Cancelar");

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE VIDEOS");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		getContentPane().add(cod);
		
		title = new JLabel();
		title.setText("Titulo");
		title.setBounds(20, 120, 80, 25);
		getContentPane().add(title);
		
		cli_id = new JLabel();
		cli_id.setText("Codigo Cliente");
		cli_id.setBounds(194, 80, 103, 25);
		getContentPane().add(cli_id);
		
		director = new JLabel();
		director.setText("Director");
		director.setBounds(20, 160, 80, 25);
		getContentPane().add(director);
		
		textCod=new JTextField();
		textCod.setBounds(80, 80, 80, 25);
		getContentPane().add(textCod);
		
		textTitle=new JTextField();
		textTitle.setBounds(80, 120, 130, 25);
		getContentPane().add(textTitle);
		
		textCli_id = new JComboBox<Object>();
		textCli_id.setBounds(282, 80, 140, 25);
		textCli_id.setModel(new DefaultComboBoxModel<Object>((new ClienteDao()).recogerIdsCliente()));
		getContentPane().add(textCli_id);
		
		textDirector = new JTextField();
		textDirector.setBounds(80, 160, 190, 25);
		getContentPane().add(textDirector);
		
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
		if(textCli_id.getItemCount() > 0)
		{
			textCli_id.setSelectedIndex(0);
		}
		textDirector.setText("");
	}


	public void setCoordinador(VideosController videosController) {
		this.videosController=videosController;
	}


	public JComboBox<Object> getTextCli_id() 
	{
		return textCli_id;
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==botonGuardar)
		{
			try 
			{				
				Videos miVideos=new Videos();
				miVideos.setId(Integer.parseInt(textCod.getText()));
				miVideos.setTitle(textTitle.getText());
				miVideos.setDirector(textDirector.getText());
				miVideos.setCli_id((Integer)textCli_id.getSelectedItem());
				
				videosController.registrarVideos(miVideos);	
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
