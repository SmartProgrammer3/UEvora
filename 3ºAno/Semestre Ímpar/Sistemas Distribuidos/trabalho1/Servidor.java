package trabalho;

import java.rmi.server.RemoteObject;

public class Servidor {
    static ConexaoBD bd;

    public static void main(String[] args) {

        int regPort = 1099;

        if (args.length != 5) {
            System.out.println("Faltam argumentos");
            System.exit(1);
        }

        try {
            regPort = Integer.parseInt(args[0]);
            String host = args[1];
            String db = args[2];
            String user = args[3];
            String passe = args[4];
            // java -cp build:resources/postgresql.jar Servidor.java 9000 localhost bd1 user1 umaPass


            RemoteObject obj = new remoteObjectImpl(host, db, user, passe);
            bd = new ConexaoBD(host, db, user, passe); 

            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);
        
            registry.rebind("remoteobject", obj);
            
            System.out.println("servidor pronto");
            bd.connectDb();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
