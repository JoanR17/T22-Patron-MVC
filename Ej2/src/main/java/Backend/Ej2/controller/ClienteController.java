package Backend.Ej2.controller;

import Backend.Ej2.model.dto.Cliente;
import Backend.Ej2.model.service.ClienteServ;
import Backend.Ej2.view.VentanaBuscarCliente;
import Backend.Ej2.view.VentanaPrincipal;
import Backend.Ej2.view.VentanaRegistroCliente;

public class ClienteController {
	
	private ClienteServ clienteServ;
	private VideosController videosController;
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaRegistroCliente miVentanaRegistroCliente;
	private VentanaBuscarCliente miVentanaBuscarCliente;
	
	//Metodos getter Setters de vistas
	public VentanaPrincipal getMiVentanaPrincipal() 
	{
		return miVentanaPrincipal;
	}
	
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) 
	{
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	public VentanaRegistroCliente getMiVentanaRegistroCliente() {
		return miVentanaRegistroCliente;
	}
	
	public void setMiVentanaRegistroCliente(VentanaRegistroCliente miVentanaRegistro)
	{
		this.miVentanaRegistroCliente = miVentanaRegistro;
	}
	
	public VentanaBuscarCliente getMiVentanaBuscarCliente() 
	{
		return miVentanaBuscarCliente;
	}
	
	public void setMiVentanaBuscarCliente(VentanaBuscarCliente miVentanaBuscarCliente) 
	{
		this.miVentanaBuscarCliente = miVentanaBuscarCliente;
	}
	
	public ClienteServ getClienteServ() 
	{
		return clienteServ;
	}
	
	public void setClienteServ(ClienteServ clienteServ) 
	{
		this.clienteServ = clienteServ;
	}
	
	//Hace visible las vistas de Registro y Consulta
	public void mostrarVentanaRegistroCliente() 
	{
		miVentanaRegistroCliente.setVisible(true);
	}
	public void mostrarVentanaConsultaCliente() 
	{
		miVentanaBuscarCliente.setVisible(true);
	}
	
	//Llamadas a los metodos CRUD de la capa service para validar los datos de las vistas
	public void registrarCliente(Cliente miCliente) 
	{
		clienteServ.validarRegistro(miCliente, videosController.getMiVentanaBuscarVideos(), videosController.getMiVentanaRegistroVideos());
	}
	
	public Cliente buscarCliente(String codigoCliente) 
	{
		return clienteServ.validarConsulta(codigoCliente);
	}
	
	public void modificarCliente(Cliente miCliente) 
	{
		clienteServ.validarModificacion(miCliente);
	}
	
	public void eliminarCliente(String codigo) 
	{
		clienteServ.validarEliminacion(codigo, videosController.getMiVentanaBuscarVideos(), videosController.getMiVentanaRegistroVideos());
	}
	

	public void setVideosController(VideosController videosController) 
	{
		this.videosController = videosController;
	}
}
