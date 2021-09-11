package py.una.server.tcp;


import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket("localhost", 4444);
            // envia el cliente
            out = new PrintWriter(unSocket.getOutputStream(), true);

            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {	//Excepcion por si no se encuentra el servidor
            System.err.println("Host NO ENCONTRADO");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de Entrada/salida en la conexion al host");
            System.exit(1);
        }

        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println("El Servidor es: " + fromServer);
            if (fromServer.equals("Bye")) {
                break;
            }

            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("El Cliente es: " + fromUser);

                //escribimos al servidor
                out.println(fromUser);
            }
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
}
