package Backend.Ej2.model.service;

import javax.swing.JOptionPane;

import Backend.Ej2.controller.VideosController;
import Backend.Ej2.model.dao.VideosDao;
import Backend.Ej2.model.dto.Videos;

public class VideosServ {

	private VideosController videosController; 
	public static boolean consultaVideos=false;
	public static boolean modificaVideos=false;
	
	//Metodo de vinculación con el controller principal
	public void setvideosController(VideosController videosController) 
	{
		this.setController(videosController);		
	}

	//Metodo que valida los datos de Registro antes de pasar estos al DAO
	public void validarRegistro(Videos miVideos) 
	{
		VideosDao miVideosDao;
		if (miVideos.getId() > 0) 
		{
			miVideosDao = new VideosDao();
			miVideosDao.registrarVideos(miVideos);						
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"El documento de los videos debe ser mayor a 0", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//Metodo que valida los datos de consulta antes de pasar estos al DAO
	public Videos validarConsulta(String codigoVideos) 
	{
		VideosDao miVideosDao;
		
		try 
		{
			int codigo=Integer.parseInt(codigoVideos);	
			if (codigo > 0) 
			{
				miVideosDao = new VideosDao();
				consultaVideos=true;
				return miVideosDao.buscarVideos(codigo);						
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El documento de los videos debe ser mayor a 0", "Advertencia", JOptionPane.WARNING_MESSAGE);
				consultaVideos=false;
			}
			
		}
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(null, "Debe ingresar un dato numerico", "Error", JOptionPane.ERROR_MESSAGE);
			consultaVideos=false;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha presentado un Error", "Error", JOptionPane.ERROR_MESSAGE);
			consultaVideos=false;
		}
					
		return null;
	}

	//Metodo que valida los datos de Modificación antes de pasar estos al DAO
	public void validarModificacion(Videos miVideos) 
	{
		VideosDao miVideosDao;
		if (miVideos.getTitle().length()>5) 
		{
			miVideosDao = new VideosDao();
			miVideosDao.modificarVideos(miVideos);	
			modificaVideos=true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "El titulo de los videos debe ser mayor a 5 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
			modificaVideos=false;
		}
	}

	//Metodo que valida los datos de Eliminación antes de pasar estos al DAO
	public void validarEliminacion(String codigo) 
	{
		VideosDao miVideosDao=new VideosDao();
		miVideosDao.eliminarVideos(codigo);
	}

	
	
	public VideosController getVideosController() 
	{
		return videosController;
	}

	public void setController(VideosController videosController) 
	{
		this.videosController = videosController;
	}
}
