package utils;

import java.util.ArrayList;
import java.util.List;
import modelo.Pasajero;

public class Utileria {

    public static List<Integer> CargarAsientosDisp(List<Pasajero> lista) {
        List<Integer> asientos = new ArrayList<>();
        boolean encontro;
        for (int i = 1; i <= 30; i++) {
            encontro = false;
            for (Pasajero p : lista) {
                if (p.getNroAsiento() == i) {
                    encontro = true;
                }
            }
            if (!encontro) {
                asientos.add(new Integer(i));
            }
        }
        return asientos;
    }
}
