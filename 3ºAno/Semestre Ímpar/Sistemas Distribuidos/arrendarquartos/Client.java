/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.io.*;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author gui
 */
public class Client {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //variável responsável por receber os inputs da consola
    private static RemoteObject obj; //variável do objeto remoto responsável por comunicar com a BD

    /*
    função responsável por mostrar o menu para
    escolher a funcionalidade desejada
     */
    private static void showMenu() {
        try {
            System.out.println("1 - Registar um novo anúncio");
            System.out.println("2 - Mostrar anúncios (oferta ou procura)");
            System.out.println("3 - Mostrar todos os anúncios de um anunciante");
            System.out.println("4 - Obter detalhes de um anúncio (inserir aid do anúncio)");
            System.out.println("5 - Enviar mensagem para um anúncio (inserir aid do anúncio)");
            System.out.println("6 - Consultar mensagens de um anúncio (inserir aid do anúncio)");
            System.out.println("0 - Sair");

            int option = -1;
            try {
                option = Integer.parseInt(br.readLine());
                if (option < 0 || option > 6) {
                    System.out.println("Insira uma das opções apresentadas!");
                    showMenu();
                }
            } catch(NumberFormatException e) {
                System.out.println("Opção inválida!");
                showMenu();
            }

            chooseOption(option);
        }
        catch (Exception e) {
            System.err.println("Argumentos inválidos!");
            showMenu();
        }
    }

    /*
    função que recebe a opção desejada e encaminha o cliente
    para a função que execute o requisitado por este
    */
    private static void chooseOption(int option) throws IOException{
        switch (option) {
            case 0:
                System.out.println("Adeus! Até à próxima!");
                System.exit(1);
                break;
            case 1:
                registerAd();
                break;
            case 2:
                searchAds();
                break;
            case 3:
                searchByAdvertiser();
                break;
            case 4:
                getDetailsByAid();
                break;
            case 5:
                sendMessage();
                break;
            case 6:
                showMessages();
                break;
            default:
                showMenu();
        }
        showMenu();
    }

    /*
    função responsável por registar os anúncios na base de dados
     */
    private static void registerAd() throws IOException{
        Ad ad = new Ad();

        String type = "";
        do {
            System.out.print("Insira o tipo de anúncio: ");
            type = br.readLine();
            type = type.toLowerCase();
            if(type.equals("oferta") || type.equals("procura"))
                break;
        } while(true);
        ad.setType(type);

        String name = "";
        do {
            System.out.print("Insira o seu nome: ");
            name = br.readLine();
            name = name.trim();
        } while(name.length() == 0);
        ad.setAdvertiser(name);

        String local = "";
        do {
            System.out.print("Insira a localização do alojamento: ");
            local = br.readLine();
            local = local.trim();
        } while(local.length() == 0);
        ad.setLocal(local);

        int price = -1;
        do {
            System.out.print("Insira o preço do alojamento: ");
            try {
                price = Integer.parseInt(br.readLine());
                if(price > 0)
                    break;
            } catch(NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while(true);
        ad.setPrice(price);

        String gender = "";
        do {
            System.out.print("Insira o género que pretende para potenciais interessados (masculino, feminino ou indiferente): ");
            gender = br.readLine();
            gender = gender.trim();
            gender = gender.toLowerCase();
            if(gender.equals("masculino") || gender.equals("feminino") || gender.equals("indiferente"))
                break;
        } while(true);
        ad.setGender(gender);

        String typology = "";
        int n = 0;
        do {
            System.out.print("Insira a tipologia do alojamento (quarto ou T1, T2...): ");
            typology = br.readLine();
            typology = typology.trim();
            typology = typology.toLowerCase();
            if(typology.equals("quarto"))
                break;
            else if(typology.startsWith("t")){
                String aux = typology.substring(1, typology.length());
                try {
                    n = Integer.parseInt(aux);
                    if(n > 0) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato errado! TN (sendo N um número inteiro positivo");
                    continue;
                }
            }
            else
                continue;
        } while(true);
        if(typology.startsWith("t"))
            typology = "T" + String.valueOf(n);
        ad.setTypology(typology);

        ad.setDate(new Date(System.currentTimeMillis()));
        ad.setState("inativo");

        int aid = obj.sendAd(ad);
        ad.setAid(aid);

        System.out.println("Ok anúncio " + aid);
    }

    /*
    função que trata de recolher os campos desejados
    pelo cliente para pesquisa na base de dados e mostrar
    os anúncios que correspondem aos mesmos
    */
    private static void searchAds() throws java.rmi.RemoteException, IOException {
        String typeAd = "";
        String fields = "";
        do {
            System.out.print("Tipo de anúncio (o - oferta, p - procura): ");
            typeAd = br.readLine();
            typeAd = typeAd.trim();
            typeAd = typeAd.toLowerCase();
            if(typeAd.equals("o") || typeAd.equals("p") || typeAd.equals("oferta") || typeAd.equals("procura"))
                break;
        } while(true);
        if(typeAd.equals("o") || typeAd.equals("oferta"))
            fields = "typead=oferta&";
        else
            fields = "typead=procura&";
        fields += "statead=ativo";

        String chooseFilters = "";
        do {
            System.out.print("Deseja utilizar campos de procura adicionais (s - sim, n - não)? ");
            chooseFilters = br.readLine();
            chooseFilters = chooseFilters.toLowerCase();
            if(chooseFilters.equals("s") || chooseFilters.equals("n") || chooseFilters.equals("sim") || chooseFilters.equals("não"))
                break;
        } while(true);

        if(chooseFilters.equals("sim") || chooseFilters.equals("s")) {
            String aux = "";

            System.out.println("Insira a localização desejada");
            aux = br.readLine();
            if(!aux.equals(""))
                fields += "&localad=" + aux + "&";
            aux = "";
            do {
                System.out.println("Insira o género desejado");
                aux = br.readLine();
                aux = aux.trim();
                aux = aux.toLowerCase();
                if(aux.equals("masculino") || aux.equals("feminino") || aux.equals("indiferente") || aux.equals(""))
                    break;
            } while(true);

            if(!aux.equals(""))
                fields += "gender=" + aux + "&";

            aux = "";
            do {
                System.out.print("Insira o preço desejado: ");
                aux = br.readLine();
                if(aux.equals(""))
                    break;
                try {
                    int price = Integer.parseInt(aux);
                    if(price > 0)
                        break;
                } catch(NumberFormatException e) {
                    System.out.println("Insira um valor válido para o preço!");
                    continue;
                }
            } while(true);
            if(!aux.equals(""))
                fields += "price=" + aux;

        }
        List<Ad> results = obj.sendSearchFields(fields);

        if(results.size() > 0) {
            for (Ad ad : results)
                System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
        }
        else
            System.out.println("Nenhum anúncio encontrado!");
    }

    /*
    função que recolhe o nome do anunciante para pesquisa
    na base de dados e mostra os anúncios relativos a este
     */
    private static void searchByAdvertiser() throws java.rmi.RemoteException, IOException {
        String advertiser = "";
        do {
            System.out.print("Insira o nome do anunciante desejado: ");
            advertiser = br.readLine();
        } while (advertiser.equals(""));
        advertiser = "advertiser=" + advertiser;

        List<Ad> results = obj.sendSearchFields(advertiser);

        if (results.size() > 0) {
            for (Ad ad : results)
                System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
        }
        else
            System.out.println("Nenhum anúncio encontrado!");
    }

    /*
    função responsável por recolher o aid para pesquisa na base
    de dados e mostrar os anúncios relativos ao mesmo
     */
    private static void getDetailsByAid() throws IOException {
        int aux;
        do {
            System.out.print("Insira o aid do anúncio desejado: ");
            try {
                aux = Integer.parseInt(br.readLine());
                if (aux > 0)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Aid inválido!");
                continue;
            }
        } while (true);
        String aid = "aid=" + String.valueOf(aux);

        List<Ad> results = obj.sendSearchFields(aid);

        if (results.size() > 0) {
            for (Ad ad : results)
                System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
        }
        else
            System.out.println("Nenhum anúncio encontrado!");
    }

    /*
    função responsável por recolher os campos necessários para
    uma mensagem e envia a mensagem para o objeto remoto
     */
    private static void sendMessage() throws IOException {
        Message msg = new Message();
        String aux = "";

        do {
            System.out.print("Digite o aid do anúncio que pretende contactar: ");
            aux = br.readLine();
            try {
                int n = Integer.parseInt(aux);
                if(n > 0)
                    break;
            } catch(NumberFormatException e) {
                System.err.println("Formato de aid inválido! Por favor insira apenas números inteiros positivos!");
                continue;
            }
        } while(true);

        List<Ad> results = obj.sendSearchFields("aid=" + aux);

        if(results.size() > 0) {
            for(Ad ad : results)
                System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
            msg.setAid(Integer.parseInt(aux));

            aux = "";
            do {
                System.out.print("Insira o seu nome: ");
                aux = br.readLine();
            } while (aux.equals(""));
            msg.setSender(aux);

            aux = "";
            do {
                System.out.print("Escreva o conteúdo da sua mensagem: ");
                aux = br.readLine();
            } while (aux.equals(""));
            msg.setContent(aux);

            msg.setDate(new Date(System.currentTimeMillis()));

            obj.sendMessage(msg);
        }
        else
            System.out.println("O anúncio não existe!");
    }

    /*
    função responsável por recolher o aid do anúncio cujo
    se pretende ver as mensagens relativas aos mesmos
     */
    public static void showMessages() throws IOException {
        int aid;
        do {
            try {
                System.out.print("Insira o aid do anúncio: ");
                aid = Integer.parseInt(br.readLine());
                if (aid > 0)
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Formato de aid inválido! Por favor insira um número inteiro positivo");
                continue;
            }
        } while (true);
        String fields = "aid=" + String.valueOf(aid);

        List<Message> results = obj.showMessages(fields);

        if (results.size() > 0) {
            for (Message msg : results)
                System.out.println("remetente: " + msg.getSender() + " | data: " + msg.getDate() + " | aid do anúncio: " + msg.getAid() + " | conteúdo: " + msg.getContent());

        }
        else
            System.out.println("Nenhuma mensagem encontrada no anúncio " + aid + "!");
    }

    /*
    inicializa o cliente e liga-o ao servidor
    utilizando Java RMI para o efeito
     */
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
             obj = (RemoteObject) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/remoteobject");


            System.out.println("Bem vindo ao sistema de oferta e procura de alojamentos!");
            System.out.println("");

            showMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
