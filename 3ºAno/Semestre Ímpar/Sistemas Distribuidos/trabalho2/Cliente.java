import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


// java -cp build:resources/postgresql.jar Cliente.java localhost 9000
public class Cliente {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //variável responsável por receber os inputs da consola
    private static remoteObject obj;
    private static Utilizador utilizador;


    private static void opcoesRegisto() {
        try {
            System.out.println("Opção 1 - Quero registar");
            System.out.println("Opção 2 - Quero fazer login!");
            System.out.println("Opção 0 - Sair.");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(br.readLine());
                if (opcao < 0 || opcao > 2) {
                    System.out.println("Insira o número de uma das opções apresentadas!");
                    opcoesRegisto();
                } 
            } catch(NumberFormatException e) {
                System.out.println("Opção inválida!");
                opcoesRegisto();
            }
            escolherOpcaoRegisto(opcao);

        }
        catch (Exception e) {
            e.printStackTrace();
            opcoesRegisto();
        }
    }


    private static void escolherOpcaoRegisto(int opcao) throws IOException{
        boolean loginFeito = false;

        switch (opcao) {
            case 0:
                System.out.println(" Até uma próxima! ");
                System.exit(1);
                break;
            case 1:
                registarUtilizador();
                break;
            case 2:
                loginFeito = loginUtilizador();
                break;
            default:
                opcoesRegisto();
            
        }
        if(loginFeito == true){
            menuDasAcoes();
        } else{
            opcoesRegisto();
        }
    }


    private static void registarUtilizador() throws IOException{
        Utilizador utilizador = new Utilizador(null, null, null, null);
        String nomeUtilizador = "";
        String passwordUtilizador = "";
        String emailUtilizador = "";
        List<Utilizador> listaDeRegistos = null;
        String where = "";

        do {
            System.out.print("Insira o seu nome: ");
            nomeUtilizador = br.readLine();
            nomeUtilizador = nomeUtilizador.trim();
            where = "WHERE username='" + nomeUtilizador +"'";

            listaDeRegistos = obj.filtroUtilizadores(where);

            if(!listaDeRegistos.isEmpty()){
                System.out.println("O username que estás a escolher já existe. Usa outro.");
            }

        } while(!listaDeRegistos.isEmpty());
        
        utilizador.setUtilizador(nomeUtilizador);
       
        do {
            System.out.print("Insira a sua passe: ");
            passwordUtilizador = br.readLine();
            passwordUtilizador = passwordUtilizador.trim();
        } while(passwordUtilizador.length() == 0);
        
        passwordUtilizador = hashPassword(passwordUtilizador);
        utilizador.setPasse(passwordUtilizador);


        do {
            System.out.print("Insira o seu email: ");
            emailUtilizador = br.readLine();
            emailUtilizador = emailUtilizador.trim();
        } while(emailUtilizador.length() == 0);
        
        utilizador.setEmail(emailUtilizador);

        utilizador.setTipo("normal");

        boolean teste = obj.registou(utilizador);
        if(teste){
            System.out.println("Registado com sucesso!");
        } else {
            System.out.println("O nome já se encontra a ser usado. Regista-te com outro nome.");
        }
    }


    private static boolean loginUtilizador() throws IOException {
        List<Utilizador> listalogin = null;
        String where = "";
        
        do{
            System.out.print("Insira o seu nome de utilizador: ");
            String nomeUtilizador = br.readLine().trim();
        
            System.out.print("Insira a sua senha: ");
            String passwordUtilizador = br.readLine().trim();
            passwordUtilizador = hashPassword(passwordUtilizador);

            where = "WHERE username='" + nomeUtilizador +"' AND password='" + passwordUtilizador + "'";
            listalogin = obj.filtroUtilizadores(where);

            if (listalogin.isEmpty()) {
                System.out.println("Credenciais erradas!");
                return false;
            } else {
                utilizador = listalogin.get(0);
            }

        } while(listalogin.isEmpty());

        return true;

    } 
    
    // Encriptar a palavra-passe
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void menuDasAcoes() {
        try {
            System.out.println("Opção 1 - Registar um novo artista.");
            System.out.println("Opção 2 - Mostrar artistas (localização e arte).");
            System.out.println("Opção 3 - Mostrar localizações, onde existem artistas a atuar.");
            System.out.println("Opção 4 - Procurar por atuações antigas de um artista.");
            System.out.println("Opção 5 - Procurar por atuações futuras de um artista.");
            System.out.println("Opção 6 - Enviar um donativo a um artista.");
            System.out.println("Opção 7 - Listar os donativos recebidos por um determinado artista.");
            System.out.println("Opção 8 - Classificar um artista.");
            
            if (utilizador.getTipo().equals("administrador")) {
                System.out.println("Opção 9 - Promover um utilizador a administrador.");
                System.out.println("Opção 10 - Listar artistas por estado.");
                System.out.println("Opção 11 - Aprovar um artista.");
                System.out.println("Opção 12 - Consultar e alterar as informações de um artista.");
            }

            System.out.println("Opção 0 - Sair.");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(br.readLine());

                if ((opcao < 0 || opcao > 8) && (utilizador.getTipo().equals("normal"))) {
                    System.out.println("Insira uma opção válida, apresentada no menu.");
                    menuDasAcoes();
                } else if ((opcao < 0 || opcao > 12) && (utilizador.getTipo().equals("administrador"))) {
                    System.out.println("Insira uma opção válida, apresentada no menu.");
                    menuDasAcoes();
                }
            } catch(NumberFormatException e) {
                System.out.println("Opção inválida!");
                menuDasAcoes();
            }

            escolherOpcao(opcao);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Argumentos inválidoewdws!");
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
                atuacoesAntigas();
                break;
            
            case 5:
                atuacoesFuturas();
                break;

            case 6:
                donate();
                break;

            case 7:
                listarDonativos();
                break;

            case 8:
                classificarUmArtista();
                break;

            case 9:
                meterAdmin();
                break;

            case 10:
                listarArtistasPorEstado();
                break;

            case 11:
                aprovarArtista();
                break;

            case 12:
                atualizarArtista();
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
        Artistas artista = new Artistas();
        String nomeArtista = "";
        String tipoDeArte = "";
        double latitude, longitude;
        Boolean aAtuar;
        LocalDate today = LocalDate.now(); 
        Date data = Date.valueOf(today); // Para guardar a data do registo - data da atuação
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataComOFormato = dateFormat.format(data);

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
            System.out.println("Insira a latitude do artista: ");
            try {
                latitude = Double.parseDouble(br.readLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Insere uma latitude válida");
                continue;
            }
        } while (true);
        artista.setLatitude(latitude);


        do {
            System.out.println("Insira a longitude do artista: ");
            try {
                longitude = Double.parseDouble(br.readLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Insere uma longitude válida");
                continue;
            }
        } while (true);
        artista.setLongitude(longitude);


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


        String where = "WHERE " + "nomeArtista='" + artista.getNome() + "'";
        List<Artistas> auxNome = obj.filtro(where);

        if(auxNome.size() > 0){         // Se o artista já foi registado, ou seja, já tem o seu nome na base de dados.
            for (Artistas a : auxNome) {    
                if( (a.getLatitude() != artista.getLatitude()) || (a.getLongitude() != artista.getLongitude()) ){

                    where = "WHERE longitude=" + artista.getLongitude() + " AND latitude=" + artista.getLatitude() + " AND datadaatuacao='" + dataComOFormato + "'";
                    List<Atuacoes> auxAtuacao = obj.filtroAtuacao(where);

                    if (auxAtuacao.size() <= 0) { // Se não houver atuações com a mesma localização e data
                        Atuacoes atuacao = new Atuacoes();
                        atuacao.setidArtista(a.getID());
                        atuacao.setLati(artista.getLatitude());
                        atuacao.setLongi(artista.getLongitude());
                        atuacao.setDataAtuacao(data);

                        obj.registarAtuacao(atuacao);
                    }

                    String set = "latitude=" + artista.getLatitude() + ",  longitude=" + artista.getLongitude();
                    obj.mudarLocalizacao(set, a.getID());

                    System.out.println("O artista " + a.getID() + " já tinha sido registado, mas com localização diferente, a localização foi atualizada!");
                } else {
                    System.out.println("O artista " + a.getID() + " Já foi registado e tem a mesma localização.");
                }
            }
        } else{
            int artistID = obj.enviarArtista(artista);
            artista.setID(artistID);
            System.out.println("Artista registado à base de dados: " + "Artista " + artistID + ".");
            
            Atuacoes atuacao = new Atuacoes();
            atuacao.setidArtista(artista.getID());
            atuacao.setLati(artista.getLatitude());
            atuacao.setLongi(artista.getLongitude());
            atuacao.setDataAtuacao(data);
            obj.registarAtuacao(atuacao);
        }
    }




    private static void artistasFiltro() throws IOException {
        double latitude, longitude;
        String tipoDeArte = "";
        String where = "";
        
        System.out.println("Opções de filtro:");
        System.out.println("1 - Filtrar por localização");
        System.out.println("2 - Filtrar por tipo de arte");
        System.out.println("3 - Filtrar por localização e tipo de arte");
        System.out.println("0 - Voltar ao menu principal");
    
        int opcaoFiltro = Integer.parseInt(br.readLine());
    
        switch (opcaoFiltro) {
            case 1:
                do {
                    System.out.println("Insira a latitude do artista: ");
                    try {
                        latitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Insere uma latitude válida");
                        continue;
                    }
                } while (true);
    
                do {
                    System.out.println("Insira a longitude do artista: ");
                    try {
                        longitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Insere uma longitude válida");
                        continue;
                    }
                } while (true);
        
                where = "WHERE " + "latitude=" + latitude + "AND longitude=" + longitude;
                List<Artistas> artistasDessaLocalizacão = obj.filtro(where);
                mostrarArtistas(artistasDessaLocalizacão);
                break;
    
            case 2:
                do {
                    System.out.print("Insira o tipo de arte do artista: ");
                    tipoDeArte = br.readLine();
                    tipoDeArte = tipoDeArte.trim();
                } while(tipoDeArte.length() == 0);
                
                where = "WHERE " + "tipodearte='" + tipoDeArte + "'";
                List<Artistas> artistasDesseTipoDeArte = obj.filtro(where);
                mostrarArtistas(artistasDesseTipoDeArte);
                break;
    
            case 3:
                do {
                    System.out.println("Insira a latitude do artista: ");
                    try {
                        latitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Insere uma latitude válida");
                        continue;
                    }
                } while (true);

                do {
                    System.out.println("Insira a longitude do artista: ");
                    try {
                        longitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Insere uma longitude válida");
                        continue;
                    }
                } while (true);

                do {
                    System.out.print("Insira o tipo de arte do artista: ");
                    tipoDeArte = br.readLine();
                    tipoDeArte = tipoDeArte.trim();
                } while(tipoDeArte.length() == 0);

                where = "WHERE " + "latitude=" + latitude + "AND longitude=" + longitude + "AND tipodearte='" + tipoDeArte + "'";
                List<Artistas> artistasLocalEArte = obj.filtro(where);
                mostrarArtistas(artistasLocalEArte);
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


    private static void mostrarArtistas(List<Artistas> artistas) throws RemoteException {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (!artistas.isEmpty()) {
            System.out.println("Artistas encontrados com base no seu filtro pretendido:");

            for (Artistas artista : artistas) {
                double rating = obj.ratingDoArtista(artista.getID());
                String ratingDoArtista = decimalFormat.format(rating);

                System.out.println("Nome: " + artista.getNome() + " | Tipo de Arte: " + artista.getTipoDeArte() + " | Avaliação: " + ratingDoArtista);
            }
        } else {
            System.out.println("Nenhum artista encontrado com as opções desejadas!");
        }
    }
    

    private static void localizacoesArtistasAAtuar() throws IOException {
        String where = "WHERE aatuar=true";

        List<Artistas> artistasQueEstaoAAtuar = obj.filtro(where);
        List<String> localizacoesAAtuar = new ArrayList<String>();
 
        if (artistasQueEstaoAAtuar.size() > 0) {   // Se tiver artistas que estão a atuar

            for (Artistas artista : artistasQueEstaoAAtuar) { // Avaliar cada artista
                if ( (!localizacoesAAtuar.contains(artista.getLatitude() + " " + artista.getLongitude())) ) {  // Verifica se a localização desse artista está na lista das localizações onde têm artistas a atuar. 
                    localizacoesAAtuar.add(artista.getLatitude() + " " + artista.getLongitude()); // Caso não esteja, adiciona essa localização.
                }
            }
            for (String localizacao : localizacoesAAtuar) {
                System.out.println(localizacao);
            }
        } else {
            System.out.println("Não existe localizações com artistas a atuar.");
        }
    }



    private static void atuacoesAntigas() throws IOException {
        List<Integer> artistIds = obj.idsPresentesArtista();
        int artistid = -1;
        LocalDate today = LocalDate.now(); 
        Date data = Date.valueOf(today); // Para guardar a data do registo - data da atuação
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataComOFormato = dateFormat.format(data);
        String where = "";

        System.out.println("IDs presentes na tabela de artistas: (Para verificar a atuação, escolha um dos presentes.)");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }
    
        do {
            System.out.print("Insira o ID do Artista: ");
            try {
                artistid = Integer.parseInt(br.readLine());
                if (artistid >= 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido! Tens que escolher um id em cima apresentado.");
                continue;
            }
        } while (true);

        where = "WHERE " + "iddoartista='" + artistid + "'" + " AND datadaatuacao<'" + dataComOFormato + "'";

        List<Atuacoes> atuacoesRealizadas = obj.filtroAtuacao(where);

        if (atuacoesRealizadas.size() > 0) {

            for (Atuacoes atuacao : atuacoesRealizadas) {
                System.out.println("Latitude:" + atuacao.getLati() + "| Longitude:" + atuacao.getLongi() + "| Data:" + atuacao.getDataAtuacao());
            }
        } else{
            System.out.println("O artista " + artistid + " não realizou nenhuma atuação até o dia de hoje.");
        }
    }



    private static void atuacoesFuturas() throws IOException {
        List<Integer> artistIds = obj.idsPresentesArtista();
        int artistid = -1;
        LocalDate today = LocalDate.now(); 
        Date data = Date.valueOf(today); // Para guardar a data do registo - data da atuação
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataComOFormato = dateFormat.format(data);
        String where = "";

        System.out.println("IDs presentes na tabela de artistas: (Para verificar a atuação, escolha um dos presentes.)");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }
    
        do {
            System.out.print("Insira o ID do Artista: ");
            try {
                artistid = Integer.parseInt(br.readLine());
                if (artistid >= 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido! Tens que escolher um id em cima apresentado.");
                continue;
            }
        } while (true);

        where = "WHERE " + "iddoartista='" + artistid + "'" + " AND datadaatuacao>='" + dataComOFormato + "'";

        List<Atuacoes> atuacoesRealizadas = obj.filtroAtuacao(where);

        if (atuacoesRealizadas.size() > 0) {

            for (Atuacoes atuacao : atuacoesRealizadas) {
                System.out.println("Latitude:" + atuacao.getLati() + "| Longitude:" + atuacao.getLongi() + "| Data:" + atuacao.getDataAtuacao());
            }
        } else{
            System.out.println("O artista " + artistid + " não realizou nenhuma atuação até o dia de hoje.");
        }
    }




    private static void donate() throws IOException {
        Donativo donativo = new Donativo();
        String nomeUser = "";
        int artistid = -1;
        int valorDonativo;

        nomeUser = utilizador.getUtilizador();
        donativo.setUserEnvioDonativo(nomeUser);

        List<Integer> artistIds = obj.idsPresentesArtista();

        System.out.println("IDs presentes na tabela de artistas: ");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }


        do {
            System.out.print("Escolhe o artista que queres enviar o donativo (de acordo com a lista acima apresentada)");
            try {
                artistid = Integer.parseInt(br.readLine());
                if (artistid >= 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while (true);
        donativo.setidartista(artistid);


        
        do {
            System.out.print("Insere o valor que pretendes doar ao artista " + artistid + ":");
            try {
                valorDonativo = Integer.parseInt(br.readLine());
                if (valorDonativo > 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while (true);
        donativo.setValorDonativo(valorDonativo);

        LocalDate today = LocalDate.now();
        Date data = Date.valueOf(today);

        donativo.setDataDonativo(data);

        int id = obj.enviarDonativo(donativo);
        donativo.setDonativoID(id);

        System.out.println("Envio do donativo realizado com sucesso ao artista " + artistid + ". Id da doação: " + id + ", no dia: " + data);
    }



    private static void listarDonativos() throws  IOException {
        List<Integer> artistIds = obj.idsPresentesArtista();
        int artistid = -1;
        String where = "";

        System.out.println("IDs presentes na tabela de artistas: ");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }

        do {
            System.out.print("Insere o id do artista que quer ver a lista dos donativos: ");
            try {
                artistid = Integer.parseInt(br.readLine());
                if (artistid >= 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while (true);


        where = "WHERE " + "idartista='" + artistid + "'";

        List<Donativo> listaDonativos = obj.listarDonativos(where);

        if (listaDonativos.isEmpty()) {
            System.out.println("Nenhum Donativo para este Artista!");
        } else {
            for (Donativo donativo : listaDonativos) {
                int valor = donativo.getValorDonativo();
                int idArtista = donativo.getidartista();
                String utilizador = donativo.getUserEnvioDonativo();
                Date data = donativo.getDataDonativo();
        
                System.out.println("Valor - " + valor + " doado para o Artista com ID- " + idArtista + " por: " + utilizador + ", no dia: " + data);
            }
        }
    }


    private static void classificarUmArtista() throws RemoteException {
        String username = utilizador.getUtilizador();
        List<Integer> artistIds = obj.idsPresentesArtista();
        int classificacao;
        int artistid = -1;

        if(artistIds.size() > 0){
            System.out.println("IDs presentes na tabela de artistas: ");
            for (Integer artistId : artistIds) {
                System.out.println(artistId);
            }
        } else{
            System.out.println("Não podes classificar nenhum artista, porque não existe artistas.");
        }


        do {
            System.out.print("Escolhe o id do artista que queres dar rating.");
            try {
                artistid = Integer.parseInt(br.readLine());
                if (artistid >= 0)
                break;
            } catch (NumberFormatException | IOException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while (true);


        do {
            System.out.print("Insere a tua avaliação. Desde 0 (Muito mau) a 10 (Muito bom) ");
            try {
                classificacao = Integer.parseInt(br.readLine());
                if (classificacao >= 0 && classificacao <= 10)
                    break;
            } catch (NumberFormatException | IOException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while (true);


        Classificacao avaliacao = new Classificacao(artistid, username, classificacao);

        int id = obj.enviarClassificacao(avaliacao);

        System.out.println("Classificação enviada, ao artista" + artistid + ", com sucesso." );
    }



    // Admin


    private static void meterAdmin() throws IOException {
        String username = null;

        do {
            System.out.println("Escreve o username do utilizador que queres que seja promovido: ");
            username = br.readLine().trim();
        } while (username == null);

        String where = "WHERE tipodeutilizador='normal' AND username='" + username +"'";

        System.out.println(where);
        List<Utilizador> utilizador = obj.filtroUtilizadores(where);

        if (utilizador.size() > 0) {
            obj.darAdmin(username);
            System.out.println("Utilizador: " + username + " , Agora é administrador ");

        } else {
            System.out.println("Esse utilizador não existe!");
        }
    }




    private static void listarArtistasPorEstado() throws IOException {
        String where = "";
        System.out.println("Opções de filtro:");
        System.out.println("1 - Filtrar pelos artistas com estado - Aprovado");
        System.out.println("2 - Filtrar pelos artistas com estado - Não aprovado");
        System.out.println("0 - Voltar ao menu principal");
        int opcaoFiltro = Integer.parseInt(br.readLine());

    
        switch (opcaoFiltro) {
            case 1:
                where = "WHERE estado='Aprovado'";

                List<Artistas> artistasAprovados = obj.filtro(where);

                if (artistasAprovados.size() > 0) {
                    for (Artistas artista : artistasAprovados)
                        System.out.println("Id do artista: " + artista.getID() + "| Nome do artista: " + artista.getNome());
                } else{
                    System.out.println("Nenhum artista está aprovado!");
                }
                break;

            case 2:
                where = "WHERE estado='Não aprovado'";

                List<Artistas> artistasNaAprovados = obj.filtro(where);

                if (artistasNaAprovados.size() > 0) {
                    for (Artistas artista : artistasNaAprovados)
                        System.out.println("Id do artista: " + artista.getID() + "| Nome do artista: " + artista.getNome());
                } else{
                    System.out.println("Nenhum artista está não aprovado!");
                }
                break;

            case 0:
                menuDasAcoes();
                break;
    
            default:
                System.out.println("Opção inválida. Por favor, escolhe uma opção válida.");
                listarArtistasPorEstado();
                break;
        }

    }




    private static void aprovarArtista() throws java.rmi.RemoteException, IOException {
        String where = "";
        int idAAprovar;
        
        where = "WHERE estado='Não aprovado'";
        List<Artistas> artistasNaAprovados = obj.filtro(where);

        if (artistasNaAprovados.size() > 0) {
            for (Artistas artista : artistasNaAprovados)
                System.out.println("Id do artista: " + artista.getID() + "| Nome do artista: " + artista.getNome());
        } else{
            System.out.println("Nenhum artista está não aprovado!");
        }

        do {
            System.out.println("Insira o ID do artista a aprovar: ");
            try {
                idAAprovar = Integer.parseInt(br.readLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Insira um ID valido(Númerico)");
                continue;
            }
        } while (true);

        String set = "estado='Aprovado'";

        obj.alterarInformacoesArtista(set, idAAprovar);
    }



    

    private static void atualizarArtista() throws IOException {
        int idConsulta;
        List<Integer> artistIds = obj.idsPresentesArtista();
        String where;

        System.out.println("IDs presentes na tabela de artistas: ");
        for (Integer artistId : artistIds) {
            System.out.println(artistId);
        }

        do {
            System.out.println("Com base nos ids existentes, escolhe o que tu queres consultar.");
            try {
                idConsulta = Integer.parseInt(br.readLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Insere um ID valido (um id lá em cima).");
                continue;
            }
        } while (true);


        where = "WHERE artistid='" + idConsulta + "'";
        List<Artistas> artista = obj.filtro(where);

        if (artista.size() > 0) {
            for (Artistas a : artista) {
                System.out.println("Id do artista: " + a.getID());
                System.out.println("Nome do artista: " + a.getNome());
                System.out.println("Tipo de Arte do artista: " + a.getTipoDeArte());
                System.out.println("Localização do artista: (" + a.getLatitude() + ", " + a.getLongitude() + ")");
                System.out.println("Está a atuar?  " + a.getAAtuar());

                menuDasAlteracoes(a.getID());
            }
        } else {
            System.out.println("Não deu.");
        }
    }



    private static void menuDasAlteracoes(int artistID) throws IOException {
        int opcao = -1;
        System.out.println("Opção 1 - Mudar o nome do artista");
        System.out.println("Opção 2 - Mudar o tipo de arte do artista");
        System.out.println("Opção 3 - Mudar a localização do artista");
        System.out.println("Opção 4 - Mudar se o artista está a atuar ou não (true/false)");
        System.out.println("Opção 0 - Voltar ao menu principal");
        String set = "";

        try {
            opcao = Integer.parseInt(br.readLine());

            if (opcao < 0 || opcao > 4) {
                System.out.println("Insira uma opção apresentada");
                menuDasAlteracoes(artistID);
            }
        } catch (NumberFormatException e) {
            System.err.println("Opção inválida!");
            menuDasAlteracoes(artistID);
        }
         
        switch (opcao) {
            case 0:
                menuDasAcoes();
                break;
            
            case 1:
                String novoNome = "";
                do {
                    System.out.print("Insere o novo nome do artista: ");
                    novoNome = br.readLine();
                    novoNome = novoNome.trim();
                } while (novoNome.length() == 0);

                set = "nomeartista='" + novoNome + "'";
                obj.alterarInformacoesArtista(set, artistID);
                break;

            case 2:
                String novoTipoDeArte = "";
                do {
                    System.out.print("Insere o novo tipo de arte do artista: ");
                    novoTipoDeArte = br.readLine();
                    novoTipoDeArte = novoTipoDeArte.toLowerCase();
                    novoTipoDeArte = novoTipoDeArte.trim();
                } while (novoTipoDeArte.length() == 0);

                set = "tipodearte='" + novoTipoDeArte + "'";
                obj.alterarInformacoesArtista(set, artistID);
                break;

            case 3:
                double novaLatitude;
                double novaLongitude;

                do {
                    System.out.println("Insere a nova latitude da localização do artista: ");
                    try {
                        novaLatitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) { // Até ser valido
                        continue;
                    }
                } while (true);

                do {
                    System.out.println("Insere a nova longitude da localização do artista: ");
                    try {
                        novaLongitude = Double.parseDouble(br.readLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } while (true);

                set = "latitude = " + novaLatitude + ", longitude = " + novaLongitude;
                obj.alterarInformacoesArtista(set, artistID);
                break;


            case 4:
                boolean novoAAtuar;

                do {
                    System.out.print("O artista está a atuar neste momento? (true/false): ");
                    try {
                        novoAAtuar = Boolean.parseBoolean(br.readLine());
                        break; 
                    } catch (Exception e) {
                        System.out.println("Insere um valor válido (true/false).");
                    }
                } while (true);

                set = "aatuar='" + novoAAtuar + "'";
                obj.alterarInformacoesArtista(set, artistID);
                break;
            default:
                menuDasAlteracoes(artistID);
        }
        menuDasAlteracoes(artistID);
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
            opcoesRegisto();

            System.out.println("Bem-vindo ao sistema para gerir as performances dos artistas de rua do Alentejo.");
            System.out.println("");
            
            menuDasAcoes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
