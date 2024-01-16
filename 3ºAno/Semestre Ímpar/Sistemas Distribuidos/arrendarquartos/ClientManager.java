/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.io.*;
import java.util.List;

/**
 *
 * @author gui
 */
public class ClientManager {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //variável responsável por receber os inputs da consola
    private static RemoteObject obj; //variável do objeto remoto responsável por comunicar com a BD

    /*
    função responsável por mostrar o menu
     */
    private static void showMenu() throws IOException{
        System.out.println("1 - Mostrar anúncios (inserir estado ativo ou inativo)");
        System.out.println("2 - Obter detalhes de um anúncio (inserir aid do anúncio)");
        System.out.println("3 - Alterar o estado de um anúncio (inserir aid do anúncio)");
        System.out.println("0 - Sair");

        int option = -1;
        try {
            option = Integer.parseInt(br.readLine());
            if(option < 0 || option > 3) {
                System.out.println("Insira uma das opções apresentadas!");
                showMenu();
            }
        } catch(NumberFormatException e) {
            System.err.println("Opção inválida!");
            showMenu();
        }
        chooseOption(option);
    }

    /*
    função que recebe a opção escolhida pelo admin e
    encaminha o admin para a função que execute
    o requisitado
     */
    private static void chooseOption(int option) throws IOException {
        switch(option) {
            case 0:
                System.out.println("Adeus admin! Até à próxima!");
                System.exit(1);
                break;
            case 1:
                showAdsByState();
                break;
            case 2:
                getDetails();
                break;
            case 3:
                changeStateOfAd();
                break;
            default:
                showMenu();
        }
        showMenu();
    }

    /*
    função que recolhe o estado do anúncio dado pelo admin para pesquisa
    na base de dados e mostra os anúncios relativos ao mesmo
     */
    public static void showAdsByState() throws IOException {
        String state = "";
        do {
            System.out.print("Insira o estado pretendido: ");
            state = br.readLine();
            state = state.trim();
            state = state.toLowerCase();
            if(state.equals("a") || state.equals("i") || state.equals("ativo") || state.equals("inativo"))
                break;
        } while(true);
        if(state.equals("a") || state.equals("ativo"))
            state = "ativo";
        else
            state = "inativo";

        state = "statead=" + state;

        List<Ad> results = obj.sendSearchFields(state);

        for(Ad ad : results)
            System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
    }

    /*
    função que recebe o aid introduzido pelo admin para pesquisa
    na base de dados e mostra os anúncios relativos ao mesmo
     */
    public static void getDetails() throws IOException {
        String aid = "";
        do {
            System.out.print("Inserir aid do anúncio desejado: ");
            aid = br.readLine();
            try {
                int aux = Integer.parseInt(aid);
                if(aux > 0)
                    break;
            } catch(NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while(true);

        List<Ad> results = obj.sendSearchFields("aid=" + aid);

        for(Ad ad : results)
            System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());
    }

    /*
    função que recebe o aid dado pelo cliente para pesquisa
    na base de dados e, se o anúncio existir, recebe do admin o estado
    que pretende colocar no anúncio
     */
    public static void changeStateOfAd() throws IOException {
        String aux = "";
        int aid;
        do {
            System.out.print("Inserir aid do anúncio desejado: ");
            aux = br.readLine();
            try {
                aid = Integer.parseInt(aux);
                if(aid > 0)
                    break;
            } catch(NumberFormatException e) {
                System.err.println("Formato inválido! Insira um número inteiro positivo!");
                continue;
            }
        } while(true);

        List<Ad> results = obj.sendSearchFields("aid=" + aid);

        for(Ad ad : results)
            System.out.println("tipo: " + ad.getType() + " | estado: " + ad.getState() + " | aid: " + ad.getAid() + " | anunciante: " + ad.getAdvertiser() + " | localização: " + ad.getLocal() + " | preço: " + ad.getPrice() + " | género: " + ad.getGender() + " | data:" + ad.getDate() + " | tipologia: " + ad.getTypology());

        String change = "";
        do {
            System.out.print("Deseja alterar o estado do anúncio " + aid + "? ");
            change = br.readLine();
            change = change.trim();
            change = change.toLowerCase();
            if(change.equals("s") || change.equals("n") || change.equals("sim") || change.equals("não"))
                break;
        } while(true);

        if(change.equals("s") || change.equals("sim")) {
            do {
                System.out.print("Insira o estado pretendido para o anúncio (ativo ou inativo): ");
                change = br.readLine();
                change = change.trim();
                change = change.toLowerCase();
                if(change.equals("a") || change.equals("i") || change.equals("ativo") || change.equals("inativo"))
                    break;
            } while(true);
            if(change.equals("a") || change.equals("ativo")) {
                change = "ativo";
                obj.changeState(change, aid);
            }
            else {
                change = "inativo";
                obj.changeState(change, aid);
            }
        }
    }

    /*
    inicializa o administrador e liga-o ao servidor via Java RMI
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

