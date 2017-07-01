package dominio;

public class Agenda {

    private Integer idConsulta;
    private Integer indice;
    private String data;
    private String tipoConsulta;
    private String prioridade;
    private boolean confirmacao;
    private String situacao;
    private Integer idPac;
    private Integer matMed;
    private String nomPac;
    private String nomMed;
    private String dataInicio;
    private String dataFinal;

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdPac() {
        return idPac;
    }

    public void setIdPac(Integer idPac) {
        this.idPac = idPac;
    }

    public Integer getMatMed() {
        return matMed;
    }

    public void setMatMed(Integer matMed) {
        this.matMed = matMed;
    }

    public String getNomPac() {
        return nomPac;
    }

    public void setNomPac(String nomPac) {
        this.nomPac = nomPac;
    }

    public String getNomMed() {
        return nomMed;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}