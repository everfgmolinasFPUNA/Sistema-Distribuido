package py.socket1.entidad;

import java.net.InetAddress;

public class Log {
	Long accion;
	String nombreAccion;
	InetAddress IPAddress;
	int port;
	String datos;
	
	public Log() {
		
	}
	
	public Log(Long accion, String nombreAccion, InetAddress IPAddress, int port, String datos) {
		this.accion = accion;
		this.nombreAccion = nombreAccion;
		this.IPAddress = IPAddress;
		this.port= port;
		this.datos = datos;
	}

	public Long getAccion() {
		return accion;
	}

	public void setAccion(Long acción) {
		this.accion = acción;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public InetAddress getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(InetAddress iPAddress) {
		IPAddress = iPAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	@Override
	public String toString() {			
		return "Log [acción=" + accion + ", nombreAcción=" + nombreAccion + ", IPAddress=" + IPAddress + ", port="
				+ port + ", datos=" + datos + "]";
	}
	
}
