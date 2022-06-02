package br.edu.ifba.atendimento_discente.models;

public class Settings {
    public static final String DB_PASSW = "yourStrong(!)Password";
    public static final String DB_USER = "SA";
    public static final String DB_URL = String.format("jdbc:sqlserver://localhost;user=%s;password=%s;databaseName=AtendimentoIFBA;encrypt=true;trustServerCertificate=true", DB_USER, DB_PASSW);

}
