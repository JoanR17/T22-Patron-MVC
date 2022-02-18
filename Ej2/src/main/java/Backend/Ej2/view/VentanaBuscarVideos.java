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
import Backend.Ej2.model.service.VideosServ;

public class VentanaBuscarVideos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private VideosController videosController; //objeto videosController que permite la relacion entre esta clase y la clase videosController
	private JLabel labelTitulo;
	private JTextField textCod, textTitle, textDirector;
	private JComboBox<Object> textCli_id;
	private JLabel cod, title, cli_id, director;
	private JButton botonGuardar,botonCancelar,botonBuscar,botonModificar,botonEliminar;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana de busqueda
	 */
	public VentanaBuscarVideos() {

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
		labelTitulo.setText("ADMINISTRAR VIDEOS");
		labelTitulo.setBounds(98, 21, 272, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		cod=new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		getContentPane().add(cod);
		
		title = new JLabel();
		title.setText("Titulo");
		title.setBounds(250, 80, 80, 25);
		getContentPane().add(title);
		
		director = new JLabel();
		director.setText("Director");
		director.setBounds(20, 124, 80, 25);
		getContentPane().add(director);
		
		cli_id = new JLabel();
		cli_id.setText("Codigo Cliente");
		cli_id.setBounds(20, 171, 92, 25);
		getContentPane().add(cli_id);
		
		textCod=new JTextField();
		textCod.setBounds(80, 80, 80, 25);
		getContentPane().add(textCod);
		
		textTitle=new JTextField();
		textTitle.setBounds(310, 80, 140, 25);
		getContentPane().add(textTitle);
		
		textDirector = new JTextField();
		textDirector.setBounds(80, 124, 190, 25);
		getContentPane().add(textDirector);
		
		textCli_id = new JComboBox<Object>();
		textCli_id.setBounds(110, 171, 140, 25);
		textCli_id.setModel(new DefaultComboBoxModel<Object>((new ClienteDao()).recogerIdsCliente()));
		getContentPane().add(textCli_id);
		
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


	public void setCoordinador(VideosController videosController) 
	{
		this.videosController=videosController;
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
				miVideos.setCli_id((Integer) textCli_id.getSelectedItem());
				miVideos.setDirector(textDirector.getText());

				videosController.modificarVideos(miVideos);
				
				if (VideosServ.modificaVideos==true) 
				{
					habilita(true, false, false, false, true, false, true, true);	
				}
			} 
			catch (Exception e2) 
			{
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource()==botonBuscar)
		{
			Videos miVideos = videosController.buscarVideos(textCod.getText());
			
			if (miVideos!=null)
			{
				muestraVideos(miVideos);
			}
			else if(VideosServ.consultaVideos==true)
			{
				JOptionPane.showMessageDialog(null, "La videos no Existe","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource()==botonModificar)
		{
			habilita(false, true, true, true, false, true, false, false);
			
		}
		
		if (e.getSource()==botonEliminar)
		{
			if (!textCod.getText().equals(""))
			{
				int respuesta = JOptionPane.showConfirmDialog(this,
						"Esta seguro de eliminar la Videos?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION)
				{
					videosController.eliminarVideos(textCod.getText());
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



	public JComboBox<Object> getTextCli_id() 
	{
		return textCli_id;
	}


	/**
	 * permite cargar los datos de la videos consultada
	 * @param miVideos
	 */
	private void muestraVideos(Videos miVideos) 
	{
		textTitle.setText(miVideos.getTitle());
		textCli_id.setSelectedItem(miVideos.getCli_id());
		textDirector.setText(miVideos.getDirector()+"");
		habilita(true, false, false, false, true, false, true, true);
	}


	/**
	 * Permite limpiar los componentes
	 */
	public void limpiar()
	{
		textCod.setText("");
		textTitle.setText("");
		if(textCli_id.getItemCount() > 0)
		{
			textCli_id.setSelectedIndex(0);
		}
		textDirector.setText("");
		habilita(true, false, false, false, true, false, true, true);
	}


	/**
	 * Permite habilitar los componentes para establecer una modificacion
	 * @param codigo
	 * @param title
	 * @param edad
	 * @param tel
	 * @param profesion
	 * @param cargo
	 * @param bBuscar
	 * @param bGuardar
	 * @param bModificar
	 * @param bEliminar
	 */
	public void habilita(boolean codigo, boolean title, boolean cli_id, boolean director, boolean bBuscar, boolean bGuardar, boolean bModificar, boolean bEliminar)
	{
		textCod.setEditable(codigo);
		textTitle.setEditable(title);
		textCli_id.setEditable(cli_id);
		textDirector.setEditable(director);
		botonBuscar.setEnabled(bBuscar);
		botonGuardar.setEnabled(bGuardar);
		botonModificar.setEnabled(bModificar);
		botonEliminar.setEnabled(bEliminar);
	}
}
