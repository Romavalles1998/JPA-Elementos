package elementos;

import elementos.entity.Elemento;
import elementos.entity.Type;
import elementos.repository.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import elementos.repository.ElementoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
class App implements CommandLineRunner {
    private final ElementoRepository elementoRepository;
    private final TypeRepository typeRepository;

    public App(ElementoRepository elementoRepository, TypeRepository typeRepository) {
        this.elementoRepository = elementoRepository;
        this.typeRepository = typeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Menú en una sola línea
            System.out.println("Bienvenido a Elemental Elementos | 1. Registrar | 2. Eliminar | 3. Mostrar Todos | 4. Salir");

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarElementoPorNombreYEjemplo(scanner);
                    break;
                case 2:
                    eliminarElementoPorNombreYEjemplo(scanner);
                    break;
                case 3:
                    mostrarTodasLosElementos();
                    break;
                case 4:
                    System.out.println("Saliendo de Elemental Elementos...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarElementoPorNombreYEjemplo(Scanner scanner) {
        System.out.println("Ingrese el nombre del elemento:");
        String nombre = scanner.next();

        System.out.println("Ingrese el ejemplo del elemento:");
        String ejemplo = scanner.next();

        System.out.println("Ingrese el ID del tipo de elemento:");

        List<Type> tipos = (List<Type>) typeRepository.findAll();
        tipos.forEach(tipo -> System.out.println(tipo.getId() + ". " + tipo.getName()));

        Long tipoId;
        try {
            tipoId = scanner.nextLong();
        } catch (Exception e) {
            System.out.println("ID de tipo de elemento no válido. Ingrese un número.");
            scanner.nextLine();
            return;
        }

        Optional<Type> tipoOptional = typeRepository.findById(tipoId);
        if (tipoOptional.isEmpty()) {
            System.out.println("Tipo de elemento con ID " + tipoId + " no encontrado.");
            System.out.println("No se puede registrar el elemento.");
            return;
        }

        Type tipo = tipoOptional.get();
        Elemento nuevoElemento = new Elemento(nombre, ejemplo);
        nuevoElemento.setType(tipo);

        elementoRepository.save(nuevoElemento);
        System.out.println("Elemento registrado exitosamente.");
    }

    private void eliminarElementoPorNombreYEjemplo(Scanner scanner) {
        System.out.println("Ingrese el nombre del elemento a eliminar:");
        String nombre = scanner.next();

        System.out.println("Ingrese el ejemplo del elemento a eliminar:");
        String ejemplo = scanner.next();

        List<Elemento> elementos = elementoRepository.findElementosByNameAndEjemplo(nombre, ejemplo);

        if (elementos.isEmpty()) {
            System.out.println("No se encontró un elemento con nombre " + nombre + " y ejemplo " + ejemplo);
            return;
        }

        Elemento elementoAEliminar = elementos.get(0);
        elementoRepository.deleteById(elementoAEliminar.getId());
        System.out.println("Elemento eliminado exitosamente.");
    }

    private void mostrarTodasLosElementos() {
        System.out.println("Lista de todas los elementos registrados:");

        List<Elemento> elementos = (List<Elemento>) elementoRepository.findAll();
        if (elementos.isEmpty()) {
            System.out.println("No hay elementos registrados.");
        } else {
            String reset = "\u001B[0m";
            String blueColor = "\u001B[34m";

            for (Elemento elemento : elementos) {
                System.out.println("ID: " + elemento.getId() +
                        ", Nombre: " + blueColor + elemento.getName() + reset +
                        ", Ejemplo: " + blueColor + elemento.getEjemplo() + reset +
                        ", Tipo: " + blueColor + elemento.getType().getName() + reset);
            }
        }
    }
}