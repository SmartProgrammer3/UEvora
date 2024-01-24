package trabalho;

import java.io.*;
import java.util.List;

// java -cp build:resources/postgresql.jar Cliente.java localhost 9000
public class Cliente {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //variável responsável por receber os inputs da consola
    private static remoteObject obj;
    
    private static void menuDasAcoes() {
        try {
            System.out.println("Opção 1 - Registar um novo artista.");
            System.out.println("Opção 2 - Mostrar artistas (localização e arte).");
            System.out.println("Opção 3 - Mostrar localizações, onde existem artistas a atuar.");
            System.out.println("Opção 4 - Enviar um donativo a um artista");
            System.out.println("Opção 5 - Listar os donativos recebidos por um determinado artista");
            System.out.println("Opção 0 - Sair.");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(br.readLine());
                if (opcao < 0 || opcao > 5) {
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
                registarArtista();
                break;
            
            case 2:
                artistasFiltro();
                break;       
    
            case 3:
                localizacoesArtistasAAtuar();
                break;

            case 4:
                donate();
                break;

            case 5:
                listarDonativos();
                break;

            default:
                menuDasAcoes();
            
        }
        menuDasAcoes();
    }


    /*
    * Este método tem o propósito de registrar um novo artista.
    * Solicita informações ao cliente para meter os dados de registo do artista.
    */
    private static void registarArtista() throws IOException{
        Artistas artista = new Artistas(null, null, null, false, null);
        String nomeArtista = "";
        String tipoDeArte = "";
        String localizacao = "";
        Boolean aAtuar;
        
        do {
            System.out.print("Insira o nome do artista: ");
            nomeArtista = br.readLine();
            nomeArtista = nomeArtista.trim();
        } while(nomeArtista.length() == 0);
        
        artista.setNome(nomeArtista);
       
        do {
            System.out.print("Insira o tipo de arte do artista: ");
            tipoDeArte = br.readLine();
            tipoDeArte = tipoDeArte.trim();
        } while(tipoDeArte.length() == 0);
        
        artista.setTipoDeArte(tipoDeArte);


        do {
            System.out.print("Insira a localizacao do artista: ");
            localizacao = br.readLine();
            localizacao = localizacao.trim();
        } while(localizacao.length() == 0);
        
        artista.setLocalizacao(localizacao);


        do {
            System.out.print("O artista está a atuar neste momento? (true/false): ");
            try {
                aAtuar = Boolean.parseBoolean(br.readLine());
                break; 
            } catch (Exception e) {
                System.out.println("Insere um valor válido (true/false).");
            }
        } while (true);

        System.out.println(aAtuar);
        
        artista.setAAtuar(aAtuar);

        artista.setEstado("Não aprovado");
        
        try {
            int artistID = obj.enviarArtista(artista);
            artista.setID(artistID);

            System.out.println("Artista registado à base de dados: " + "Artista " + artistID + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    /*
    * Este método em Java tem o propósito de permitir ao cliente filtrar os artistas com base em
    * opções como localização, tipo de arte ou ambos. 
    */
    private static void artistasFiltro() throws IOException {
        System.out.println("Opções de filtro:");
        System.out.println("1 - Filtrar por localização");
        System.out.println("2 - Filtrar por tipo de arte");
        System.out.println("3 - Filtrar por localização e tipo de arte");
        System.out.println("0 - Voltar ao menu principal");
    
        int opcaoFiltro = Integer.parseInt(br.readLine());
    
        switch (opcaoFiltro) {
            case 1:
                System.out.print("Insere a localização para filtrar: ");
                String localizacaoFiltro = br.readLine().trim();

                List<Artistas> artistasPorLocalizacao = obj.filtroLocalizacaoEArte("localizacao=" + localizacaoFiltro);
                mostrarArtistas(artistasPorLocalizacao);
                break;
    
            case 2:
                System.out.print("Insere o tipo de arte para filtrar: ");
                String tipoArteFiltro = br.readLine().trim();

                List<Artistas> artistasPorTipoArte = obj.filtroLocalizacaoEArte("tipoDeArte=" + tipoArteFiltro);
                mostrarArtistas(artistasPorTipoArte);
                break;
    
            case 3:
                System.out.print("Insere a localização para filtrar: ");
                String localizacaoFiltroCombinado = br.readLine().trim();

                System.out.print("Insere o tipo de arte para filtrar: ");
                String tipoArteFiltroCombinado = br.readLine().trim();

                List<Artistas> artistasPorLocalizacaoETipoArte = obj.filtroLocalizacaoEArte("localizacao=" + localizacaoFiltroCombinado + "&tipoDeArte=" + tipoArteFiltroCombinado);
                mostrarArtistas(artistasPorLocalizacaoETipoArte);
                break;
    
            case 0:
                menuDasAcoes();
                break;
    
            default:
                System.out.println("Opção inválida. Por favor, escolhe uma opção válida.");
                artistasFiltro();
                break;
        }
    }
    


    /*
    * Este método tem o propósito de dar print no terminal do cliente dos artistas encontrados de acordo com o filtro.
    */
    private static void mostrarArtistas(List<Artistas> artistas) {
        if (!artistas.isEmpty()) {
            System.out.println("Artistas encontrados com base no seu filtro pretendido:");
            for (Artistas artista : artistas) {
                System.out.println("Nome: " + artista.getNome() + " | Tipo de Arte: " + artista.getTipoDeArte() + " | Localização: " + artista.getLocalizacao());
            }
        } else {
            System.out.println("Nenhum artista encontrado com as opções desejadas!");
        }
    }
    


    /*
    * Este método tem o propósito de mostrar as localizações onde existem artistas a atuar.
    */
    private static void localizacoesArtistasAAtuar() throws IOException {
        List<String> localizacoesAAtuar = obj.artistasAAtuar();

        if (!localizacoesAAtuar.isEmpty()) {
            System.out.println("Localizações onde existem artistas a atuar:");
            for (String localizacao : localizacoesAAtuar) {
                System.out.println(localizacao);
            }
        }
    }

    /*
    * Este método permite ao cliente escolher um artista, com base no seu id que esteja presente na tabela dos artistas e deste modo
    * é permitido o envio de donativos, do valor que o cliente pretenda enviar.
    */
    private static void donate() throws IOException {
        List<Integer> artistIds = obj.idsPresentesArtista();

        System.out.println("IDs presentes na tabela de artistas:");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }

        System.out.print("Diz o respetivo id do artista que pretendes doar: ");
        int artistID = Integer.parseInt(br.readLine());

        System.out.print("Diz o donativo que queres dar ao artista: ");
        int valorDonativo = Integer.parseInt(br.readLine());

        Donativo donativo = new Donativo(artistID, valorDonativo);

        obj.enviarDonativo(donativo);
        System.out.println("Donativo enviado! Agradecido.");
    }



    /*
    * Este método permite ao utilizador listar todos os donativos recebidos por um artista, de acordo com o seu artistid presente na tabela artistas
    * e de escolha do cliente.
    */
    private static void listarDonativos() throws IOException {
        // Mostra os artistas presentes na tabela dos artistas
        List<Integer> artistIds = obj.idsPresentesArtista();

        System.out.println("IDs presentes na tabela de artistas:");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }

        // O cliente escolhe o id
        System.out.print("Diz o respetivo ID do artista para listar os donativos recebidos: ");
        int artistID = Integer.parseInt(br.readLine());

        List<Integer> donativos = obj.retornarListaDonativosArtista(artistID);

        System.out.println("Donativos recebidos do artista" + artistID + " :");

        for (int don : donativos) {
            System.out.println(don);
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
