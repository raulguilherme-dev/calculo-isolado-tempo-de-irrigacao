import java.time.LocalDate;

public class CalculosIrrigacao {
    
    public static Double calculoRadiacaoExtraterrestre(Double latitude, int diaJuliano) {

        final double gsc = 0.0820;

        double latitudeRad = Math.toRadians(latitude);

        double delta = 0.409 * Math.sin((2 * Math.PI * diaJuliano / 365) - 1.39);

        double dr = 1 + 0.033 * Math.cos((2 * Math.PI * diaJuliano / 365));

        double omegaS = Math.acos(-Math.tan(latitudeRad) * Math.tan(delta));

        return (24 * 60 / Math.PI) * gsc * dr * (
            omegaS * Math.sin(latitudeRad) * Math.sin(delta) + 
            Math.cos(latitudeRad) * Math.cos(delta) * Math.sin(omegaS));
    }

    public static Double etoHargreavesAndSamani(Double tempMin, Double tempMax, Double tempMed) {

        return 0.0023 * (tempMed + 17.8) * Math.pow((tempMax - tempMin), 0.5) * calculoRadiacaoExtraterrestre(-10.0, LocalDate.now().getDayOfYear());
    }

    public static Double etoPenmanMonteith(double tempMin,
                                    double tempMax,
                                    double umidadeRelativaMedia,
                                    double vento,
                                    double radSolar,
                                    double latitude,
                                    int diaJualiano,
                                    double altitude) {
        
        double g = 0;
        double albedo = 0.23;
        double sigma = 4.903e-9;

        double tempMedia = (tempMin + tempMax) / 2;
        double delta = 4098 * (0.6108 * Math.exp((17.27 * tempMedia) / (tempMedia + 237.3))) / Math.pow((tempMedia + 237.3), 2);
        double p = 101.3 * Math.pow(((293 - 0.0065 * altitude) / 293), 5.26);
        double gamma = 0.000665 * p;
        
        double esTMax = 0.6108 * Math.exp((17.27 * tempMax) / (tempMax + 237.3));
        double esTMin = 0.6108 * Math.exp((17.27 * tempMin) / (tempMin + 237.3));
        double es = (esTMax + esTMin) / 2;
        double ea = es * (umidadeRelativaMedia / 100);

        double ra = calculoRadiacaoExtraterrestre(latitude, LocalDate.now().getDayOfYear());

        double rns = (1 - albedo) * radSolar;

        double tmaxK = tempMax + 273.16;
        double tminK = tempMin + 273.16;
        double rnl = sigma * ((Math.pow(tmaxK, 4) + Math.pow(tminK, 4)) / 2) * (0.34 - 0.14 * Math.sqrt(ea)) * 
            (1.35 * (radSolar / ra) - 0.35);
        
        double rn = rns - rnl;

        return (0.408 * delta * (rn - g) + gamma * (900 / (tempMedia + 273)) * vento * (es - ea)) / (delta + gamma * (1 + 0.34 * vento));
    }

    public static Double calculoIndicePrecipitacao(double vazao, double espacamentoEntreEmissores, double espacamentoEntreLaterais) {

        return vazao / (12 * 12);
    }

    public static Double calculoAreaDaPlanta(double espacamentoEntreSetores, double espacamentoEntrePlantas) {

        return espacamentoEntreSetores * espacamentoEntrePlantas;
    }

    // para realizar esse calculo é necessário os dados de Area molhada pelo sistema de irrigação e Area ocupada por cada planta
    public static Double calculoKl(double areaIrrigada, double areaDaPlanta) {

        return areaIrrigada / areaDaPlanta;
    }

    public static Double calculoEtc(double eto, double kc) {

        return kc * eto;
    }

    public static Double calculoEtc(double eto, double kc, double kl) {

        return kc * kl * eto;
    }

    public static Double calculoVolumeAplicadoPorPlanta(double lamina, double areaIrrigada) {
        
        return lamina * areaIrrigada;
    }

    public static Double calculoAreaIrrigadaMicro(double diametroDoMicro, double emissoresPorPlanta) {
        
        return (Math.PI * Math.pow(diametroDoMicro, 2) / 4) * emissoresPorPlanta;
    }

    public static Double calculoAreaIrrigadaGotejamento(double espacamentoEntrePlantas, double larguraDeFaixaMolhada) {
        
        return (espacamentoEntrePlantas * larguraDeFaixaMolhada) / 100;
    }

    public static Double calculoEmissoresPorPlanta(double espacamentoEntrePlantas, double espacamentoEntreEmissores) {

        return (espacamentoEntrePlantas * 100) / espacamentoEntreEmissores;
    }

    public static Double calculoVolumeAplicadoPlantaHora(double emissoresPorPlanta, double vazao) {

        return emissoresPorPlanta * vazao;
    }

    public static Double calculoVolumeTotalAplicadoPorPlanta(double lamina, double areaIrrigada) {

        return lamina * areaIrrigada;
    }

    public static Double calculoTempoDeIrrigacaoAspersao(double lamina, double indiceDePrecipitacao, double eficiencia) {

        return lamina / indiceDePrecipitacao * 60 / (eficiencia / 100);
    }

    public static Double calculoTempoDeIrrigacaoMicroOrGotejamento(double volumeAplicadoPlanta, double volumeAplicadoPlantaHora, double eficiencia) {

        return (volumeAplicadoPlanta / volumeAplicadoPlantaHora) * 60 / (eficiencia / 100);
    }
}
