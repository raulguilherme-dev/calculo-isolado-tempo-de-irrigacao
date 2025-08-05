// import java.text.Normalizer;

public enum IrrigacaoEnum {

    GOTEJAMENTO("Gotejamento"),
    MICROASPERSAO("Microaspersão"),
    ASPERSAO("Aspersão");

    private String alias;

    IrrigacaoEnum(String alias) {
        this.alias = alias;
    }

    String getAlias() {
        return this.alias;
    }


}
