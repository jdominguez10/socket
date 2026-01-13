package sockets.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket newSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5554);
			serverSocket.bind(addr);
			
			System.out.print("Servidor aceptando:" + addr.getHostName());
			newSocket = serverSocket.accept();

			/**###################################
			 *  Leer <= 'datos' enviados por el 'cliente'
			 * ##################################
			 */
			InputStream is = newSocket.getInputStream();
			
			byte[] buffer = new byte[25];
			int leidos = is.read(buffer);
			String recibido = new String(buffer, 0, leidos); 
			
			//System.out.println("Mensaje recibido: " + new String(mensaje));
			analizaMensaje(recibido);
			
			/**###################################
			 *  Enviar =>'datos' al 'cliente'
			 * ##################################
			 */
			//Obtenemos el flujo de salida del socket
		 OutputStream os = newSocket.getOutputStream();
		 PrintWriter pw = new PrintWriter(new OutputStreamWriter(os), true);
		 
		 String respuesta ="hola cliente, lo recibi";
		 pw.println(respuesta);
		 pw.flush();
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			System.out.println("Cerrando socket");
			try {
				if(newSocket!=null) {
					newSocket.close();
				}
				if(serverSocket != null)
					serverSocket.close();
			} catch (IOException e) {
				System.err.print(e.toString());
			}
		}

	}

	public static void analizaMensaje(String recibido) {
	
		int tam = recibido.length();
		System.out.println("\nTotal: "+ tam);
		System.out.println("\nMensaje: "+ recibido);
	}
}
