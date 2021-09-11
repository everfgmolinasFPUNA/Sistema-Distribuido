package py.socket1.client;

//importacion de paquetes de java necesarios
import java.io.*;
import java.net.*;
//importacion de entidades necesarias 
import py.socket1.entidad.Hospital;
import py.socket1.entidad.HospitalJSON;
import py.socket1.entidad.HospitalCentral;
import py.socket1.entidad.HospitalCentralJSON;
import py.socket1.entidad.Log;
import py.socket1.entidad.LogJSON;

class UDPClient {

    public static void main(String a[]) throws Exception {

        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        
        try {

            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.print("Ingrese la accion a realizar (debe ser numerico): ");
            String accionstr = inFromUser.readLine();
            Long accion = 0L;
            try {
            	accion = Long.parseLong(accionstr);
            }catch(Exception e1) {
            	
            }
            String nroHospitalstr;
            String nroCamastr;
            Long nroHospital = 0L;
            Long nroCama = 0L;
            String nombre;
            HospitalCentral p;
            Hospital h;
            Log AccionDato;
            String datoPaquete ;
            DatagramPacket sendPacket;
            
            
            if(accion ==1L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el nombre: ");
                    nombre = inFromUser.readLine();
                    
                    p = new HospitalCentral(nroHospital, nombre);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setPort(puertoServidor);
                    //AccionDato.setIPAddress(IPAddress);
                    AccionDato.setDatos(HospitalCentralJSON.objetoString(p));
                    System.out.println(AccionDato);
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        System.out.println(respuesta);
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }

            }else if(accion ==2L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el nombre: ");
                    nombre = inFromUser.readLine();
                    
                    p = new HospitalCentral(nroHospital, nombre);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos(HospitalCentralJSON.objetoString(p));
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }else if(accion ==3L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el numero de cama (debe ser numerico): ");
                    nroCamastr = inFromUser.readLine();
                    nroCama = 0L;
                    try {
                    	nroCama = Long.parseLong(nroCamastr);
                    }catch(Exception e1) {
                    	
                    }
                    
                    h = new Hospital(nroHospital, nroCama, 3L);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos(HospitalJSON.objetoString(h));
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
            
            }else if(accion ==4L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el numero de cama (debe ser numerico): ");
                    nroCamastr = inFromUser.readLine();
                    nroCama = 0L;
                    try {
                    	nroCama = Long.parseLong(nroCamastr);
                    }catch(Exception e1) {
                    	
                    }
                    
                    h = new Hospital(nroHospital, nroCama, 1L);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos(HospitalJSON.objetoString(h));
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }else if(accion ==5L) {
                    AccionDato = new Log();
                    AccionDato.setAcci�n(5L);
                    AccionDato.setDatos("Consulta de estados");
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        
                        HospitalCentral presp = HospitalCentralJSON.objetoStringList(respuesta.trim());
                        System.out.println(presp.getResumen());
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }else if(accion ==6L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el numero de cama (debe ser numerico): ");
                    nroCamastr = inFromUser.readLine();
                    nroCama = 0L;
                    try {
                    	nroCama = Long.parseLong(nroCamastr);
                    }catch(Exception e1) {
                    	
                    }
                    
                    h = new Hospital(nroHospital, nroCama, 1L);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos(HospitalJSON.objetoString(h));
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }else if(accion ==7L) {
            		System.out.print("Ingrese el numero de hospital (debe ser numerico): ");
                    nroHospitalstr = inFromUser.readLine();
                    nroHospital = 0L;
                    try {
                    	nroHospital = Long.parseLong(nroHospitalstr);
                    }catch(Exception e1) {
                    	
                    }
                    System.out.print("Ingrese el numero de cama (debe ser numerico): ");
                    nroCamastr = inFromUser.readLine();
                    nroCama = 0L;
                    try {
                    	nroCama = Long.parseLong(nroCamastr);
                    }catch(Exception e1) {
                    	
                    }
                    
                    h = new Hospital(nroHospital, nroCama, 2L);
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos(HospitalJSON.objetoString(h));
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        System.out.println(respuesta);
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }else if(accion ==8L) {
                    
                    AccionDato = new Log();
                    AccionDato.setAcci�n(accion);
                    AccionDato.setDatos("Consulta de registros");
                    datoPaquete = LogJSON.objetoString(AccionDato); 
                    sendData = datoPaquete.getBytes();

                    System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                    sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                    clientSocket.send(sendPacket);
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperamos si viene la respuesta.");

                    //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                    clientSocket.setSoTimeout(10000);

                    try {
                        // ESPERAMOS LA RESPUESTA, BLOQUENTE
                        clientSocket.receive(receivePacket);

                        String respuesta = new String(receivePacket.getData());
                        HospitalCentral presp = HospitalCentralJSON.stringObjeto(respuesta.trim());
                        System.out.println(presp.getResumen());
                        
                        InetAddress returnIPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                       
                        

                    } catch (SocketTimeoutException ste) {

                        System.out.println("TimeOut: El paquete udp se asume perdido.");
                    }
                    
            }
            
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 

