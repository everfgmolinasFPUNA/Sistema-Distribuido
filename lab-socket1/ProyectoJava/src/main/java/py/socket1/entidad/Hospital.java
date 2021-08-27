package py.socket1.entidad;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

	Long nroCama;
	byte estado;
	Long nroHospital;
	
	public Hospital() {
		
	}

	public Hospital(Long nroHospital, Long nroCama, byte estado){
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


	public byte getEstado() {
		return estado;
	}


	public void setEstado(byte estado) {
		this.estado = estado;
	}


	public Long getNroHospital() {
		return nroHospital;
	}


	public void setNroHospital(Long nroHospital) {
		this.nroHospital = nroHospital;
	}

	
}
