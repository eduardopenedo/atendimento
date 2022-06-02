package br.edu.ifba.atendimento_discente;

import br.edu.ifba.atendimento_discente.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.edu.ifba.atendimento_discente.DataAccess.*;
import static br.edu.ifba.atendimento_discente.models.Settings.*;

public class Main {

    public static void main(String[] args) {

        MenuLogin();

    }

    private static void InserirFeedback(ArrayList<RegistroAtendimentos> atendimentos, Connection connection, Usuario usuario) {
        System.out.println("\nDigite o Id do registro que gostaria de inserir/atualizar um feedback:");
        Scanner s = new Scanner(System.in);
        Integer idRegistroEscolhido = s.nextInt();

        Boolean IsdValidId = CheckIdInAtendimentos(atendimentos, idRegistroEscolhido);

        if (IsdValidId) {
            Scanner v = new Scanner(System.in);
            System.out.println("\nDigite o feedback:");
            String feedback = v.nextLine();
            UpdateRegistro(connection, idRegistroEscolhido, feedback, usuario.getId());
        } else {
            System.out.println("Id inválido");
            MenuLogin();
        }


    }


    private static Boolean CheckIdInAtendimentos(ArrayList<RegistroAtendimentos> atendimentos, Integer idRegistroEscolhido) {
        //implementar se id está em lista de atendimentos
        return true;
    }

    private static void RegistrosAnteriores(Connection connection, Integer userId) {
        System.out.println("=========================== ATENDIMENTOS ANTERIORES ===========================");
        ArrayList<RegistroAtendimentos> atendimentos = GetTodosRegistrosAluno(connection, userId);
        ListarRegistros(atendimentos);
    }

    private static void ListarMateriasAluno(Connection connection, ArrayList<Turma> turmas) {
        if (turmas.isEmpty()) {
            System.out.println("Aluno não matriculado");


        }
        for (Turma turma :
                turmas) {
            System.out.println(turma.Id + " - " + turma.Nome);
        }
    }

    private static void ListarRegistros(ArrayList<RegistroAtendimentos> atendimentos) {

        System.out.println(" \n Id Atendimento | \t Id Aluno | \t  Descricao | \t tipoAtendimento | \t feedback | \t Id Usuario que forneceu Feedback | \t   ");
        for (RegistroAtendimentos atendimento : atendimentos) {

            System.out.println(String.format("%s |\t %s |\t %s |\t %s |\t %s |\t %s ", atendimento.getId(), atendimento.getIdAtendido(), atendimento.getDescricao(), atendimento.getTipoAtendimento(), atendimento.getFeedback(), atendimento.getFeedbackUsuarioId()));
        }

    }


    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void MenuLogin() {

        System.out.println("=================================================");
        Scanner s = new Scanner(System.in);
        System.out.println("Digite sua matrícula:");
        String matricula = s.nextLine();

        System.out.println("Digite seu CPF:");
        String cpf = s.nextLine();

        Usuario user;
        clearConsole();

        try {

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSW);
            user = GetUserData(connection, cpf, matricula);

            // checar se usuario está vazio /nulo

            System.out.println(String.format("Bem vindo CPF %s, MATRICULA %s", user.getCpf(), user.getMatricula()));

            if (user.getPsicologo()) {
                ArrayList<RegistroAtendimentos> atendimentos = GetRegistroAtendimentosPorTipo(connection, "PSICOLOGICO");
                System.out.println("------------------ MENU PSICOLOGO ------------------");
                if(atendimentos.isEmpty()){
                    System.out.println("Nenhum atendimento para enviar feedback");
                    System.exit(0);
                }
                ListarRegistros(atendimentos);
                InserirFeedback(atendimentos, connection, user);
            } else if (user.getFromCampus()) {
                ArrayList<RegistroAtendimentos> atendimentos = GetRegistroAtendimentosNotMatchesTipo(connection, "PSICOLOGICO");
                System.out.println("------------------ MENU FUNCIONARIO CAMPUS ------------------");
                if(atendimentos.isEmpty()){
                    System.out.println("Nenhum atendimento para enviar feedback");
                    System.exit(0);
                }
                ListarRegistros(atendimentos);
                InserirFeedback(atendimentos, connection, user);
            } else {
                clearConsole();
                RegistrosAnteriores(connection, user.getId());
                System.out.println("Que tipo de acompanhamento necessita?");
                System.out.println("1 - Acadêmico");
                System.out.println("2 - Psicológico");

                int tipoAtendimento = Integer.parseInt(s.nextLine());
                clearConsole();

                String descricao;

                switch (tipoAtendimento) {
                    case 1:
                        ArrayList<Turma> turmas = GetMateriasAluno(connection, user.getId());
                        ListarMateriasAluno(connection, turmas);
                        System.out.println("Digite o número da matéria a qual deseja ser atendido:");
                        int idTurma = Integer.parseInt(s.nextLine());

                        Turma turmaEscolhida = turmas.stream().filter(x -> x.Id == idTurma).toList().get(0);

                        clearConsole();
                        System.out.println("Escreva seu relato:");
                        descricao = s.nextLine();
                        InsertAtendimentoAluno(connection, user.getId().toString(), turmaEscolhida.Nome, descricao);
                        break;

                    case 2:
                        System.out.println("Escreva seu relato:");
                        descricao = s.nextLine();
                        InsertAtendimentoAluno(connection, user.getId().toString(), "PSICOLOGICO", descricao);
                        break;

                    default:
                        System.out.println("Opção Inválida");
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

