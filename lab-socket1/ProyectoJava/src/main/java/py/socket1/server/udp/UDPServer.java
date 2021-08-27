package py.socket1.server.udp;

import java.io.*;
import java.net.*;

import py.socket1.bd.HospitalCentralDAO;
import py.socket1.bd.HospitalDAO;
import py.socket1.entidad.Hospital;
import py.socket1.entidad.HospitalCentral;
import py.socket1.entidad.HospitalCentralJSON;
import py.socket1.entidad.HospitalJSON;
import py.socket1.entidad.Log;
import py.socket1.entidad.LogJSON;

public class UDPServer {
	
	// Acci髇 1, crear Hospital, Acci髇 2, actualizar Hospital
	
	
    public static void main(String[] a){
        
        // Variables
        int puertoServidor = 9876;
        HospitalCentralDAO daoHospitalCentral = new HospitalCentralDAO();
        HospitalDAO daoHospital = new HospitalDAO();
        
        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor Sistemas Distribuidos - UDP ");
			
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

			
            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido );
                Log p = LogJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("Dato Recibida : " + p.getAcci髇() + ", " + p.getDatos() );
                
                int acci髇 = p.getAcci髇();
                
                switch (acci髇) {
                	case 1:
		                try {
		                	HospitalCentral h = HospitalCentralJSON.stringObjeto(p.getDatos()) ;
		                	daoHospitalCentral.insertar(h);
		                	System.out.println("Hospital creado");
		                }catch(Exception e) {
		                	System.out.println("Hospital NO insertada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
		                
                	case 2:
                		try {
		                	HospitalCentral h = HospitalCentralJSON.stringObjeto(p.getDatos()) ;
		                	daoHospitalCentral.actualizar(h);
		                	System.out.println("Hospital actualizado exitosamente en la Base de datos");
		                }catch(Exception e) {
		                	System.out.println("Hospital NO actualizado en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	case 3:
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	Byte e = 3;
		                	h.setEstado(e);
		                	daoHospital.actualizar(h);
		                	System.out.println("Cama actualizado exitosamente en la Base de datos");
		                }catch(Exception e) {
		                	System.out.println("Cama NO actualizado en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	case 4:
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	Byte e = 1;
		                	h.setEstado(e);
		                	daoHospital.insertar(h);
		                	System.out.println("Cama creada exitosamente en la Base de datos");
		                }catch(Exception e) {
		                	System.out.println("Cama NO creada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	case 5:
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	Byte e = 1;
		                	h.setEstado(e);
		                	daoHospital.insertar(h);
		                	System.out.println("Cama creada exitosamente en la Base de datos");
		                }catch(Exception e) {
		                	System.out.println("Cama NO creada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
	                
                }
                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = HospitalCentralJSON.objetoString(p).getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress,port);

                serverSocket.send(sendPacket);

            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

