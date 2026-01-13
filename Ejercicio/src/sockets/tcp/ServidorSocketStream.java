package sockets.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);
			serverSocket.bind(addr);
			
			System.out.print("Servidor aceptando:" + addr.getHostName());
			Socket newSocket = serverSocket.accept();

			InputStream is = newSocket.getInputStream();
			
			byte[] buffer = new byte[25];
			is.read(buffer);
			
			//System.out.println("Mensaje recibido: " + new String(mensaje));
			analizaMensaje(buffer);
			
			newSocket.close();
			serverSocket.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void analizaMensaje(byte[] mensaje) {
		String msn = (new String(mensaje)).trim();
		int tam = msn.length();
		System.out.println("\nTotal: "+ tam);
		System.out.println("\nMensaje: "+ msn);
	}
}
