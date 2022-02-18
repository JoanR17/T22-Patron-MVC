package Backend.Ej3.controller;

import Backend.Ej3.model.dto.Cientificos;
import Backend.Ej3.model.service.CientificosServ;
import Backend.Ej3.view.VentanaBuscarCientificos;
import Backend.Ej3.view.VentanaPrincipal;
import Backend.Ej3.view.VentanaRegistroCientificos;

public class CientificosController {
	
	private CientificosServ cientificosServ;
	private Asignado_AController asignado_aController;
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaRegistroCientificos miVentanaRegistroCientificos;
	private VentanaBuscarCientificos miVentanaBuscarCientificos;
	
	//Metodos getter Setters de vistas
	public VentanaPrincipal getMiVentanaPrincipal() 
	{
		return miVentanaPrincipal;
	}
	
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) 
	{
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	public VentanaRegistroCientificos getMiVentanaRegistroCientificos() {
		return miVentanaRegistroCientificos;
	}
	
	public void setMiVentanaRegistroCientificos(VentanaRegistroCientificos miVentanaRegistro)
	{
		this.miVentanaRegistroCientificos = miVentanaRegistro;
	}
	
	public VentanaBuscarCientificos getMiVentanaBuscarCientificos() 
	{
		return miVentanaBuscarCientificos;
	}
	
	public void setMiVentanaBuscarCientificos(VentanaBuscarCientificos miVentanaBuscarCientificos) 
	{
		this.miVentanaBuscarCientificos = miVentanaBuscarCientificos;
	}
	
	public CientificosServ getCientificosServ() 
	{
		return cientificosServ;
	}
	
	public void setCientificosServ(CientificosServ cientificosServ) 
	{
		this.cientificosServ = cientificosServ;
	}
	
	//Hace visible las vistas de Registro y Consulta
	public void mostrarVentanaRegistroCientificos() 
	{
		miVentanaRegistroCientificos.setVisible(true);
	}
	public void mostrarVentanaConsultaCientificos() 
	{
		miVentanaBuscarCientificos.setVisible(true);
	}
	
	//Llamadas a los metodos CRUD de la capa service para validar los datos de las vistas
	public void registrarCientificos(Cientificos miCientificos) 
	{
		cientificosServ.validarRegistro(miCientificos, asignado_aController.getMiVentanaAsignar_A());
	}
	
	public Cientificos buscarCientificos(String codigoCientificos) 
	{
		return cientificosServ.validarConsulta(codigoCientificos);
	}
	
	public void modificarCientificos(Cientificos miCientificos) 
	{
		cientificosServ.validarModificacion(miCientificos);
	}
	
	public void eliminarCientificos(String codigo) 
	{
		cientificosServ.validarEliminacion(codigo, asignado_aController.getMiVentanaAsignar_A());
	}
	

	public void setAsignado_AController(Asignado_AController asignado_aController) 
	{
		this.asignado_aController = asignado_aController;
	}
}
