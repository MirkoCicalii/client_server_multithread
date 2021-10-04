package it.cicali;
import java.io.*;
import java.net.*;


public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try
        {
            tastiera= new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket (nomeServer, portaServer);

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }
        catch (UnknownHostException a){
            System.err.println("Host sconosciuto");
        }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
            return miosocket;
     }

     public void comunica(){
         try{
             System.out.println("4... inserisci la stringa da trasfmettere al server:"+"\n");
             stringaUtente = tastiera.readLine();
             System.out.println ("5... inserisci la stringa al server e attendo ...");
             outVersoServer.writeBytes( stringaUtente+'\n');
             stringaRicevutaDalServer=inDalServer.readLine();
             System.out.println("8 ... risposta del server" + '\n'+stringaRicevutaDalServer);
             System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
             miosocket.close()
         }
         catch (Exception e)
         {
             System.out.println(e.getMessage());
             System.out.println("Errore durante la comunicazione col server!");
             System.exit(1);
         }
     }

     public static void main (String args[]){
         ClientStr cliente = new ClientStr();
         cliente.connetti();
         cliente.comunica();
     }


    }
