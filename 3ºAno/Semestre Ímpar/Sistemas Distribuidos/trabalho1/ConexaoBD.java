package trabalho;

import java.sql.*;
import java.util.List;
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
                String query = "INSERT INTO artistas (nomeArtista, tipoDeArte, localizacao, aAtuar, estado, artistid) VALUES (?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    // Define os parâmetros da query com os valores do objeto Artistas.
                    preparedStatement.setString(1, artista.getNome());
                    preparedStatement.setString(2, artista.getTipoDeArte());
                    preparedStatement.setString(3, artista.getLocalizacao());
                    preparedStatement.setBoolean(4, artista.getAAtuar());
                    preparedStatement.setString(5, artista.getEstado());
                    preparedStatement.setInt(6, artistid); 
                    
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
    


    /*
    * Este método tem o propósito de inserir um objeto da classe Donativo na tabela 'donativos'
    * da base de dados.
    * Retorna o ID do donativo.
    */
    public int inserirTabelaDonativos(Donativo donativo) {
        int doacaoid = -1;
        String count = "SELECT COUNT(*) AS number FROM donativos"; // Consulta SQL para contar o número atual de donativos na tabela.
    
        try {
            // Executar a consulta para obter o número de donativos.
            ResultSet rs = stmt.executeQuery(count);
            rs.next();
            doacaoid = rs.getInt("number");
    
            try {
                // Define a query SQL para inserir um novo donativo na tabela 'donativos'
                String query = "INSERT INTO donativos (artistID, valorDonativo, doacaoid) VALUES (?, ?, ?)";
                
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    // Define os parâmetros da query com os valores do objeto Donativo.
                    preparedStatement.setInt(1, donativo.artistID());
                    preparedStatement.setInt(2, donativo.getDonativo());
                    preparedStatement.setInt(3, doacaoid); 
                    
                    preparedStatement.executeUpdate(); // Executa o insert na tabela.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Problemas ao inserir o donativo à tabela.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao obter o número de donativos na tabela.");
        }
        return doacaoid;
    }
    
    

    /*
    * Este método tem como objetivo realizar uma consulta na tabela artistas com base em filtros
    * fornecidos como parâmetros em formato de string. 
    * Retorna uma lista de objetos da classe Artistas que atendem aos critérios dados pelos filtros.
    */
    public List<Artistas> consultarTabelaArtistas(String campos) {
        List<Artistas> resultados = new ArrayList<>();
    
        try {
            // Filtros individuais
            String[] filtros = campos.split("&");
            StringBuilder clausulaWhere = new StringBuilder();
            
            // Construir a cláusula WHERE com base nos filtros
            for (String filtro : filtros) {
                String[] chaveValor = filtro.split("=");
                String nomeColuna = chaveValor[0];
                String valorColuna = chaveValor[1];
    
                // Adiciona o filtro à cláusula WHERE
                if (nomeColuna.equals("localizacao") || nomeColuna.equals("tipoDeArte")) {
                    clausulaWhere.append(nomeColuna).append("='").append(valorColuna).append("' AND ");
                } else {
                    // Se for outro tipo de filtro, personalize conforme necessário
                    // (por exemplo, considerando intervalos, comparações, etc.)
                    clausulaWhere.append(nomeColuna).append("='").append(valorColuna).append("' AND ");
                }
            }
    
            // Remover o último "AND" da cláusula WHERE, se existir
            if (clausulaWhere.length() > 0) {
                clausulaWhere.setLength(clausulaWhere.length() - 5);
            }
            
            // Consulta sql final
            String sql = "SELECT * FROM artistas";
            if (clausulaWhere.length() > 0) {
                sql += " WHERE " + clausulaWhere.toString();
            }
            

            // Executar a consulta e cria os objetos Artistas com base nos filtros.
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
    
                while (rs.next()) {
                    String nome = rs.getString("nomeArtista");
                    String tipoDeArte = rs.getString("tipoDeArte");
                    String localizacao = rs.getString("localizacao");
                    boolean aAtuar = rs.getBoolean("aAtuar");
                    String estado = rs.getString("estado");
    
                    Artistas artista = new Artistas(null, null, null, false, null);
    
                    artista.setNome(nome);
                    artista.setTipoDeArte(tipoDeArte);
                    artista.setLocalizacao(localizacao);
                    artista.setAAtuar(aAtuar);
                    artista.setEstado(estado);
                    
                    // Adiciona o artista à lista
                    resultados.add(artista);
                }

                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problemas.");
        }
        return resultados;
    }
    

    /*
    * Este método tem o propósito de obter uma lista de localizações onde os artistas estão atualmente a atuar.
    * Retorna uma lista de strings representando as localizações dos artistas a atuar.
    */
    public List<String> obterArtistasAAtuar() {
        List<String> localizacoesAAtuar = new ArrayList<>(); // Lista para armazenar as localizações dos artistas a atuar.
    
        try {
            // Consulta sql para obter localizações diferentes, onde aAtuar é 'true'. 
            String sql = "SELECT DISTINCT localizacao FROM artistas WHERE aAtuar = 'true'" + ";";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
                
                // Adicionar a localização à lista.
                while (rs.next()) {
                    String localizacao = rs.getString("localizacao");
                    localizacoesAAtuar.add(localizacao);
                }

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localizacoesAAtuar;
    }



    /*
    * Este método tem o propósito de obter uma lista de IDs da tabela artistas.
    * Retorna uma lista de inteiros representando os IDs presentes na tabela.
    */
    public List<Integer> retornarIDSTabela() {
        List<Integer> listaDeIds = new ArrayList<>();
        
        try {
            // Define a consulta sql para obter todos os ids da tabela artistas.
            String sql = "SELECT artistid FROM artistas";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
        
                // Adiciona os ids à lista.
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
    

    /*
    * Este método tem o propósito de obter uma lista de artistas com um estado específico da tabela 'artistas'.
    * Retorna uma lista de objetos da classe Artistas que correspondem ao estado fornecido.
    * Apenas funciona para o cliente administrador.
    */
    public List<Artistas> ObterArtistasEstado(String estado) {
        List<Artistas> artistasPorEstado = new ArrayList<>();
    
        try {
            // Define a consulta sql para obter todos os artistas com um estado específico.
            // Seleciona todas as colunas da tabela artistas e metemos a condicao where, onde 
            // a coluna do estado é igual ao parametro dado pelo cliente admin.  
            String sql = "SELECT * FROM artistas WHERE estado = '" + estado + "'" + ";";

            try (Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql)){

                while (rs.next()) {
                        
                    // Obtém os valores dos atributos do artista do resultado atual.
                    String nome = rs.getString("nomeArtista");
                    String tipoDeArte = rs.getString("tipoDeArte");
                    String localizacao = rs.getString("localizacao");
                    boolean aAtuar = rs.getBoolean("aAtuar");
                    String estadoArtista = rs.getString("estado");
                    
                    // Criamos um novo objeto da classe Artistas com os valores obtidos.
                    Artistas artista = new Artistas(null, null, null, false, null);
                    
                    // Definir os atributos do objeto Artistas.
                    artista.setNome(nome);
                    artista.setTipoDeArte(tipoDeArte);
                    artista.setLocalizacao(localizacao);
                    artista.setAAtuar(aAtuar);
                    artista.setEstado(estadoArtista);
                    
                    // Adiciona o artista à lista.
                    artistasPorEstado.add(artista);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problems");
        }

        return artistasPorEstado;
    }





    /*
    * Este método tem o objetivo de aprovar um artista, alterando o estado do artista para 'aprovado'
    * na tabela 'artistas' com base no artistID fornecido como argumento do cliente administrador.
    * Retorna true se a aprovação for bem-sucedida; caso contrário, retorna false.
    */
    public boolean aprovarArtista(int artistId) {
        try {
            // consulta sql para atualizar o estado do artista
            // Dar update na tabela artistas, meter o estado a aprovado onde o artistID é igual ao metido pelo cliente administrador.
            String aprovar= "UPDATE artistas SET estado = 'aprovado' WHERE artistid = " + artistId + ";";
    
            try (Statement approveStatement = con.createStatement()) {
                int linhasAfetadas = approveStatement.executeUpdate(aprovar);
    
                // Verificar se foi dado algum update. Se sim, retornar true, ou seja foi aprovado.
                if (linhasAfetadas > 0) {
                    return true;
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao aprovar o artista.");
        }
        return false;
    }



    /*
    * Este método tem o propósito de obter uma lista de valores de donativos associados a um artista
    * com base no artistid fornecido como argumento.
    * Retorna a lista de inteiros representando os valores dos donativos do artista.
    */
    public List<Integer> donativosDeUmArtista(Integer id) {
        List<Integer> listaDonativos = new ArrayList<>(); // Inicializa a lista para guardar os valores dos donativos do artista.
    
        // Consulta SQL para obter os valores dos donativos associados ao artistid fornecido como argumento.
        String sql = "SELECT valorDonativo FROM donativos WHERE artistID = '" + id + "'" + ";";
    
        try {
            // Criação do Statement dentro do bloco try para tratamento adequado das exceções.
            try (Statement statement = con.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                
                // Adicionar o valor do donativo à lista.
                while (rs.next()) {
                    int valorDonativo = rs.getInt("valorDonativo");
                    listaDonativos.add(valorDonativo);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problemas ao obter os donativos do artista.");
        }
    
        return listaDonativos;
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