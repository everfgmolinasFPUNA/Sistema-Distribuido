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

import java.util.ArrayList;
import java.util.List;

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
            
            Log p = new Log();
            List <Log> historialTotal = new ArrayList<Log>();
			
            //3) Servidor siempre esperando
            while (true) {
            	Log historial = new Log();
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
                p = LogJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("Dato Recibida : " + p.getAcci髇() + ", " + p.getDatos() );
                
                long acci髇 = p.getAcci髇();
                
                
                	if(acci髇 ==1L)
		                try {
		                	HospitalCentral h = HospitalCentralJSON.stringObjeto(p.getDatos()) ;
		                	System.out.println(p.getDatos());
		                	daoHospitalCentral.insertar(h);
		                	System.out.println("Hospital creado");
		                	
		                	sendData = HospitalCentralJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    
		                }catch(Exception e) {
		                	System.out.println("Hospital NO insertada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
		                
                	else if(acci髇 ==2L)
                		try {
		                	HospitalCentral h = HospitalCentralJSON.stringObjeto(p.getDatos()) ;
		                	daoHospitalCentral.actualizar(h);
		                	System.out.println("Hospital actualizado exitosamente en la Base de datos");
		                	
		                	sendData = HospitalCentralJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    
		                }catch(Exception e) {
		                	System.out.println("Hospital NO actualizado en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==3L)
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	h.setEstado(3L);
		                	daoHospital.actualizar(h);
		                	System.out.println("Cama actualizado exitosamente en la Base de datos");
		                	
		                	sendData = HospitalJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    System.out.println(historialTotal);
		                    
		                }catch(Exception e) {
		                	System.out.println("Cama NO actualizado en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==4L)
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	h.setEstado(1l);
		                	daoHospital.insertar(h);
		                	System.out.println("Cama creada exitosamente en la Base de datos");
		                	
		                	sendData = HospitalJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    System.out.println(historialTotal);
		                    
		                }catch(Exception e) {
		                	System.out.println("Cama NO creada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==5L)
                		try {
		                	HospitalCentral h = new HospitalCentral();//HospitalCentralJSON.stringObjeto(p.getDatos()) ;
		                	List<String> listaResumen = new ArrayList<String>();
		                	listaResumen = daoHospitalCentral.estadoHospitales();
		                	System.out.println("Consultas realizadas");
		                	h.setResumen(listaResumen);
		                	System.out.println(HospitalCentralJSON.listObjetoString(h));
		                	sendData = HospitalCentralJSON.listObjetoString(h).getBytes();;
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    
		                    serverSocket.send(sendPacket);
		                    
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    
		                }catch(Exception e) {
		                	System.out.println("consultas no realizadas " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==6L)
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	h.setEstado(1L);
		                	daoHospital.insertar(h);
		                	System.out.println("Cama desocupada exitosamente en la Base de datos");
		                	
		                	sendData = HospitalJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    
		                }catch(Exception e) {
		                	System.out.println("Cama NO desocupada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==7L)
                		try {
		                	Hospital h = HospitalJSON.stringObjeto(p.getDatos()) ;
		                	h.setEstado(2L);
		                	daoHospital.insertar(h);
		                	System.out.println("Cama ocupada exitosamente en la Base de datos");
		                	
		                	sendData = HospitalJSON.objetoString(h).getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                }catch(Exception e) {
		                	System.out.println("Cama NO ocupada en la Base de datos, raz贸n: " + e.getLocalizedMessage());
		                }
                		
                	else if(acci髇 ==8L)
                		try {
                			historial.setAcci髇(acci髇);
		                    historial.setDatos(datoRecibido);
		                    historial.setIPAddress(IPAddress);
		                    historial.setPort(port);
		                    historialTotal.add(historial);
		                    String hist = LogJSON.ListLogs(historialTotal);
		                    System.out.println(historialTotal);
		                	System.out.println("Historial recibido");
		                	
		                	sendData = hist.getBytes();
		                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
		                    serverSocket.send(sendPacket);
		                    
		                    
		                }catch(Exception e) {
		                	System.out.println("Historial no recibido " + e.getLocalizedMessage());
		                }
	                
                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                

            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

