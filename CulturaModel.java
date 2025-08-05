public class CulturaModel {

    private String nome;

    private CicloCulturaEnum ciclo;

    private Double kc;

    

    public CulturaModel(String nome, CicloCulturaEnum ciclo, Double kc) {
        this.nome = nome;
        this.ciclo = ciclo;
        this.kc = kc;
    }

    public String getNome() {
        return nome;
    }

    public CicloCulturaEnum getCiclo() {
        return ciclo;
    }

    public Double getKc() {
        return kc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCiclo(CicloCulturaEnum ciclo) {
        this.ciclo = ciclo;
    }

    public void setKc(Double kc) {
        this.kc = kc;
    }
    
}
