public class Main {

    public static void main(String[] args) {
    
        CauculoTempoIrrigacaoIsolado calc1 = new CauculoTempoIrrigacaoIsolado(36.0, 18.0, 25.0, 23.0, 3.5, -8.07, 420.0);

        CauculoTempoIrrigacaoIsolado calc2 = new CauculoTempoIrrigacaoIsolado(36.0, 18.0);

        CulturaModel cultura = new CulturaModel("Tomate", CicloCulturaEnum.CURTO, 0.4);

        SistemaIrrigacaoModel aspersao = SistemaIrrigacaoModel.aspersao(500.0, 12.0, 12.0);

        SistemaIrrigacaoModel microaspersao = SistemaIrrigacaoModel.microaspersao(40.0, 3.0, 0.5);

        SistemaIrrigacaoModel gotejamento = SistemaIrrigacaoModel.gotejamento(1.6, 20.0, 1.0, 35.0);
        
        System.out.println("O tempo de irrigação é de " + calc2.calculoTempoDeIrrigacao(cultura, microaspersao) + " minutos");
    }
}