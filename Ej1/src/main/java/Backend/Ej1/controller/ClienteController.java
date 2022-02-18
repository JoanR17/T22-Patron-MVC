package Backend.Ej1.controller;

import Backend.Ej1.model.dto.Cliente;
import Backend.Ej1.model.service.ClienteServ;
import Backend.Ej1.view.VentanaBuscar;
import Backend.Ej1.view.VentanaPrincipal;
import Backend.Ej1.view.VentanaRegistro;

public class ClienteController {
	
	private ClienteServ clienteServ;
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaRegistro miVentanaRegistro;
	private VentanaBuscar miVentanaBuscar;
	
	//Metodos getter Setters de vistas
	public VentanaPrincipal getMiVentanaPrincipal() 
	{
		return miVentanaPrincipal;
	}
	
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) 
	{
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	public VentanaRegistro getMiVentanaRegistro() {
		return miVentanaRegistro;
	}
	
	public void setMiVentanaRegistro(VentanaRegistro miVentanaRegistro)
	{
		this.miVentanaRegistro = miVentanaRegistro;
	}
	
	public VentanaBuscar getMiVentanaBuscar() 
	{
		return miVentanaBuscar;
	}
	
	public void setMiVentanaBuscar(VentanaBuscar miVentanaBuscar) 
	{
		this.miVentanaBuscar = miVentanaBuscar;
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
	public void mostrarVentanaRegistro() 
	{
		miVentanaRegistro.setVisible(true);
	}
	public void mostrarVentanaConsulta() 
	{
		miVentanaBuscar.setVisible(true);
	}
	
	//Llamadas a los metodos CRUD de la capa service para validar los datos de las vistas
	public void registrarCliente(Cliente miCliente) 
	{
		clienteServ.validarRegistro(miCliente);
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
		clienteServ.validarEliminacion(codigo);
	}


}
