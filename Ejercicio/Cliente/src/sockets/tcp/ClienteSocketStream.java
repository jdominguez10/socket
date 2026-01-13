package sockets.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClienteSocketStream {

    public static void main(String[] args) {
    	Socket socket = null; 
        String host = "192.168.59.109";   // Cambia por la IP del servidor si está en otra máquina
        int port = 5554;

        // OJO: tu servidor lee SOLO 1 vez y con buffer de 25 bytes
        String mensaje = "hola servidor"; // <= 25 bytes recomendado

        try  {
        	socket = new Socket();
        	socket.connect(new InetSocketAddress(host, port), 3000);

            // Enviar al servidor
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            pw.print(mensaje);     // tu servidor usa is.read(buffer) (no espera '\n')
            pw.flush();            // por si acaso

            // Leer respuesta del servidor (el servidor envía con println)
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String respuesta = br.readLine();

            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	if(socket != null) {
        		try {
					socket.close();
				} catch (IOException e) {
					System.err.print(e.toString());
				}
        	}
        }
    }
}
