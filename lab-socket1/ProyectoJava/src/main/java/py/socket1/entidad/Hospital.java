package py.socket1.entidad;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

	Long nroCama;
	Long estado;
	Long nroHospital;
	
	public Hospital() {
		
	}

	public Hospital(Long nroHospital, Long nroCama, Long estado){
		this.nroCama = nroCama;
		this.nroHospital = nroHospital;
		this.estado = estado;
		
	}


	public Long getNroCama() {
		return nroCama;
	}


	public void setNroCama(Long nroCama) {
		this.nroCama = nroCama;
	}


	public Long getEstado() {
		return estado;
	}


	public void setEstado(Long estado) {
		this.estado = estado;
	}


	public Long getNroHospital() {
		return nroHospital;
	}


	public void setNroHospital(Long nroHospital) {
		this.nroHospital = nroHospital;
	}

	
}
