/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

/**
 *
 * @author gui
 */
public class  Server {
    static ConnectionDB bd;

    public static void main(String[] args) {

        int regPort = 1099;

        if (args.length != 1) {
            System.out.println("Faltam argumentos");
            System.exit(1);
        }

        try {
            regPort = Integer.parseInt(args[0]);

            RemoteObject obj = new RemoteObjectImpl();
            bd = new ConnectionDB();

            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

            registry.rebind("remoteobject", obj);

            System.out.println("servidor pronto");
            bd.connectDb();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
