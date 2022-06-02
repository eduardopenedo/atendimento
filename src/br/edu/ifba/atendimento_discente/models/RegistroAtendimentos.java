package br.edu.ifba.atendimento_discente.models;

public class RegistroAtendimentos {


    private String descricao;
    private String feedback;
    private Integer feedbackUsuarioId;
    private String tipoAtendimento;
    private Integer idAtendido;
    private Integer id;

    public RegistroAtendimentos(String Descricao, String Feedback, Integer FeedbackUsuarioId, String TipoAtendimento, Integer IdAtendido, Integer Id){

        descricao = Descricao;
        feedback = Feedback != null ?Feedback:"";
        feedbackUsuarioId = FeedbackUsuarioId;
        tipoAtendimento = TipoAtendimento;
        idAtendido = IdAtendido;
        id = Id;
    }

    public Integer getIdAtendido() {
        return idAtendido;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFeedback() {
        return feedback;
    }

    public Integer getFeedbackUsuarioId() {
        return feedbackUsuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setFeedbackUsuarioId(Integer feedbackUsuarioId) {
        this.feedbackUsuarioId = feedbackUsuarioId;
    }

    public void setIdAtendido(Integer idAtendido) {
        this.idAtendido = idAtendido;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }
}
