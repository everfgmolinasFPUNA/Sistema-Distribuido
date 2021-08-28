package py.socket1.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.socket1.entidad.HospitalCentral;
public class HospitalCentralDAO {
 
	/**
	 * 
	 * @param condiciones 
	 * @return
	 */
	public List<HospitalCentral> seleccionar() {
		String query = "SELECT nrohospital, nombre FROM hospitalcentral ";
		
		List<HospitalCentral> lista = new ArrayList<HospitalCentral>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		HospitalCentral p = new HospitalCentral();
        		p.setNroHospital(rs.getLong(1));
        		p.setNombreHospital(rs.getString(2));
        		
        		lista.add(p);
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}
	
	public List<HospitalCentral> seleccionarPorNroHospital(long cedula) {
		String SQL = "SELECT nrohospital, nombre FROM hospitalcentral WHERE nrohospital = ? ";
		
		List<HospitalCentral> lista = new ArrayList<HospitalCentral>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setLong(1, cedula);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		HospitalCentral p = new HospitalCentral();
        		p.setNroHospital(rs.getLong(1));
        		p.setNombreHospital(rs.getString(2));
        		
        		lista.add(p);
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}
	
    public long insertar(HospitalCentral p) throws SQLException {

        String SQL = "INSERT INTO hospitalcentral(nrohospital, nombre) "
                + "VALUES(?,?)";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, p.getNroHospital());
            pstmt.setString(2, p.getNombreHospital());
            
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        	
        return id;
    	
    	
    }
    
    public List<String> estadoHospitales() {
		
		//Listar Hospitales
		
		String SQL = "SELECT nrohospital, nombre FROM hospitalcentral";
		
		List<HospitalCentral> lista = new ArrayList<HospitalCentral>();
		List<String> listaTotal = new ArrayList<String>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(SQL);
        	while(rs.next()) {
        		HospitalCentral p = new HospitalCentral();
        		p.setNroHospital(rs.getLong(1));
        		p.setNombreHospital(rs.getString(2));
        		
        		lista.add(p);
        	}
        	
        	
        	for(int i = 0 ; i<lista.size(); i++) {
        		
        		SQL = "SELECT count(*) FROM hospital WHERE nrohospital = ? ";
        		PreparedStatement pstmt = conn.prepareStatement(SQL);
        		pstmt.setLong(1, lista.get(i).getNroHospital());
        		ResultSet rstotal = pstmt.executeQuery();
        		Long totalcamas = rstotal.getLong(1);
        		String result = "Hospital n˙mero " + lista.get(i) + "Total camas= " + totalcamas;
        		listaTotal.add(result);
        		
        		SQL = "SELECT count(*) FROM hospital WHERE nrohospital = ? AND estado=1";
        		pstmt = conn.prepareStatement(SQL);
        		pstmt.setLong(1, lista.get(i).getNroHospital());
        		rstotal = pstmt.executeQuery();
        		Long totalCamasDisponibles = rstotal.getLong(1);
        		result = "Hospital n˙mero " + lista.get(i) + "Total camas disponibles= " + totalCamasDisponibles;
        		listaTotal.add(result);
        		
        		SQL = "SELECT count(*) FROM hospital WHERE nrohospital = ? estado=2 ";
        		pstmt = conn.prepareStatement(SQL);
        		pstmt.setLong(1, lista.get(i).getNroHospital());
        		rstotal = pstmt.executeQuery();
        		Long totalOcupadas = rstotal.getLong(1);
        		result = "Hospital n˙mero " + lista.get(i) + "Total cama ocupadass= " + totalOcupadas;
        		listaTotal.add(result);
        		
        		SQL = "SELECT count(*) FROM hospital WHERE nrohospital = ? estado=3 ";
        		pstmt = conn.prepareStatement(SQL);
        		pstmt.setLong(1, lista.get(i).getNroHospital());
        		rstotal = pstmt.executeQuery();
        		Long totalAveriadas = rstotal.getLong(1);
        		result = "Hospital n˙mero " + lista.get(i) + "Total cama averiadass= " + totalAveriadas;
        		listaTotal.add(result);
        		
        		
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return listaTotal;

	}
	

    public long actualizar(HospitalCentral p) throws SQLException {

        String SQL = "UPDATE hospitalcentral SET nombre = ? WHERE nrohospital = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, p.getNombreHospital());
            pstmt.setLong(2, p.getNroHospital());
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    
    public long borrar(long nroHospital) throws SQLException {

        String SQL = "DELETE FROM hospitalcentral WHERE nrohospital = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, nroHospital);
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminaci√≥n: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    

}
