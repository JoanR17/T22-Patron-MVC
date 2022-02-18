package Backend.Ej2.controller;

import Backend.Ej2.model.dto.Videos;
import Backend.Ej2.model.service.VideosServ;
import Backend.Ej2.view.VentanaBuscarVideos;
import Backend.Ej2.view.VentanaPrincipal;
import Backend.Ej2.view.VentanaRegistroVideos;

public class VideosController {
	
	private VideosServ videosServ;
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaRegistroVideos miVentanaRegistroVideos;
	private VentanaBuscarVideos miVentanaBuscarVideos;
	
	//Metodos getter Setters de vistas
	public VentanaPrincipal getMiVentanaPrincipal() 
	{
		return miVentanaPrincipal;
	}
	
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) 
	{
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	public VentanaRegistroVideos getMiVentanaRegistroVideos() {
		return miVentanaRegistroVideos;
	}
	
	public void setMiVentanaRegistroVideos(VentanaRegistroVideos miVentanaRegistro)
	{
		this.miVentanaRegistroVideos = miVentanaRegistro;
	}
	
	public VentanaBuscarVideos getMiVentanaBuscarVideos() 
	{
		return miVentanaBuscarVideos;
	}
	
	public void setMiVentanaBuscarVideos(VentanaBuscarVideos miVentanaBuscarVideos) 
	{
		this.miVentanaBuscarVideos = miVentanaBuscarVideos;
	}
	
	public VideosServ getVideosServ() 
	{
		return videosServ;
	}
	
	public void setVideosServ(VideosServ videosServ) 
	{
		this.videosServ = videosServ;
	}
	
	//Hace visible las vistas de Registro y Consulta
	public void mostrarVentanaRegistroVideos() 
	{
		miVentanaRegistroVideos.setVisible(true);
	}
	public void mostrarVentanaConsultaVideos() 
	{
		miVentanaBuscarVideos.setVisible(true);
	}
	
	//Llamadas a los metodos CRUD de la capa service para validar los datos de las vistas
	public void registrarVideos(Videos miVideos) 
	{
		videosServ.validarRegistro(miVideos);
	}
	
	public Videos buscarVideos(String codigoVideos) 
	{
		return videosServ.validarConsulta(codigoVideos);
	}
	
	public void modificarVideos(Videos miVideos) 
	{
		videosServ.validarModificacion(miVideos);
	}
	
	public void eliminarVideos(String codigo) 
	{
		videosServ.validarEliminacion(codigo);
	}
}
