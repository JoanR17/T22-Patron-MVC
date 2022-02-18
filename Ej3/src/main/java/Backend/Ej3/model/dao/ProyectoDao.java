package Backend.Ej3.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Backend.Ej3.model.conexion.Conexion;
import Backend.Ej3.model.dto.Proyecto;

public class ProyectoDao {

	
	public void registrarProyecto(Proyecto miProyecto)
	{
		Conexion conex = new Conexion();
		
		try 
		{
			Statement st = conex.getConnection().createStatement();
			
			String sql= "INSERT INTO proyecto VALUES ('"+miProyecto.getId()+"', '"
					+miProyecto.getNombre()+"', '"+miProyecto.getHoras()+"');";
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

	public Proyecto buscarProyecto(String codigo) 
	{
		Conexion conex= new Conexion();
		Proyecto proyecto= new Proyecto();
		boolean existe=false;
		
		try 
		{
			String sql= "SELECT * FROM proyecto where id = ? ";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			consulta.setString(1, codigo);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				existe=true;
				proyecto.setId(res.getString("Id"));
				proyecto.setNombre(res.getString("Nombre"));
				proyecto.setHoras(res.getInt("Horas"));
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
			return proyecto;
		}
		else return null;				
	}

	public void modificarProyecto(Proyecto miProyecto) {
		
		Conexion conex= new Conexion();
		
		try
		{
			String consulta="UPDATE proyecto SET Id = ? , Nombre = ? , Horas = ? WHERE Id = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
            estatuto.setString(1, miProyecto.getId());
            estatuto.setString(2, miProyecto.getNombre());
            estatuto.setInt(3, miProyecto.getHoras());
            estatuto.setString(4, miProyecto.getId());
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

	public void eliminarProyecto(String codigo)
	{
		Conexion conex= new Conexion();
		
		try 
		{
			String sql= "DELETE FROM proyecto WHERE id='"+codigo+"'";
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

	public Object[] recogerIdsProyectos()
	{
		Conexion conex= new Conexion();
		ArrayList<String> array = new ArrayList<String>();
		
		try 
		{
			String sql= "SELECT Id FROM proyecto";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				array.add(res.getString("Id"));
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
