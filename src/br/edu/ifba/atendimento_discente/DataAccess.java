package br.edu.ifba.atendimento_discente;

import br.edu.ifba.atendimento_discente.models.RegistroAtendimentos;
import br.edu.ifba.atendimento_discente.models.Turma;
import br.edu.ifba.atendimento_discente.models.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {
    public static  ArrayList<Turma> GetMateriasAluno(Connection connection, Integer userId ){
        String QUERY = String.format("SELECT Turma.id, Turma.nome FROM Usuario INNER JOIN UsuarioTurma ON Usuario.id = UsuarioTurma.usuarioId  INNER JOIN Turma ON Turma.id = UsuarioTurma.TurmaId WHERE Usuario.id = %s;", userId);
        ArrayList<Turma> turmas = new ArrayList<Turma>();

        try {
            Statement statement = connection.createStatement();
            statement.execute(QUERY);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                turmas.add(new Turma(resultSet.getString(2),resultSet.getInt(1)));
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return turmas;
    }



    public static void InsertAtendimentoAluno(Connection connection,String usuarioIdAluno,String tipoAtendimento,String descricao){
        String QUERY = String.format("INSERT [RegistroAtendimentos](atendidoUsuarioId, tipoAtendimento, descricao,feedback, feedbackUsuarioId) VALUES ('%s','%s','%s',NULL,NULL)",usuarioIdAluno,tipoAtendimento,descricao);

        try {
            Statement statement = connection.createStatement();
            statement.execute(QUERY);

            System.out.println("Registro Inserido com Sucesso!");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static Usuario GetUserData(Connection connection, String cpf, String matricula){
        String QUERY = String.format("SELECT  TOP 1 id,cpf, isFromCampus, isPsicologo, matricula  FROM Usuario WHERE cpf=%s AND matricula='%s'",cpf,matricula);
        Usuario usuario = new Usuario();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(QUERY);

            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()){
                usuario.setId(resultSet.getInt(1));
                usuario.setCpf(resultSet.getString(2));
                usuario.setFromCampus(resultSet.getBoolean(3));
                usuario.setPsicologo(resultSet.getBoolean(4));
                usuario.setMatricula(resultSet.getString(5));
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return usuario;
    }

    public static ArrayList<RegistroAtendimentos> GetRegistroAtendimentosPorTipo(Connection connection, String tipoAtendimento){
        String QUERY = String.format("SELECT [id], [atendidoUsuarioId] , [descricao] , [tipoAtendimento] ,[feedback],[feedbackUsuarioId] FROM [RegistroAtendimentos] WHERE [tipoAtendimento]='%s'",tipoAtendimento);
        ArrayList<RegistroAtendimentos> atendimentos = new ArrayList<RegistroAtendimentos>();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(QUERY);

            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer atendidoUsuarioId = resultSet.getInt(2);
                String descricao = resultSet.getString(3);
                String tipo = resultSet.getString(4);
                String feedback =  resultSet.getString(5);
                Integer feedbackUsuarioId = resultSet.getInt(6);

                RegistroAtendimentos registro = new RegistroAtendimentos(descricao,feedback,feedbackUsuarioId,tipo,atendidoUsuarioId,id);
                atendimentos.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return atendimentos;
    }

    public static ArrayList<RegistroAtendimentos> GetTodosRegistrosAluno(Connection connection, Integer userId){
        String QUERY = String.format("SELECT [id], [atendidoUsuarioId] , [descricao] , [tipoAtendimento] ,[feedback],[feedbackUsuarioId] FROM [RegistroAtendimentos] WHERE [atendidoUsuarioId]='%s'",userId);
        ArrayList<RegistroAtendimentos> atendimentos = new ArrayList<RegistroAtendimentos>();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(QUERY);

            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer atendidoUsuarioId = resultSet.getInt(2);
                String descricao = resultSet.getString(3);
                String tipo = resultSet.getString(4);
                String feedback =  resultSet.getString(5);
                Integer feedbackUsuarioId = resultSet.getInt(6);

                RegistroAtendimentos registro = new RegistroAtendimentos(descricao,feedback,feedbackUsuarioId,tipo,atendidoUsuarioId,id);
                atendimentos.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return atendimentos;
    }


    public static ArrayList<RegistroAtendimentos> GetRegistroAtendimentosNotMatchesTipo(Connection connection, String tipoAtendimento){
        String QUERY = String.format("SELECT [id], [atendidoUsuarioId] , [descricao] , [tipoAtendimento] ,[feedback],[feedbackUsuarioId] FROM [RegistroAtendimentos] WHERE [tipoAtendimento] NOT LIKE '%s'",tipoAtendimento);
        ArrayList<RegistroAtendimentos> atendimentos = new ArrayList<RegistroAtendimentos>();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(QUERY);

            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer atendidoUsuarioId = resultSet.getInt(2);
                String descricao = resultSet.getString(3);
                String tipo = resultSet.getString(4);
                String feedback =  resultSet.getString(5);
                Integer feedbackUsuarioId = resultSet.getInt(6);

                RegistroAtendimentos registro = new RegistroAtendimentos(descricao,feedback,feedbackUsuarioId,tipo,atendidoUsuarioId,id);
                atendimentos.add(registro);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return atendimentos;
    }

    public static void UpdateRegistro(Connection connection, Integer idRegistro, String feedback, Integer idUsuario) {
        String QUERY = String.format("UPDATE RegistroAtendimentos SET [feedback] = '%s', [feedbackUsuarioId] ='%s'  WHERE [id] = '%s'",feedback,idUsuario,idRegistro);

        try {
            Statement statement = connection.createStatement();
            statement.execute(QUERY);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Registro atualizado com sucesso!");
    }

}
