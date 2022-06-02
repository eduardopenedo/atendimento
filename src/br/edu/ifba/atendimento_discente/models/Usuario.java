package br.edu.ifba.atendimento_discente.models;

import java.time.temporal.IsoFields;

public class Usuario {
    private Integer Id;
    private String Cpf;
    private Boolean IsFromCampus;
    private Boolean IsPsicologo;
    private String Matricula;

    public Usuario(int Id, String Cpf, Boolean IsFromCampus, Boolean IsPsicologo, String Matricula) {
        this.Id = Id;
        this.Cpf = Cpf;
        this.IsFromCampus = IsFromCampus;
        this.IsPsicologo = IsPsicologo;
        this.Matricula = Matricula;
    }

    public Usuario() {

    }
    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setFromCampus(Boolean fromCampus) {
        IsFromCampus = fromCampus;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setPsicologo(Boolean psicologo) {
        IsPsicologo = psicologo;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getMatricula() {
        return Matricula;
    }

    public Boolean getFromCampus() {
        return IsFromCampus;
    }

    public Boolean getPsicologo() {
        return IsPsicologo;
    }

    public Integer getId() {
        return Id;
    }

    public String getCpf() {
        return Cpf;
    }
}
