package Backend.Ej2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Backend.Ej2.model.conexion.Conexion;
import Backend.Ej2.model.dto.Cliente;

public class ClienteDao {
	
	public void registrarCliente(Cliente miCliente)
	{
		Conexion conex= new Conexion();
		
		try 
		{
			Statement st = conex.getConnection().createStatement();
			
			String sql= "INSERT INTO cliente VALUES ('"+miCliente.getId()+"', '"
					+miCliente.getNombre()+"', '"+miCliente.getApellido()+"', '"
					+miCliente.getDireccion()+"', '"+miCliente.getDni()+"', '"+miCliente.getFecha()+"');";
			st.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println(sql);
			st.close();
			conex.desconectar();
			
		} 
		catch (SQLException e) 
		{
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}
	}

	public Cliente buscarCliente(int codigo) 
	{
		Conexion conex= new Conexion();
		Cliente cliente= new Cliente();
		boolean existe=false;
		
		try 
		{
			String sql= "SELECT * FROM cliente where id = ? ";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			consulta.setInt(1, codigo);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				existe=true;
				cliente.setId(Integer.parseInt(res.getString("id")));
				cliente.setNombre(res.getString("nombre"));
				cliente.setApellido(res.getString("apellido"));
				cliente.setDireccion(res.getString("direccion"));
				cliente.setDni(Integer.parseInt(res.getString("dni")));
				cliente.setFecha(""+res.getDate("fecha"));
			 }
			
			res.close();
			conex.desconectar();
			
			System.out.println(sql);
					
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		
		if (existe) 
		{
			return cliente;
		}
		else return null;				
	}

	public void modificarCliente(Cliente miCliente) {
		
		Conexion conex= new Conexion();
		
		try
		{
			String consulta="UPDATE cliente SET id= ? , nombre = ? , apellido=? , direccion=? , dni= ? , fecha= ? WHERE id= ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
            estatuto.setInt(1, miCliente.getId());
            estatuto.setString(2, miCliente.getNombre());
            estatuto.setString(3, miCliente.getApellido());
            estatuto.setString(4, miCliente.getDireccion());
            estatuto.setInt(5,miCliente.getDni());
            estatuto.setString(6, miCliente.getFecha());
            estatuto.setInt(7, miCliente.getId());
            estatuto.executeUpdate();
            
            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(consulta);
         
        }
		catch(SQLException	 e)
		{
			System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	public void eliminarCliente(String codigo)
	{
		Conexion conex= new Conexion();
		
		try 
		{
			String sql= "DELETE FROM cliente WHERE id='"+codigo+"'";
			Statement st = conex.getConnection().createStatement();
			st.executeUpdate(sql);
			
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(sql);
			st.close();
			conex.desconectar();
			
		} 
		catch (SQLException e) 
		{
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}
	}

	public Object[] recogerIdsCliente()
	{
		Conexion conex= new Conexion();
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		try 
		{
			String sql= "SELECT id FROM cliente";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				array.add(res.getInt("id"));
			 }
			
			res.close();
			conex.desconectar();
					
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		return array.toArray();
	}
}
