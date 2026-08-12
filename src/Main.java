import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File archivo = new File("gastos.txt");

        Scanner lector = new Scanner(archivo);

        double total = 0;

        Map<String, Double> gastos = new HashMap<>();

        while (lector.hasNextLine()) {

            String linea = lector.nextLine();

            String[] datos = linea.split(",");

            String categoria = datos[0];
            double monto = Double.parseDouble(datos[1]);

            total += monto;

            if (gastos.containsKey(categoria)) {
                gastos.put(categoria, gastos.get(categoria) + monto);
            } else {
                gastos.put(categoria, monto);
            }

            System.out.println("Categoría: " + categoria);
            System.out.println("Monto: $" + monto);
        }

        lector.close();

        System.out.println("\nTotal gastado: $" + total);

        System.out.println("\nGasto por categoria:");

        for (String categoria : gastos.keySet()) {
            System.out.println(categoria + ": $" + gastos.get(categoria));
        }
        String mayorCategoria = "";
        double mayorGasto = 0;

        for (String categoria : gastos.keySet()) {

            if (gastos.get(categoria) > mayorGasto) {
                mayorGasto = gastos.get(categoria);
                mayorCategoria = categoria;
            }
        }
        System.out.println("\nCategoría con mayor gasto:");
        System.out.println(mayorCategoria + ": $" + mayorGasto);

        System.out.println("\\nPorcentaje por categoría:");

        for (String categoria : gastos.keySet()) {

            double porcentaje = (gastos.get(categoria) / total) * 100;

            System.out.println(
                    categoria + ": " + String.format("%.2f", porcentaje) + "%"
            );
        }
    }
}