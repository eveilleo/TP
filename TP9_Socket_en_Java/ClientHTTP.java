// ClientHTTP.java

import java.io.*;
import java.net.*;

public class ClientHTTP
{
	public static void main( String[] args )
	{
		try 
		{
			String host = args[0];
			Socket socket = new Socket( host, 80 );
			OutputStreamWriter osw = new OutputStreamWriter( socket.getOutputStream() );
			InputStreamReader isw = new InputStreamReader( socket.getInputStream() );

			BufferedWriter bufOut = new BufferedWriter( osw );
			BufferedReader bufIn = new BufferedReader( isw );

			String request = "GET / HTTP/1.0\r\n\r\n"; // requete HTTP
			bufOut.write( request, 0, request.length() );
			bufOut.flush();

			String line = bufIn.readLine(); 	// lecture ligne par ligne
			while( line != null ) 
			{					// tant qu'il y a des donnees recues
				System.out.println( line );	// ... les afficher
				line = bufIn.readLine();
			}
			bufIn.close();
			bufOut.close();
			socket.close();
		}
		catch( Exception ex )
		{
			System.out.println( "erreur !" );
			ex.printStackTrace();
		}

	}
}	