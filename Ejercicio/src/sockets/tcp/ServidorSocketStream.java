package sockets.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket newSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);
			serverSocket.bind(addr);
			
			System.out.print("Servidor aceptando:" + addr.getHostName());
			newSocket = serverSocket.accept();

			InputStream is = newSocket.getInputStream();
			
			byte[] buffer = new byte[25];
			int leidos = is.read(buffer);
			String recibido = new String(buffer, 0, leidos); 
			
			//System.out.println("Mensaje recibido: " + new String(mensaje));
			analizaMensaje(recibido);
			
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
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
