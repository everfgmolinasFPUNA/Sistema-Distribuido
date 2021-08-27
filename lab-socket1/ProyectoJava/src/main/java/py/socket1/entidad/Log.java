package py.socket1.entidad;

import java.net.InetAddress;

public class Log {
	int acción;
	String nombreAcción;
	InetAddress IPAddress;
	int port;
	String datos;
	
	public Log() {
		
	}
	
	public Log(int acción, String nombreAcción, InetAddress IPAddress, int port, String datos) {
		this.acción = acción;
		this.nombreAcción = nombreAcción;
		this.IPAddress = IPAddress;
		this.port= port;
		this.datos = datos;
	}

	public int getAcción() {
		return acción;
	}

	public void setAcción(int acción) {
		this.acción = acción;
	}

	public String getNombreAcción() {
		return nombreAcción;
	}

	public void setNombreAcción(String nombreAcción) {
		this.nombreAcción = nombreAcción;
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
		return "Log [acción=" + acción + ", nombreAcción=" + nombreAcción + ", IPAddress=" + IPAddress + ", port="
				+ port + ", datos=" + datos + "]";
	}
	
}
