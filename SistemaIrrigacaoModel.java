
public class SistemaIrrigacaoModel {

    private IrrigacaoEnum tipo;

    private Double vazao;

    private Double espacamentoEntreUnidades;

    private Double espacamentoEntreLaterais;

    private Double indicePrecipitacao;

    private Integer eficiencia;

    private Double diametro;

    private Double emissoresPorPlanta;

    private Double volumeAplicadoNaPlantaPorHora;

    private Double espacamentoEntrePlantas;

    private Double larguraDeFaixaMolhada;

    private Double areaIrrigada;

    private Float qtdPlantasPorMetroQuadrado;

    

    private SistemaIrrigacaoModel(IrrigacaoEnum tipo, Double vazao, Double espacamentoEntreUnidades,
            Double espacamentoEntreLaterais, Double indicePrecipitacao, Integer eficiencia, Double diametro,
            Double emissoresPorPlanta, Double volumeAplicadoNaPlantaPorHora, Double espacamentoEntrePlantas,
            Double larguraDeFaixaMolhada, Double areaIrrigada, Float qtdPlantasPorMetroQuadrado) {
        this.tipo = tipo;
        this.vazao = vazao;
        this.espacamentoEntreUnidades = espacamentoEntreUnidades;
        this.espacamentoEntreLaterais = espacamentoEntreLaterais;
        this.indicePrecipitacao = indicePrecipitacao;
        this.eficiencia = eficiencia;
        this.diametro = diametro;
        this.emissoresPorPlanta = emissoresPorPlanta;
        this.volumeAplicadoNaPlantaPorHora = volumeAplicadoNaPlantaPorHora;
        this.espacamentoEntrePlantas = espacamentoEntrePlantas;
        this.larguraDeFaixaMolhada = larguraDeFaixaMolhada;
        this.areaIrrigada = areaIrrigada;
        this.qtdPlantasPorMetroQuadrado = qtdPlantasPorMetroQuadrado;
    }

    public static SistemaIrrigacaoModel aspersao(Double vazao, Double espacamentoEntreUnidades, Double espacamentoEntreLaterais) {
        return new SistemaIrrigacaoModel(
            IrrigacaoEnum.ASPERSAO, 
            vazao, 
            espacamentoEntreUnidades, 
            espacamentoEntreLaterais, 
            CalculosIrrigacao.calculoIndicePrecipitacao(vazao, espacamentoEntreUnidades, espacamentoEntreLaterais), 
            100, 
            null, 
            null, 
            null, 
            null, 
            null, 
            null, 
            null);
    }

    public static SistemaIrrigacaoModel microaspersao(Double vazao, Double diametro, Double emissoresPorPlanta) {
        return new SistemaIrrigacaoModel(
            IrrigacaoEnum.MICROASPERSAO, 
            vazao, 
            null, 
            null, 
            null, 
            85, 
            diametro, 
            emissoresPorPlanta, 
            CalculosIrrigacao.calculoVolumeAplicadoPlantaHora(emissoresPorPlanta, vazao), 
            null, 
            null, 
            CalculosIrrigacao.calculoAreaIrrigadaMicro(diametro, emissoresPorPlanta), 
            null);
    }

    

    public static SistemaIrrigacaoModel gotejamento(Double vazao, Double espacamentoEntreUnidades, Double espacamentoEntrePlantas,
            Double larguraDeFaixaMolhada) {
        SistemaIrrigacaoModel gotejamento = new SistemaIrrigacaoModel(
            IrrigacaoEnum.GOTEJAMENTO, 
            vazao, 
            espacamentoEntreUnidades, 
            null, 
            null, 
            90, 
            null, 
            CalculosIrrigacao.calculoEmissoresPorPlanta(espacamentoEntrePlantas, espacamentoEntreUnidades), 
            null, 
            espacamentoEntrePlantas, 
            larguraDeFaixaMolhada, 
            CalculosIrrigacao.calculoAreaIrrigadaGotejamento(espacamentoEntrePlantas, larguraDeFaixaMolhada), 
            null);

        gotejamento.setVolumeAplicadoNaPlantaPorHora(CalculosIrrigacao.calculoVolumeAplicadoPlantaHora(gotejamento.emissoresPorPlanta, gotejamento.vazao));

        return gotejamento;
    
    }

    public IrrigacaoEnum getTipo() {
        return tipo;
    }

    public Double getVazao() {
        return vazao;
    }

    public Double getEspacamentoEntreUnidades() {
        return espacamentoEntreUnidades;
    }

    public Double getEspacamentoEntreLaterais() {
        return espacamentoEntreLaterais;
    }

    public Double getIndicePrecipitacao() {
        return indicePrecipitacao;
    }

    public Integer getEficiencia() {
        return eficiencia;
    }

    public Double getDiametro() {
        return diametro;
    }

    public Double getEmissoresPorPlanta() {
        return emissoresPorPlanta;
    }

    public Double getVolumeAplicadoNaPlantaPorHora() {
        return volumeAplicadoNaPlantaPorHora;
    }

    public Double getEspacamentoEntrePlantas() {
        return espacamentoEntrePlantas;
    }

    public Double getLarguraDeFaixaMolhada() {
        return larguraDeFaixaMolhada;
    }

    public Double getAreaIrrigada() {
        return areaIrrigada;
    }

    public Float getQtdPlantasPorMetroQuadrado() {
        return qtdPlantasPorMetroQuadrado;
    }

    public void setTipo(IrrigacaoEnum tipo) {
        this.tipo = tipo;
    }

    public void setVazao(Double vazao) {
        this.vazao = vazao;
    }

    public void setEspacamentoEntreUnidades(Double espacamentoEntreUnidades) {
        this.espacamentoEntreUnidades = espacamentoEntreUnidades;
    }

    public void setEspacamentoEntreLaterais(Double espacamentoEntreLaterais) {
        this.espacamentoEntreLaterais = espacamentoEntreLaterais;
    }

    public void setIndicePrecipitacao(Double indicePrecipitacao) {
        this.indicePrecipitacao = indicePrecipitacao;
    }

    public void setEficiencia(Integer eficiencia) {
        this.eficiencia = eficiencia;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public void setEmissoresPorPlanta(Double emissoresPorPlanta) {
        this.emissoresPorPlanta = emissoresPorPlanta;
    }

    public void setVolumeAplicadoNaPlantaPorHora(Double volumeAplicadoNaPlantaPorHora) {
        this.volumeAplicadoNaPlantaPorHora = volumeAplicadoNaPlantaPorHora;
    }

    public void setEspacamentoEntrePlantas(Double espacamentoEntrePlantas) {
        this.espacamentoEntrePlantas = espacamentoEntrePlantas;
    }

    public void setLarguraDeFaixaMolhada(Double larguraDeFaixaMolhada) {
        this.larguraDeFaixaMolhada = larguraDeFaixaMolhada;
    }

    public void setAreaIrrigada(Double areaIrrigada) {
        this.areaIrrigada = areaIrrigada;
    }

    public void setQtdPlantasPorMetroQuadrado(Float qtdPlantasPorMetroQuadrado) {
        this.qtdPlantasPorMetroQuadrado = qtdPlantasPorMetroQuadrado;
    }

    
}
