import java.time.LocalDateTime;

public class CauculoTempoIrrigacaoIsolado {

    private Double temperaturaMaxima;

    private Double temperaturaMinima;

    private Double umidadeDoArMedia;

    private Double radiacaoMedia;

    private Double velocidadeDoVentoMedia;

    private Double latitude;

    private Double altitude;
    
    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public Double getUmidadeDoArMedia() {
        return umidadeDoArMedia;
    }

    public Double getRadiacaoMedia() {
        return radiacaoMedia;
    }

    public Double getVelocidadeDoVentoMedia() {
        return velocidadeDoVentoMedia;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public void setUmidadeDoArMedia(Double umidadeDoArMedia) {
        this.umidadeDoArMedia = umidadeDoArMedia;
    }

    public void setRadiacaoMedia(Double radiacaoMedia) {
        this.radiacaoMedia = radiacaoMedia;
    }

    public void setVelocidadeDoVentoMedia(Double velocidadeDoVentoMedia) {
        this.velocidadeDoVentoMedia = velocidadeDoVentoMedia;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public CauculoTempoIrrigacaoIsolado(Double temperaturaMaxima, Double temperaturaMinima, Double umidadeDoArMedia,
            Double radiacaoMedia, Double velocidadeDoVentoMedia, Double latitude, Double altitude) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidadeDoArMedia = umidadeDoArMedia;
        this.radiacaoMedia = radiacaoMedia;
        this.velocidadeDoVentoMedia = velocidadeDoVentoMedia;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public CauculoTempoIrrigacaoIsolado(Double temperaturaMaxima, Double temperaturaMinima) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
    }

    public Double calculoTempoDeIrrigacao(CulturaModel cultura, SistemaIrrigacaoModel sistemaIrrigacao) {

        Double temperaturaMedia = (temperaturaMaxima + temperaturaMinima) / 2;

        Double eto;

        if (temperaturaMedia != null && temperaturaMedia > 0
            && umidadeDoArMedia != null && umidadeDoArMedia > 0
            && radiacaoMedia != null && radiacaoMedia > 0
            && velocidadeDoVentoMedia != null && radiacaoMedia > 0
        ) {

            eto = CalculosIrrigacao.etoPenmanMonteith(
                temperaturaMinima, 
                temperaturaMaxima, 
                umidadeDoArMedia, 
                velocidadeDoVentoMedia, 
                radiacaoMedia, 
                latitude,
                LocalDateTime.now().getDayOfYear(),
                altitude
            );

        } else {

            eto = CalculosIrrigacao.etoHargreavesAndSamani(temperaturaMinima, temperaturaMaxima, temperaturaMedia);
        }

        // Falta confirmar em que momento é feito o calculo da lamina em relação a eficiência
        return calculoTempoDeIrrigacao(cultura, sistemaIrrigacao, eto);

    }

    private Double calculoTempoDeIrrigacao(CulturaModel cultura, SistemaIrrigacaoModel sistemaIrrigacao, Double eto) {

        Double etc = null;

        if (sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.ASPERSAO)) {
            
            etc = CalculosIrrigacao.calculoEtc(eto, cultura.getKc());

        } else if (sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.GOTEJAMENTO) 
            || sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.MICROASPERSAO)) {
            
            etc = CalculosIrrigacao.calculoEtc(eto, cultura.getKc(), 1); 
        }

        System.out.println("ETc: " + etc);

        Double lamina = (etc * 100) / sistemaIrrigacao.getEficiencia();

        System.out.println("Lâmina: " + lamina);

        if (sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.ASPERSAO)) {

            return CalculosIrrigacao.calculoTempoDeIrrigacaoAspersao(lamina, 
            sistemaIrrigacao.getIndicePrecipitacao(), sistemaIrrigacao.getEficiencia());

        } else if (sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.MICROASPERSAO) 
            || sistemaIrrigacao.getTipo().equals(IrrigacaoEnum.GOTEJAMENTO)) {

            return CalculosIrrigacao.calculoTempoDeIrrigacaoMicroOrGotejamento(CalculosIrrigacao.calculoVolumeAplicadoPorPlanta(
                lamina, sistemaIrrigacao.getAreaIrrigada()), CalculosIrrigacao.calculoVolumeAplicadoPlantaHora(
                    sistemaIrrigacao.getEmissoresPorPlanta(), sistemaIrrigacao.getVazao()
                ), 
                sistemaIrrigacao.getEficiencia());

        }

        return null;
    } 
}