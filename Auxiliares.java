import java.util.List;

public class Auxiliares {
    
    public static Double getLatitudeByCoordenada(String coordenada) {
        String [] coordenadaSplit = coordenada.split("/", 2);
        return Double.parseDouble(coordenadaSplit[0]);
    }

    public static Double minInLista(List<Double> lista) {

        Double min = (double) 0;
        for (int i=0; i < lista.size(); i ++) {
            if (i == 0) {
                min = lista.get(i);
            }
            if (lista.get(i) < min) {
                min = lista.get(i);
            }
        }

        return min;
    }

    public static Double maxInLista(List<Double> lista) {
        
        Double max = (double) 0;
        for (int i=0; i < lista.get(i); i++) {
            if (i == 0) {
                max = lista.get(i);
            }
            if (lista.get(i) > max) {
                max = lista.get(i);
            }
        }

        return max;
    }

    public static Double mediaByList(List<Double> lista) {
        
        Double media = (double) 0;
        for (Double valor : lista) {
            media += valor;
        }

        return media / lista.size();
    }
}
