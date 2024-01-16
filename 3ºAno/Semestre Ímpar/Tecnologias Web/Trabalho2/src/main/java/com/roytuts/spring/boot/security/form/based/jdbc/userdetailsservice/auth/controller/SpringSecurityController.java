package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// De notar que algumas funções foram usadas com a ajuda das definições: https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.5/api/javax/servlet/http/HttpServletRequest.html
@Controller
public class SpringSecurityController {

    @GetMapping("/")
    public String defaultPage(Model model, HttpServletRequest request) throws Exception {
        if (request.getUserPrincipal() == null) { // Retorna um objeto java.security.Principal que contém o nome do utilizador atualmente autenticado.
            return "index";
        } else {
            String username = request.getRemoteUser();      //  Retorna o username do utilizador que efetuou este pedido, pedido esse que é o login.
            String role = new Registar(username).getRole();
            
            model.addAttribute("session", username + ", <a href=\"/logout\">Logout</a>");
    
            if (role.equals("ROLE_STAFF")) {
                model.addAttribute("operations", 
                    "<a href=\"/registarUmEvento\"> Registar um evento novo </a> <br>" 
                    + "<a href=\"/registarTempoParticipante\"> Registar o tempo de um participante </a> <br>" 
    
                    + "<a href=\"/eventosPassados?page=1\"> Eventos Passados </a> <br>"
                    + "<a href=\"/eventosADecorrer?page=1\"> Eventos a Decorrer </a> <br>"
                    + "<a href=\"/eventosFuturos?page=1\"> Eventos Futuros </a> <br>"
                    + "<a href=\"/eventosPesquisar\"> Procurar Eventos </a> <br>"
                );
            } else {
                model.addAttribute("operations", 
                    "<a href=\"/inscricao\"> Entra num evento! </a> <br>"
                    + "<a href=\"/inscricoesRealizadas\"> As minhas inscrições </a> <br>" 
    
                    + "<a href=\"/eventosPassados?page=1\"> Eventos Passados </a> <br>"
                    + "<a href=\"/eventosADecorrer?page=1\"> Eventos a Decorrer </a> <br>"
                    + "<a href=\"/eventosFuturos?page=1\"> Eventos Futuros </a> <br>"
                    + "<a href=\"/eventosPesquisar\"> Procurar Eventos </a> <br>"
                );
            }
            return "/home";
        }
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,     
            @RequestParam String genero,
            @RequestParam String escalao,
            Model model) throws Exception{

        Registar aux = new Registar(username, password, email, genero, escalao);

        int res = aux.RegistarNome();

        // Se já tiver um user com o mesmo nome
        if (res == -1) {
            model.addAttribute("error", "O username já está a ser usado");
            return "newuser";
        } 

        return "registoconfirmado";
    }


    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Credenciais inválidas. Tenta novamente.");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }


    @GetMapping("/newuser")
    public String newuser(Model model) {
        return "newuser";
    }

    @GetMapping("/sobreNos")
    public String sobreNos(Model model) {
        return "sobreNos";
    }


    @GetMapping("/registarUmEvento")
    public String registarUmEvento(Model model) {
        return "registarUmEvento";
    }

    @GetMapping("/regevento")
    public String registerEvent(@RequestParam String nomeEvento, 
            @RequestParam String dataEvento,
            @RequestParam String descricaoEvento,
            @RequestParam String valorInscricao,
            Model model) throws Exception {

            Registar aux = new Registar(nomeEvento, dataEvento, descricaoEvento, valorInscricao);
            int res = aux.RegistarEvento();

            if(res == -1){
                model.addAttribute("error", "O nome do evento já está a ser usado");
                return "registarUmEvento";
            }

        return "eventoconfirmado";
    }
    
    @GetMapping("/inscricao")
    public String inscricao(Model model, HttpServletRequest request) throws Exception {
        String username = request.getRemoteUser();
        Registar aux = new Registar(username);
    
        StringBuilder builder = new StringBuilder();
    
        JSONArray events = aux.getEventosDisponiveis();
    
        // O valor de inscrição de um evento tem que ser um numérico com separador decimal, logo no mínimo e máximo 2 casas decimais
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
    
        if (events.length() == 0) {
            builder.append("<p> Não há eventos para participares</p>");
        } else {
            builder.append("<table class=\"listaEventos\">");
    
            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);
    
                builder.append("<tr class=\"Evento\">");
                
                builder.append("<td class=\"NomeEvento\">").append(event.getString("NomeDoEvento")).append("</td>");
                builder.append("<td class=\"PrecoEvento\">").append(df.format(Float.parseFloat(event.getString("PrecoDoEvento")))).append("€</td>");
                builder.append("<td class=\"DataEvento\">").append(event.getString("DataDoEvento")).append("</td>");
    
                builder.append("<td><form class=\"join-button\" action=\"/confirmarInscricao\" method=\"GET\">")
                       .append("<input type=\"hidden\" name=\"nomeEvento\" value=\"")
                       .append(event.getString("NomeDoEvento"))
                       .append("\"><button class=\"join\"> Quero entrar! </button></form></td>");
    
                builder.append("</tr>");
            }
    
            builder.append("</table>");
        }
    
        model.addAttribute("events", builder.toString());
    
        return "inscreverEvento";
    }
    
    @GetMapping("/confirmarInscricao")
    public String confirmarInscricao(@RequestParam String nomeEvento,
            Model model, HttpServletRequest request) throws Exception {
        
        String username = request.getRemoteUser();
    
        Registar aux = new Registar(username, nomeEvento);
        JSONObject referenciaGerada = aux.fazerInscricao();
        
        if (referenciaGerada != null) {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            
           
            model.addAttribute("pagamento", "<p class=\"payment-param\">Entidade: " + referenciaGerada.getString("Entidade") + "</p>"
                    + "<p class=\"payment-param\">Referencia: " + referenciaGerada.getString("ref") + "</p>"
                    + "<p class=\"payment-param\">Valor: " + df.format(Float.parseFloat(referenciaGerada.getString("quantia"))) + "&euro;</p>");
        } 


    
        return "confirmarInsc";
    }



    @GetMapping("/inscricoesRealizadas")
    public String inscricoesRealizadas(Model model, HttpServletRequest request) throws Exception {
        String username = request.getRemoteUser();
        Registar aux = new Registar(username);
    
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        StringBuilder builder = new StringBuilder();
    
        JSONArray eventosNaoPagos = aux.getEventosNaoPagos(username);
        JSONArray eventosPagos = aux.getEventosPagos(username);
    
        model.addAttribute("username", username);
    
        if (eventosNaoPagos.length() == 0 && eventosPagos.length() == 0) {
            builder.append("<p> Não estás inscrito em nenhum evento </p>");
        } else {
            builder.append("<table class=\"listaDeEventos\">");
            builder.append("<tr><th>Nome do Evento</th><th>Preço</th><th>Data do evento</th><th>Pagamento</th></tr>");
    
            for (int i = 0; i < eventosNaoPagos.length(); i++) {
                JSONObject evento = eventosNaoPagos.getJSONObject(i);
                builder.append("<tr class=\"event\">")
                .append("<td>").append(evento.getString("NomeDoEvento")).append("</td>")
                .append("<td>").append(df.format(Float.parseFloat(evento.getString("PrecoDoEvento")))).append("&euro;</td>")
                .append("<td>").append(evento.getString("DataDoEvento")).append("</td>")
                .append("<td><form class=\"payment-button\" action=\"/pagamento\" method=\"GET\">")
                .append("<input type=\"hidden\" name=\"evento\" value=\"").append(evento.getString("NomeDoEvento")).append("\">")
                .append("<button class=\"payment-status-red\">Pagar</button></form></td>")
                .append("</tr>");
            }
        
            for (int i = 0; i < eventosPagos.length(); i++) {
                JSONObject evento = eventosPagos.getJSONObject(i);
                builder.append("<tr class=\"event\">")
                        .append("<td>").append(evento.getString("NomeDoEvento")).append("</td>")
                        .append("<td>").append(df.format(Float.parseFloat(evento.getString("PrecoDoEvento")))).append("&euro;</td>")
                        .append("<td>").append(evento.getString("DataDoEvento")).append("</td>")
                        .append("<td class=\"payment-status-green\">Pago</td>")
                        .append("</tr>");
            }
            builder.append("</table>");
        }
        
        model.addAttribute("listaEventos", builder.toString());
        
        return "asMinhasInscricoes";
    }
 
    

    @GetMapping("/pagamento")
    public String pagamento(@RequestParam String evento, HttpServletRequest request, Model model) throws Exception {
        String username = request.getRemoteUser();
        Registar aux = new Registar(username, evento);

        JSONObject refenciaMB = aux.getDetalhesDoPagamento();

        String detPagamento = "<div class=\"payment-details\"><div class=\"full-width\">"
                        + "<div class=\"payment-content\">"
                        + "<p class=\"payment-param\">Entidade: " + refenciaMB.getString("Entidade") + "</p>"
                        + "<p class=\"payment-param\">Referência: " + refenciaMB.getString("Referencia") + "</p>"
                        + "<p class=\"payment-param\">Valor: " + refenciaMB.getString("Valor") + "&euro;</p></div></div>";

        model.addAttribute("pagamento", detPagamento);
        model.addAttribute("evento", evento);

        return "paginaPagamento";
    }


    @GetMapping("/confirmarPagamento")
    public String confirmarPagamento(@RequestParam String evento, HttpServletRequest request, Model model) throws Exception {
        String username = request.getRemoteUser();
        Registar aux = new Registar(username, evento);

        aux.pagar();

        return "pagamentoConfirmado";
    }



    @GetMapping("/eventosPassados")
    public String eventosPassados(@RequestParam String page, Model model) throws Exception {
        int numeroDaPagina = Integer.parseInt(page); // Deve existir pelo menos uma página
        Registar aux = new Registar();
        StringBuilder builder = new StringBuilder();
    
        // Obtém os eventos cuja data é anterior à data de hoje
        JSONArray eventosFeitos = aux.getEventos("passados");
    
        int numeroDePaginas = (int) Math.ceil((double) eventosFeitos.length() / 4);
    
        if (eventosFeitos.length() == 0) {
            if (numeroDaPagina < 1) {
                throw new Exception();
            }
            builder.append("<h4>Não há eventos realizados no passado.</h4>");
            model.addAttribute("eventosPassados", builder.toString());
        } else {
            if (numeroDaPagina < 1 || numeroDaPagina > numeroDePaginas) {
                throw new Exception();
            }
    
            // Início da tabela
            builder.append("<table class=\"events-table\">");
            builder.append("<tr><th>Nome do Evento</th><th>Data do Evento</th><th>Detalhes do Evento</th></tr>");
    
            int indiceInicio = (numeroDaPagina - 1) * 4;
            int indiceFim = Math.min(indiceInicio + 4, eventosFeitos.length());
    
            for (int i = indiceInicio; i < indiceFim; i++) {
                JSONObject evento = eventosFeitos.getJSONObject(i);
    
                builder.append("<tr>");
                builder.append("<td>").append(evento.getString("nomeDoEvento")).append("</td>");
                builder.append("<td>").append(evento.getString("dataDoEvento")).append("</td>");
                builder.append("<td><form action=\"/informacaoEvento\" method=\"GET\"><input type=\"hidden\" name=\"evento\" value=\"").append(evento.getString("nomeDoEvento")).append("\"><button type=\"submit\">Detalhes</button></form></td>");
                builder.append("</tr>");
            }
    
            builder.append("</table>");
    
            model.addAttribute("eventosPassados", builder.toString());
    
            builder = new StringBuilder();
    
            // Lógica de Paginação
            if (numeroDePaginas > 1) {
                builder.append("<div class=\"pagination\">");
                if (numeroDaPagina > 1) {
                    builder.append("<form action=\"/eventosPassados\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina - 1)).append("\"><button style=\"margin-right:10px\" type=\"submit\">Anterior</button></form>");
                }
                if (numeroDaPagina < numeroDePaginas) {
                    builder.append("<form action=\"/eventosPassados\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina + 1)).append("\"><button style=\"margin-left:10px\" type=\"submit\">Seguinte</button></form>");
                }
                builder.append("</div>");
            }
    
            model.addAttribute("navegacao", builder.toString());
        }
    
        return "eventosJaOcorridos";
    }
    
    



    @GetMapping("/eventosFuturos")
    public String eventosFuturos(@RequestParam String page, Model model) throws Exception {
        int numeroDaPagina = Integer.parseInt(page); // Deve existir pelo menos uma página
        Registar aux = new Registar();
        StringBuilder builder = new StringBuilder();
    
        // Obtém os eventos cuja data é superior à data de hoje
        JSONArray eventosAFazer = aux.getEventos("futuros");
    
        int numeroDePaginas = (int) Math.ceil((double) eventosAFazer.length() / 4);
    
        if (eventosAFazer.length() == 0) {
            if (numeroDaPagina < 1) {
                throw new Exception();
            }
    
            builder.append("<h4>Não há eventos por realizar.</h4>");
            model.addAttribute("eventosFuturos", builder.toString());
        } else {
            if (numeroDaPagina < 1 || numeroDaPagina > numeroDePaginas) {
                throw new Exception();
            }
    
            // Início da tabela
            builder.append("<table class=\"events-table\">");
            builder.append("<tr><th>Nome do Evento</th><th>Data do Evento</th><th>Detalhes do Evento</th></tr>");
    
            int indiceInicio = (numeroDaPagina - 1) * 4;
            int indiceFim = Math.min(indiceInicio + 4, eventosAFazer.length());
    
            for (int i = indiceInicio; i < indiceFim; i++) {
                JSONObject evento = eventosAFazer.getJSONObject(i);
    
                builder.append("<tr>");
                builder.append("<td>").append(evento.getString("nomeDoEvento")).append("</td>");
                builder.append("<td>").append(evento.getString("dataDoEvento")).append("</td>");
                builder.append("<td><form action=\"/informacaoEvento\" method=\"GET\"><input type=\"hidden\" name=\"evento\" value=\"").append(evento.getString("nomeDoEvento")).append("\"><button type=\"submit\">Detalhes</button></form></td>");
                builder.append("</tr>");
            }
    
            builder.append("</table>");
    
            model.addAttribute("eventosFuturos", builder.toString());
    
            builder = new StringBuilder();
    
            // Lógica de Paginação
            if (numeroDePaginas > 1) {
                builder.append("<div class=\"pagination\">");
                if (numeroDaPagina > 1) {
                    builder.append("<form action=\"/eventosFuturos\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina - 1)).append("\"><button style=\"margin-right:10px\" type=\"submit\">Anterior</button></form>");
                }
                if (numeroDaPagina < numeroDePaginas) {
                    builder.append("<form action=\"/eventosFuturos\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina + 1)).append("\"><button style=\"margin-left:10px\" type=\"submit\">Seguinte</button></form>");
                }
                builder.append("</div>");
            }
    
            model.addAttribute("navegacao", builder.toString());
        }
    
        return "eventosPorOcorrer";
    }
    


    @GetMapping("/eventosADecorrer")
    public String eventosADecorrer(@RequestParam String page, Model model) throws Exception {
        int numeroDaPagina = Integer.parseInt(page); // Deve existir pelo menos uma página
        Registar aux = new Registar();
        StringBuilder builder = new StringBuilder();
    
        JSONArray eventosQueEstaoADecorrer = aux.getEventos(" ");
    
        int numeroDePaginas = (eventosQueEstaoADecorrer.length() + 3) / 4; 
    
        if (eventosQueEstaoADecorrer.length() == 0) {
            if (numeroDaPagina < 1) {
                throw new Exception();
            }
    
            builder.append("<h4> Não há eventos a decorrer neste momento </h4>");
            model.addAttribute("eventosHoje", builder.toString());
        } else {
            if (numeroDaPagina < 1 || numeroDaPagina > numeroDePaginas) {
                throw new Exception();
            }
    
            // Início da tabela
            builder.append("<table class=\"events-table\">");
            builder.append("<tr><th>Nome do Evento</th><th>Data do Evento</th><th>Detalhes do Evento</th></tr>");
    
            int indiceInicio = (numeroDaPagina - 1) * 4;
            int indiceFim = Math.min(indiceInicio + 4, eventosQueEstaoADecorrer.length());
    
            for (int i = indiceInicio; i < indiceFim; i++) {
                JSONObject evento = eventosQueEstaoADecorrer.getJSONObject(i);
    
                builder.append("<tr>");
                builder.append("<td>").append(evento.getString("nomeDoEvento")).append("</td>");
                builder.append("<td>").append(evento.getString("dataDoEvento")).append("</td>");
                builder.append("<td><form action=\"/informacaoEvento\" method=\"GET\"><input type=\"hidden\" name=\"evento\" value=\"").append(evento.getString("nomeDoEvento")).append("\"><button type=\"submit\">Detalhes</button></form></td>");
                builder.append("</tr>");
            }
    
            builder.append("</table>");
    
            model.addAttribute("eventosHoje", builder.toString());
    
            builder = new StringBuilder();
    
            if (numeroDePaginas > 1) {
                builder.append("<div class=\"pagination\">");
                if (numeroDaPagina > 1) {
                    builder.append("<form action=\"/eventosADecorrer\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina - 1)).append("\"><button style=\"margin-right:10px\" type=\"submit\">Anterior</button></form>");
                }
                if (numeroDaPagina < numeroDePaginas) {
                    builder.append("<form action=\"/eventosADecorrer\" method=\"GET\"><input type=\"hidden\" name=\"page\" value=\"").append((numeroDaPagina + 1)).append("\"><button style=\"margin-left:10px\" type=\"submit\">Seguinte</button></form>");
                }
                builder.append("</div>");
            }
    
            model.addAttribute("navegacao", builder.toString());
        }
    
        return "eventosAOcorrer";
    }
    

    @GetMapping("/registarTempoParticipante")
    public String registarTempoParticipante(Model model) {
        return "registarTempoParticipante";
    }



    @GetMapping("/registarTempo")
    public String registarTempo(@RequestParam String nomeEvento, 
            @RequestParam int nParticipante,
            @RequestParam String ponto,
            @RequestParam String horas,
            Model model) throws Exception {
        
            
        Registar aux = new Registar(nParticipante, nomeEvento, ponto, horas);
        int res = aux.registarTempo();
        
    
       
        if (res == 1) {
            return "confirmarTempo";
        }     
        else if (res == -1) {
            model.addAttribute("sucesso", "<p class=\"error\"> Falhou o registo do tempo </p>");
            model.addAttribute("motivo", "<p> Este atleta ainda não esta inscrito no evento </p>");
        } else if (res == -2) {
            model.addAttribute("sucesso", "<p class=\"error\"> Falhou o registo do tempo </p>");
            model.addAttribute("motivo", "<p> Este atleta ainda não pagou a inscrição </p>");
        } else if (res == -3) {
            model.addAttribute("sucesso", "<p class=\"error\"> Falhou o registo do tempo </p>");
            model.addAttribute("motivo", "<p> Ainda não existe tempo para o ponto da prova anterior </p>");
        } else {
            model.addAttribute("sucesso", "<p class=\"error\"> Falhou o registo do tempo </p>");
            model.addAttribute("motivo", "<p> O atleta já tem um tempo registado (neste evento, neste ponto) </p>");
        }
        
        return "tempoErro";   
    }



    @GetMapping("/informacaoEvento")
    public String informacaoEvento(@RequestParam String evento, Model model) throws Exception {    
        model.addAttribute("evento", StringUtils.capitalize(evento));
    
        Registar aux = new Registar();
        JSONObject infoDoEvento = aux.informacaoDoEvento(evento);

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int dataDeHoje = Integer.parseInt(df.format(today).replaceAll("-", "")); // Converter a data para um número inteiro. Fica no formato "aaaammdd" (dá jeito para os calculos). Ex: 20240111 para 11 de janeiro de 2024
        // Jeito nos calculos de verificar se o evento já passou ou não, ou seja ja pode ter classificação
        int dataDoEvent = Integer.parseInt(infoDoEvento.getString("dataDoEvento").replaceAll("-", ""));

        String info = "<p>Descrição do evento: " + infoDoEvento.getString("descricaoDoEvento") + "</p><br>" +
                      "<p>Data do evento: " + infoDoEvento.getString("dataDoEvento") + "</p><br>" +
                      "<p>Preço de inscrição do evento: " + infoDoEvento.getString("preco") + "&euro;</p><br>";
        
        model.addAttribute("info", info);


        JSONArray participantes = aux.participantesDoEvento(evento);
        
        if (participantes.length() > 0) {
            StringBuilder builder = new StringBuilder();

            builder.append("<table class=\"participants-table\">");
            builder.append("<tr>")
                   .append("<th>Número</th>")
                   .append("<th>Nome</th>")
                   .append("<th>Escalão</th>")
                   .append("<th>Gênero</th>")
                   .append("<th>Pagamento</th>")
                   .append("</tr>");
        
            for (int i = 0; i < participantes.length(); i++) {
                JSONObject participante = participantes.getJSONObject(i);

                builder.append("<tr>")
                       .append("<td>").append(participante.getInt("nParticipante")).append("</td>")
                       .append("<td>").append(participante.getString("nomeParticipante")).append("</td>")
                       .append("<td>").append(participante.getString("escalaoParticipante").toUpperCase()).append("</td>")
                       .append("<td>").append(participante.getString("generoParticipante")).append("</td>");

                if (participante.getBoolean("pagamento")) {
                    builder.append("<td>Válido</td>");
                } else {
                    builder.append("<td>Inválido</td>");
                }
        
                builder.append("</tr>");
            }
        
            builder.append("</table>");
            model.addAttribute("participantes", builder.toString());

            if (dataDoEvent <= dataDeHoje) {
                model.addAttribute("classificacao", "<form action=\"/classificacoes\" method=\"GET\"><input type=\"hidden\" name=\"evento\" value=\"" + evento + "\"><button class=\"classification-button\" type=\"submit\"> Classificação </button></form>");
            } else {
                model.addAttribute("classificacao", "<p> Este evento ainda não tem classificação, porque não acabou. Nem começou. </p>");
            }
        } else {
            if (dataDoEvent < dataDeHoje) {
                model.addAttribute("participantes", "<p> Este evento não teve participantes</p>");
                model.addAttribute("classificacao", "<p> Este evento não teve classificação, porque já acabou e não teve participantes. </p>");
            } else {
                model.addAttribute("participantes", "<p> Este evento ainda não tem participantes. </p>");
                model.addAttribute("classificacao", "<p> Este evento ainda não tem classificação, porque não acabou. </p>");
            }
        }
        
        return "infoEvento";
    }



    @GetMapping("/eventosPesquisar")
    public String eventosPesquisar(Model model) throws Exception {
        Registar aux = new Registar();
        JSONArray todosOsEventos = aux.getEventos("todos");
    
        StringBuilder eventosBuilder = new StringBuilder();
    
        if (todosOsEventos.length() == 0) {
            eventosBuilder.append("<tr><td colspan=\"3\"> Não há eventos registados. </td></tr>");
        } else {
            for (int i = 0; i < todosOsEventos.length(); i++) {
                JSONObject evento = todosOsEventos.getJSONObject(i);
                String nomeDoEvento = evento.getString("nomeDoEvento");
                String dataDoEvento = evento.getString("dataDoEvento");
    
                eventosBuilder.append("<tr id=\"").append(nomeDoEvento).append("|").append(dataDoEvento).append("\">");
                eventosBuilder.append("<td>").append(nomeDoEvento).append("</td>");
                eventosBuilder.append("<td>").append(dataDoEvento).append("</td>");
                eventosBuilder.append("<td><form action=\"/informacaoEvento\" method=\"GET\">");
                eventosBuilder.append("<input type=\"hidden\" name=\"evento\" value=\"").append(nomeDoEvento).append("\">");
                eventosBuilder.append("<button type=\"submit\"> Detalhes </button></form></td>");
                eventosBuilder.append("</tr>");
            }
        }
    
        model.addAttribute("eventos", eventosBuilder.toString());
        return "pesquisaEventos";
    }
    


    @GetMapping("/classificacoes")
    public String classificacao(@RequestParam String evento, Model model)  throws Exception {
        Registar aux = new Registar();
        JSONObject classificacoes = aux.classificacaoDoEvento(evento);

        StringBuilder classificacoesBuilder = new StringBuilder();
        
        // Processar os finalizados
        JSONArray finalizados = classificacoes.getJSONArray("finalizados");
        if (finalizados.length() == 0) {
            classificacoesBuilder.append("<tr><td colspan=\"4\">Não há participantes que finalizaram o evento.</td></tr>");
        } else {
            for (int i = 0; i < finalizados.length(); i++) {
                JSONObject participante = finalizados.getJSONObject(i);
                classificacoesBuilder.append("<tr>");
                classificacoesBuilder.append("<td>").append(participante.getInt("nparticipante")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("nomeParticipante")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("genero")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("escalao")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getDouble("tempoTotal")).append("</td>");
                classificacoesBuilder.append("<td>");
                classificacoesBuilder.append("<form action='/tempoParticipante' method='GET'>");
                classificacoesBuilder.append("<input type='hidden' name='evento' value='").append(evento).append("'>");
                classificacoesBuilder.append("<input type='hidden' name='nparticipante' value='").append(participante.getInt("nparticipante")).append("'>");
                classificacoesBuilder.append("<button class='classification-button' type='submit'>+</button>");
                classificacoesBuilder.append("</form>");
                classificacoesBuilder.append("</tr>");
            }
        }

        // Processar os não finalizados
        JSONArray naoFinalizados = classificacoes.getJSONArray("naoFinalizados");
        if (naoFinalizados.length() > 0) {
            for (int i = 0; i < naoFinalizados.length(); i++) {
                JSONObject participante = naoFinalizados.getJSONObject(i);
                classificacoesBuilder.append("<tr>");
                classificacoesBuilder.append("<td>").append(participante.getInt("nparticipante")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("nomeParticipante")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("genero")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getString("escalao")).append("</td>");
                classificacoesBuilder.append("<td>").append(participante.getDouble("tempoTotal")).append(" (").append(participante.getString("ultimoPontoDaProva")).append(")").append("</td>");
                classificacoesBuilder.append("<td>");
                classificacoesBuilder.append("<form action='/tempoParticipante' method='GET'>");
                classificacoesBuilder.append("<input type='hidden' name='evento' value='").append(evento).append("'>");
                classificacoesBuilder.append("<input type='hidden' name='nparticipante' value='").append(participante.getInt("nparticipante")).append("'>");
                classificacoesBuilder.append("<button class='classification-button' type='submit'>+</button>");
                classificacoesBuilder.append("</form>");                
                classificacoesBuilder.append("</tr>");
            }
        }   

        model.addAttribute("classificacoes", classificacoesBuilder.toString());
        model.addAttribute("evento", evento);  // Para poder voltar á pagina dos detalhes do evento
        return "listaClassificacoesDoEvento";
    }
    

    @GetMapping("/tempoParticipante")
    public String tempoParticipante(@RequestParam String evento, @RequestParam int nparticipante, Model model) throws Exception {
        Registar aux = new Registar();
        System.out.println(evento);
        System.out.println(nparticipante);
        JSONObject temposParticipante = aux.temposPorPonto(evento, nparticipante);
    
        StringBuilder tabelaTemposBuilder = new StringBuilder();
    
        tabelaTemposBuilder.append("<table>");
        tabelaTemposBuilder.append("<tr><th>Ponto da prova</th><th>Tempo</th></tr>");
        
        // Pontos da prova
        String[] pontosDaProva = {"start", "p1", "p2", "p3", "finish"}; 
    
        for (String ponto : pontosDaProva) {
            tabelaTemposBuilder.append("<tr>");
            tabelaTemposBuilder.append("<td>").append(ponto).append("</td>");
            
            // 
            if (temposParticipante.has(ponto)) {
                tabelaTemposBuilder.append("<td>").append(temposParticipante.getString(ponto)).append("</td>");
            } else {
                tabelaTemposBuilder.append("<td>-----</td>");
            }
    
            tabelaTemposBuilder.append("</tr>");
        }
    
        tabelaTemposBuilder.append("</table>");
    
        model.addAttribute("tabelaTempos", tabelaTemposBuilder.toString());
        model.addAttribute("evento", evento);  // Para poder voltar á pagina das classificações para ver novamente a lista de atletas
        return "temposDoParticipante"; 
    }
    
    









}
