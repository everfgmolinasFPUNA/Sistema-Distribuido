package py.socket1.entidad;

import java.net.InetAddress;

public class Log {
	Long acci�n;
	String nombreAcci�n;
	InetAddress IPAddress;
	int port;
	String datos;
	
	public Log() {
		
	}
	
	public Log(Long acci�n, String nombreAcci�n, InetAddress IPAddress, int port, String datos) {
		this.acci�n = acci�n;
		this.nombreAcci�n = nombreAcci�n;
		this.IPAddress = IPAddress;
		this.port= port;
		this.datos = datos;
	}

	public Long getAcci�n() {
		return acci�n;
	}

	public void setAcci�n(Long acci�n) {
		this.acci�n = acci�n;
	}

	public String getNombreAcci�n() {
		return nombreAcci�n;
	}

	public void setNombreAcci�n(String nombreAcci�n) {
		this.nombreAcci�n = nombreAcci�n;
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
		return "Log [acci�n=" + acci�n + ", nombreAcci�n=" + nombreAcci�n + ", IPAddress=" + IPAddress + ", port="
				+ port + ", datos=" + datos + "]";
	}
	
}
