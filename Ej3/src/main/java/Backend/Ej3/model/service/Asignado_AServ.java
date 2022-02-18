package Backend.Ej3.model.service;

import javax.swing.JOptionPane;

import Backend.Ej3.controller.Asignado_AController;
import Backend.Ej3.model.dao.Asignado_ADao;
import Backend.Ej3.model.dto.Asignado_A;

public class Asignado_AServ {

	private Asignado_AController asignado_aController; 
	public static boolean assignarAsignado_a=false;
	
	//Metodo de vinculación con el controller principal
	public void setasignado_aController(Asignado_AController asignado_aController) 
	{
		this.setController(asignado_aController);		
	}

	//Metodo que valida los datos de consulta antes de pasar estos al DAO
	public void validarAsignacion(Asignado_A miAsignado_A) 
	{
		Asignado_ADao miAsignado_ADao;
		
		try 
		{
			miAsignado_ADao = new Asignado_ADao();
			assignarAsignado_a = true;
			miAsignado_ADao.asignarCientificoProyecto(miAsignado_A);		
		}
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(null, "Debe ingresar un dato numerico", "Error", JOptionPane.ERROR_MESSAGE);
			assignarAsignado_a=false;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha presentado un Error", "Error", JOptionPane.ERROR_MESSAGE);
			assignarAsignado_a=false;
		}
	}

	public Asignado_AController getAsignado_aController() 
	{
		return asignado_aController;
	}

	public void setController(Asignado_AController asignado_aController) 
	{
		this.asignado_aController = asignado_aController;
	}
	
	
}
