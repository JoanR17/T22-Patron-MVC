package Backend.Ej1.model.service;

import javax.swing.JOptionPane;

import Backend.Ej1.controller.ClienteController;
import Backend.Ej1.model.dao.ClienteDao;
import Backend.Ej1.model.dto.Cliente;

public class ClienteServ {

	private ClienteController clienteController; 
	public static boolean consultaCliente=false;
	public static boolean modificaCliente=false;
	
	//Metodo de vinculación con el controller principal
	public void setclienteController(ClienteController clienteController) 
	{
		this.setController(clienteController);		
	}

	//Metodo que valida los datos de Registro antes de pasar estos al DAO
	public void validarRegistro(Cliente miCliente) 
	{
		ClienteDao miClienteDao;
		if (miCliente.getId() > 99) 
		{
			miClienteDao = new ClienteDao();
			miClienteDao.registrarCliente(miCliente);						
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"El documento de la cliente debe ser mas de 3 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//Metodo que valida los datos de consulta antes de pasar estos al DAO
	public Cliente validarConsulta(String codigoCliente) 
	{
		ClienteDao miClienteDao;
		
		try 
		{
			int codigo=Integer.parseInt(codigoCliente);	
			if (codigo > 99) 
			{
				miClienteDao = new ClienteDao();
				consultaCliente=true;
				return miClienteDao.buscarCliente(codigo);						
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El documento del cliente debe ser mas de 3 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
				consultaCliente=false;
			}
			
		}
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(null, "Debe ingresar un dato numerico", "Error", JOptionPane.ERROR_MESSAGE);
			consultaCliente=false;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha presentado un Error", "Error", JOptionPane.ERROR_MESSAGE);
			consultaCliente=false;
		}
					
		return null;
	}

	//Metodo que valida los datos de Modificación antes de pasar estos al DAO
	public void validarModificacion(Cliente miCliente) 
	{
		ClienteDao miClienteDao;
		if (miCliente.getNombre().length()>2) 
		{
			miClienteDao = new ClienteDao();
			miClienteDao.modificarCliente(miCliente);	
			modificaCliente=true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "El nombre del cliente debe ser mayor a 2 digitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
			modificaCliente=false;
		}
	}

	//Metodo que valida los datos de Eliminación antes de pasar estos al DAO
	public void validarEliminacion(String codigo) 
	{
		ClienteDao miClienteDao=new ClienteDao();
		miClienteDao.eliminarCliente(codigo);
	}

	
	
	public ClienteController getClienteController() 
	{
		return clienteController;
	}

	public void setController(ClienteController clienteController) 
	{
		this.clienteController = clienteController;
	}

}
