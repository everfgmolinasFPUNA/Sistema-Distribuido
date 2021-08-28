package py.socket1.entidad;
import java.util.ArrayList;
import java.util.List;

public class HospitalCentral {
	String nombreHospital;
	Long nroHospital;
	
	List<String> resumen;
	
	
	public List<String> getResumen() {
		return resumen;
	}

	public void setResumen(List<String> resumen) {
		this.resumen = resumen;
	}

	public HospitalCentral(){
		this.nombreHospital = "";
		this.nroHospital = 0L;
		
	}
	
	public HospitalCentral(Long nroHospital, String nombreHospital){
		this.nombreHospital = nombreHospital;
		this.nroHospital = nroHospital;
		
	}

	public String getNombreHospital() {
		return nombreHospital;
	}

	public void setNombreHospital(String nombreHospital) {
		this.nombreHospital = nombreHospital;
	}

	public Long getNroHospital() {
		return nroHospital;
	}

	public void setNroHospital(Long nroHospital) {
		this.nroHospital = nroHospital;
	}

}