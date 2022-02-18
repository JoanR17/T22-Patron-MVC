package Backend.Ej3.controller;

import Backend.Ej3.model.dto.Asignado_A;
import Backend.Ej3.model.service.Asignado_AServ;
import Backend.Ej3.view.VentanaAsignar_a;
import Backend.Ej3.view.VentanaPrincipal;

public class Asignado_AController {
	
	private Asignado_AServ asignado_aServ;
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaAsignar_a miVentanaAsignar_A;
	
	public String test = "Hola";
	
	//Metodos getter Setters de vistas
	public VentanaPrincipal getMiVentanaPrincipal() 
	{
		return miVentanaPrincipal;
	}
	
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) 
	{
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	public Asignado_AServ getAsignado_aServ() 
	{
		return asignado_aServ;
	}

	public void setAsignado_aServ(Asignado_AServ asignado_aServ) 
	{
		this.asignado_aServ = asignado_aServ;
	}

	public VentanaAsignar_a getMiVentanaAsignar_A() 
	{
		return miVentanaAsignar_A;
	}

	public void setMiVentanaAsignar_A(VentanaAsignar_a miVentanaAsignar_A)
	{
		this.miVentanaAsignar_A = miVentanaAsignar_A;
	}

	//Hace visible las vistas de Registro y Consulta
	public void mostrarVentanaAsignarCientificos() 
	{
		miVentanaAsignar_A.setVisible(true);
	}
	
	//Llamadas a los metodos CRUD de la capa service para validar los datos de las vistas
	public void asignarCientificoProyecto(Asignado_A miAsignado_A) 
	{
		asignado_aServ.validarAsignacion(miAsignado_A);
	}
}
