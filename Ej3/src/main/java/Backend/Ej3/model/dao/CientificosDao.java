package Backend.Ej3.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Backend.Ej3.model.conexion.Conexion;
import Backend.Ej3.model.dto.Cientificos;

public class CientificosDao {
	
	public void registrarCientificos(Cientificos miCientificos)
	{
		Conexion conex= new Conexion();
		
		try 
		{
			Statement st = conex.getConnection().createStatement();
			
			String sql= "INSERT INTO cientificos VALUES ('"+miCientificos.getDni()+"', '"
					+miCientificos.getNomApels()+"');";
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

	public Cientificos buscarCientificos(String dni) 
	{
		Conexion conex= new Conexion();
		Cientificos cientificos= new Cientificos();
		boolean existe=false;
		
		try 
		{
			String sql= "SELECT * FROM cientificos where DNI = ? ";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			consulta.setString(1, dni);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				existe=true;
				cientificos.setDni(res.getString("DNI"));
				cientificos.setNomApels(res.getString("NomApels"));
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
			return cientificos;
		}
		else return null;				
	}

	public void modificarCientificos(Cientificos miCientificos) {
		
		Conexion conex= new Conexion();
		
		try
		{
			String consulta="UPDATE cientificos SET DNI= ? , NomApels = ? WHERE DNI = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
            estatuto.setString(1, miCientificos.getDni());
            estatuto.setString(2, miCientificos.getNomApels());
            estatuto.setString(3, miCientificos.getDni());
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

	public void eliminarCientificos(String codigo)
	{
		Conexion conex= new Conexion();
		
		try 
		{
			String sql= "DELETE FROM cientificos WHERE DNI='"+codigo+"'";
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

	public Object[] recogerDnisCientificos()
	{
		Conexion conex= new Conexion();
		ArrayList<String> array = new ArrayList<String>();
		
		try 
		{
			String sql= "SELECT dni FROM cientificos";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				array.add(res.getString("dni"));
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
