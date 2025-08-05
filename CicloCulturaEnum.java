public enum CicloCulturaEnum {
    
    CURTO("Curto"),
    MEDIO("Médio"),
    LONGO("Longo"),
    PERENE("Perene");

    private String alias;

    CicloCulturaEnum(String alias) {
        this.alias = alias;
    }

    String getAlias() {
        return this.alias;
    }

}
