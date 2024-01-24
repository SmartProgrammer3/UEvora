import java.sql.*;
import java.util.List;

import javax.print.attribute.AttributeSet;

import java.util.ArrayList;

public class ConexaoBD implements java.io.Serializable
{

    public static Statement stmt = null; //variável responsável sobretudo por realizar alterações nas tabelas da base de dados SQL
    public static Connection con = null; //variável responsável por estabelecer a comunicação com a base de dados pretendida
    private String PG_HOST;
    private String PG_DB;
    private String USER;
    private String PWD;


    public ConexaoBD(String PG_HOST, String PG_DB, String USER, String PWD){    
        this.PG_HOST = PG_HOST;
        this.PG_DB = PG_DB;
        this.USER = USER;
        this.PWD = PWD;
    }


    /*
        Função responsável por criar a conexão com a base de dados
    */
    public void connectDb() {
    
        try {
            Class.forName ("org.postgresql.Driver");    
            
            
            // url = "jdbc:postgresql://host:port/database",
            con = DriverManager.getConnection("jdbc:postgresql://"+PG_HOST+":5432/"+PG_DB, USER, PWD);
            
            stmt = con.createStatement();
            
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems setting the connection");

        }


    }



    public boolean inserirTabelaUtilizador(Utilizador utilizador) {
        try {
            // Query SQL para inserir um novo artista na tabela 'artistas'.
            String query = "INSERT INTO utilizadores (username, email, password, tipoDeUtilizador) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Define os parâmetros da query com os valores do objeto Artistas.
                preparedStatement.setString(1, utilizador.getUtilizador());
                preparedStatement.setString(2, utilizador.getEmail());
                preparedStatement.setString(3, utilizador.getPasse());
                preparedStatement.setString(4, utilizador.getTipo());

                preparedStatement.executeUpdate(); // Executa o insert na tabela.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao inserir o artista à tabela.");
        }

        return true;
    }


    public List<Utilizador> consultarTabelaUtilizadores(String fields) {
        List<Utilizador> dadosLogin = new ArrayList<Utilizador>();
        Utilizador utilizador;

        try {
            String query = "SELECT username, email, tipoDeUtilizador FROM utilizadores " + fields + " ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String role = rs.getString("tipoDeUtilizador");

                utilizador = new Utilizador();
                utilizador.setUtilizador(username);
                utilizador.setEmail(email);
                utilizador.setTipo(role);

                dadosLogin.add(utilizador);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro?");
        }
        return dadosLogin;
    }








    /*
    * Este método tem como objetivo inserir um objeto da classe Artistas na tabela 'artistas'
    * da base de dados. 
    * Retorna o artistID.
    */
    public int inserirTabelaArtistas(Artistas artista) {
        int artistid = -1;
        String count = "SELECT COUNT(*) AS number FROM artistas";

        try {
            // Contar o número de artistas existentes na tabela.
            ResultSet rs = stmt.executeQuery(count);
            rs.next();
            artistid = rs.getInt("number");

            try {
                // Query SQL para inserir um novo artista na tabela 'artistas'.
                String query = "INSERT INTO artistas (nomeArtista, tipoDeArte, latitude, longitude, aAtuar, estado, artistid) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    // Define os parâmetros da query com os valores do objeto Artistas.
                    preparedStatement.setString(1, artista.getNome());
                    preparedStatement.setString(2, artista.getTipoDeArte());
                    preparedStatement.setDouble(3, artista.getLatitude());
                    preparedStatement.setDouble(4, artista.getLongitude());
                    preparedStatement.setBoolean(5, artista.getAAtuar());
                    preparedStatement.setString(6, artista.getEstado());
                    preparedStatement.setInt(7, artistid); 

                    preparedStatement.executeUpdate(); // Executa o insert na tabela.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Problemas ao inserir o artista à tabela.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao contar o número de artistas na tabela.");
        }

        return artistid;
    }
    



    public List<Artistas> consultarTabelaArtistas(String fields) {
        List<Artistas> artistasEncontrados = new ArrayList<Artistas>();
        Artistas artista = null;

        try {
            String insertQuery = "SELECT * FROM artistas " + fields + " ;";
            ResultSet rs = stmt.executeQuery(insertQuery);

            while (rs.next()) {
                String nomeArti = rs.getString("nomeArtista");
                String tipoArte = rs.getString("tipoDeArte");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Boolean aatuar = rs.getBoolean("aAtuar");
                String aprovado = rs.getString("estado");
                int id = rs.getInt("artistID");

                artista = new Artistas();
                artista.setNome(nomeArti);
                artista.setTipoDeArte(tipoArte);
                artista.setLongitude(longitude);
                artista.setLatitude(latitude);
                artista.setAAtuar(aatuar);
                artista.setEstado(aprovado);
                artista.setID(id);

                artistasEncontrados.add(artista);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erroooo");
        }

        return artistasEncontrados;
    }


    public void localizacaoAlterar(String set, int artistID) {
        try {
            String query = "UPDATE artistas SET " + set + " WHERE artistid= " + artistID + " ;";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não deu para atualizar.");
        }
    }


    public int inserirTabelaAtuacao(Atuacoes atuacao) {
        int idatuacao = -1;
        String count = "SELECT COUNT(*) AS number FROM atuacoes";

        try {
            // Contar o número de atuacoes existentes na tabela.
            ResultSet rs = stmt.executeQuery(count);
            rs.next();
            idatuacao = rs.getInt("number");            
            try {
                String query = "INSERT INTO atuacoes (iddoartista, latitude, longitude, datadaatuacao, idatuacao) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


                    preparedStatement.setInt(1, atuacao.getidArtista());
                    preparedStatement.setDouble(2, atuacao.getLati());
                    preparedStatement.setDouble(3, atuacao.getLongi());
                    preparedStatement.setDate(4, atuacao.getDataAtuacao());
                    preparedStatement.setInt(5, idatuacao);

                    preparedStatement.executeUpdate(); // Executa o insert na tabela.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Problemas ao inserir a atuacao à tabela.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao contar o número de artistas na tabela.");
        }

        return idatuacao;
    }


    public List<Atuacoes> consultarTabelaAtuacoes(String fields) {
        List<Atuacoes> atuacoesEncontradas = new ArrayList<Atuacoes>();
        Atuacoes atuacao = null;

        try {
            String insertQuery = "SELECT * FROM atuacoes " + fields + " ;";
            ResultSet rs = stmt.executeQuery(insertQuery);

            while (rs.next()) {
                int artistid = rs.getInt("iddoartista");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Date data = rs.getDate("datadaatuacao");
                int id = rs.getInt("idatuacao");

                atuacao = new Atuacoes();
                atuacao.setidArtista(artistid);
                atuacao.setLati(latitude);
                atuacao.setLongi(longitude);
                atuacao.setDataAtuacao(data);
                atuacao.setIdAtuacao(id);

                atuacoesEncontradas.add(atuacao);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erroooo");
        }

        return atuacoesEncontradas;
    }

 

   
    public List<Integer> retornarIDSTabela() {
        List<Integer> listaDeIds = new ArrayList<>();
        
        try {
            // Define a consulta sql para obter todos os ids da tabela artistas.
            String sql = "SELECT artistid FROM artistas";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int artistId = rs.getInt("artistid");
                    listaDeIds.add(artistId);
                }
  
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeIds;
    }
    


    public int inserirTabelaDonativos(Donativo donativo) {
        int iddoacao = -1;
        String count = "SELECT COUNT(*) AS number FROM donativos";

        try {
            // Contar o número de donativos existentes na tabela.
            ResultSet rs = stmt.executeQuery(count);
            rs.next();
            iddoacao = rs.getInt("number");            
            try {
                String query = "INSERT INTO donativos (idartista, valordodonativo, userdodonativo, datadodonativo, iddoacao) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


                    preparedStatement.setInt(1, donativo.getidartista());
                    preparedStatement.setDouble(2, donativo.getValorDonativo());
                    preparedStatement.setString(3, donativo.getUserEnvioDonativo());
                    preparedStatement.setDate(4, donativo.getDataDonativo());
                    preparedStatement.setInt(5, iddoacao);

                    preparedStatement.executeUpdate(); // Executa o insert na tabela.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Problemas ao inserir o donativo à tabela.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao contar o número de artistas na tabela.");
        }

        return iddoacao;
    }



    public List<Donativo> consultarTabelaDonativos(String fields) {
        List<Donativo> listaDosDonativos = new ArrayList<Donativo>();
        Donativo donativo;

        try {
            String insertQuery = "SELECT * FROM donativos " + fields + " ;";
            ResultSet rs = stmt.executeQuery(insertQuery);
  
            while (rs.next()) {
                int idartista = rs.getInt("idartista");
                int valordonativo = rs.getInt("valordodonativo");
                String doador = rs.getString("userdodonativo");
                Date data = rs.getDate("datadodonativo");
                int idDoacao = rs.getInt("iddoacao");

                donativo = new Donativo();
                donativo.setidartista(idartista);
                donativo.setValorDonativo(valordonativo);
                donativo.setUserEnvioDonativo(doador);
                donativo.setDataDonativo(data);
                donativo.setDonativoID(idDoacao);

                listaDosDonativos.add(donativo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problemas a obter dados ...");
        }
        return listaDosDonativos;
    }




    public int inserirTabelaClassificacao(Classificacao classificacao) {
        int idclassificacao = -1;
        String count = "SELECT COUNT(*) AS number FROM classificacoes";

        try {
            // Contar o número de donativos existentes na tabela.
            ResultSet rs = stmt.executeQuery(count);
            rs.next();
            idclassificacao = rs.getInt("number");            
            try {
                String query = "INSERT INTO classificacoes (artistid, rating, utilizadorClas, idclassificacao) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


                    preparedStatement.setInt(1, classificacao.getIDArt());
                    preparedStatement.setDouble(2, classificacao.getRating());
                    preparedStatement.setString(3, classificacao.getUsername());
                    preparedStatement.setInt(4, idclassificacao);

                    preparedStatement.executeUpdate(); // Executa o insert na tabela.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Problemas ao inserir o donativo à tabela.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao contar o número de artistas na tabela.");
        }

        return idclassificacao;
    }


    public double mediaDoRatingDoArtista(int artistID) {
        double media  = 0;
        
        String query = "SELECT SUM(rating) AS totalDeRating, COUNT(*) AS totalDeClassificacoes " + // Soma todos os ratings na variavel totalDeRating e conta todas as classificacoes (diferentes ratings) e guarda no totalDeClassificacoes
                       "FROM classificacoes " +
                       "WHERE artistid = ? " +
                       "GROUP BY artistid";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, artistID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    double totalRaiting = resultSet.getDouble("totalDeRating");
                    int classificacoes = resultSet.getInt("totalDeClassificacoes");
                    
                    media = totalRaiting / classificacoes;
                    return media;
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       

        return 0;
    }


    public void substituirPorAdmin(String username) {
        try {
            String query = "UPDATE utilizadores SET tipodeutilizador='administrador' WHERE username='" + username + "';";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void alterarArtista(String set, int ArtistID) {
        try {
            String insertQuery = "UPDATE artistas SET " + set + " WHERE artistid='" + ArtistID + "' ;";
            stmt.executeUpdate(insertQuery);
            System.out.println("Foi alterado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
































    /*
        Função responsável por finalizar a conexão com a base de dados
     */
    public void closeConnection() {
        try {
            stmt.close();
            con.close();
            System.out.println("Conexão com a base de dados terminada");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}