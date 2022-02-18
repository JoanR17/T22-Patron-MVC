package Backend.Ej3.model.service;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Backend.Ej3.controller.CientificosController;
import Backend.Ej3.model.dao.CientificosDao;
import Backend.Ej3.model.dto.Cientificos;
import Backend.Ej3.view.VentanaAsignar_a;

public class CientificosServ {

	private CientificosController cientificosController; 
	public static boolean consultaCientificos=false;
	public static boolean modificaCientificos=false;
	
	//Metodo de vinculación con el controller principal
	public void setcientificosController(CientificosController cientificosController) 
	{
		this.setController(cientificosController);		
	}

	//Metodo que valida los datos de Registro antes de pasar estos al DAO
	public void validarRegistro(Cientificos miCientificos, VentanaAsignar_a ventanaAsignar_a) 
	{
		CientificosDao miCientificosDao;
		if (miCientificos.getDni().length() == 9) 
		{
			miCientificosDao = new CientificosDao();
			miCientificosDao.registrarCientificos(miCientificos);		
			ventanaAsignar_a.getTextDniCientifico().setModel(new DefaultComboBoxModel<Object>((new CientificosDao()).recogerDnisCientificos()));		
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"El dni del cientificos debe ser de 9 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//Metodo que valida los datos de consulta antes de pasar estos al DAO
	public Cientificos validarConsulta(String dniCientificos) 
	{
		CientificosDao miCientificosDao;
		
		try 
		{
			String dni = dniCientificos;	
			if (dni.length() == 9) 
			{
				miCientificosDao = new CientificosDao();
				consultaCientificos=true;
				return miCientificosDao.buscarCientificos(dni);						
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El dni del cientificos debe ser de 9 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
				consultaCientificos=false;
			}
			
		}
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(null, "Debe ingresar un dato numerico", "Error", JOptionPane.ERROR_MESSAGE);
			consultaCientificos=false;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha presentado un Error", "Error", JOptionPane.ERROR_MESSAGE);
			consultaCientificos=false;
		}
					
		return null;
	}

	//Metodo que valida los datos de Modificación antes de pasar estos al DAO
	public void validarModificacion(Cientificos miCientificos) 
	{
		CientificosDao miCientificosDao;
		if (miCientificos.getNomApels().length() > 2) 
		{
			miCientificosDao = new CientificosDao();
			miCientificosDao.modificarCientificos(miCientificos);	
			modificaCientificos=true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "El nombre de los cientificos debe ser mayor a 2 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
			modificaCientificos=false;
		}
	}

	//Metodo que valida los datos de Eliminación antes de pasar estos al DAO
	public void validarEliminacion(String codigo, VentanaAsignar_a ventanaAsignar_a) 
	{
		CientificosDao miCientificosDao=new CientificosDao();
		miCientificosDao.eliminarCientificos(codigo);
		ventanaAsignar_a.getTextDniCientifico().setModel(new DefaultComboBoxModel<Object>((new CientificosDao()).recogerDnisCientificos()));
	}

	
	
	public CientificosController getCientificosController() 
	{
		return cientificosController;
	}

	public void setController(CientificosController cientificosController) 
	{
		this.cientificosController = cientificosController;
	}

}
