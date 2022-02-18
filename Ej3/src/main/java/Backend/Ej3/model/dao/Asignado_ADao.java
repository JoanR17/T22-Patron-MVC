package Backend.Ej3.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Backend.Ej3.model.conexion.Conexion;
import Backend.Ej3.model.dto.Asignado_A;

public class Asignado_ADao {
	
	public void asignarCientificoProyecto(Asignado_A miAsignado)
	{

		Conexion conex= new Conexion();
		
		try 
		{
			
			boolean existe = false;
			
			String sql= "SELECT * FROM asignado_a where Cientifico = ? ";
			PreparedStatement consulta = conex.getConnection().prepareStatement(sql);
			consulta.setString(1, miAsignado.getCientifico());
			ResultSet res = consulta.executeQuery();
			
			while(res.next())
			{
				existe=true;
			}

			res.close();
			String sql2;
			
			if(!existe)
			{
				Statement st = conex.getConnection().createStatement();
				sql2 = "INSERT INTO asignado_a VALUES ('"+miAsignado.getCientifico()+"', '"
						+miAsignado.getProyecto()+"');";
				st.executeUpdate(sql2);
				st.close();
			}
			else
			{
				sql2 = "UPDATE asignado_a SET Cientifico = ? , Proyecto = ? WHERE Cientifico = ? ";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(sql2);
				
	            estatuto.setString(1, miAsignado.getCientifico());
	            estatuto.setString(2, miAsignado.getProyecto());
	            estatuto.setString(3, miAsignado.getCientifico());
	            estatuto.executeUpdate();
			}
			
			JOptionPane.showMessageDialog(null, "Se ha asignado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println(sql2);
			conex.desconectar();
			
		} 
		catch (SQLException e) 
		{
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}
	}

}
