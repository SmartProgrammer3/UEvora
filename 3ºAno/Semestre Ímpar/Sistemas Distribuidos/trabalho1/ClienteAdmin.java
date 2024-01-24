package trabalho;

import java.io.*;
import java.util.List;

// java -cp build:resources/postgresql.jar ClienteAdmin.java localhost 9000

public class ClienteAdmin {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //variável responsável por receber os inputs da consola
    private static remoteObject obj;


    private static void menuDasAcoes() {
        try {
            System.out.println("Opção 1 - Listar artistas por estado");
            System.out.println("Opção 2 - Aprovar um artista que ainda não tenha sido aprovado");
            System.out.println("Opção 0 - Sair.");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(br.readLine());
                if (opcao < 0 || opcao > 2) {
                    System.out.println("Insira o número de uma das opções apresentadas!");
                    menuDasAcoes();
                }
            } catch(NumberFormatException e) {
                System.out.println("Opção inválida!");
                menuDasAcoes();
            }

            escolherOpcao(opcao);
        }
        catch (Exception e) {
            System.err.println("Argumentos inválidos!");
            menuDasAcoes();
        }
    }

    private static void escolherOpcao(int opcao) throws IOException{
        switch (opcao) {
            case 0:
                System.out.println(" Até uma próxima! ");
                System.exit(1);
                break;
            case 1:
                listarArtistasPorEstado();
                break;
            case 2:
                aprovarArtista();
                break;       
            default:
                menuDasAcoes();
            
        }
        menuDasAcoes();
    }



    private static void listarArtistasPorEstado() throws IOException {
        System.out.println("Digita o estado para listar os artistas (aprovado ou não aprovado): ");
        String estado = br.readLine();
        
        List<Artistas> artistasPorEstado = obj.listarArtistasPorEstado(estado);
        
        System.out.println("Artistas no estado " + estado + ":");

        for (Artistas artista : artistasPorEstado) {
            System.out.println("Nome: " + artista.getNome() + ", Tipo de Arte: " + artista.getTipoDeArte()
                    + ", Localização: " + artista.getLocalizacao() + ", A Atuar: " + artista.getAAtuar()
                    + ", Estado: " + artista.getEstado());
        }
    }
    


    private static void aprovarArtista() throws IOException {
        try {
            System.out.println("Digite o ID do artista a aprovar: ");
            int id = Integer.parseInt(br.readLine());
            
            if (obj.aprovacao(id)) {
                System.out.println("Artista aprovado com sucesso!");
            } else {
                System.out.println("Falha ao aprovar o artista.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de artista inválido!");
        }
    }
    




















    public static void main(String[] args) {
        
        String regHost = "localhost";
        String regPort = "9000";


        if(args.length != 2) {
            System.out.println("Faltam argumentos!");
            System.exit(1);
        } 

        regHost = args[0];
        regPort = args[1];
        
        try {
            obj = (remoteObject) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/remoteobject");

            System.out.println("Bem-vindo ao sistema para gerir as performances dos artistas de rua do Alentejo.");
            System.out.println("");
            
            menuDasAcoes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
