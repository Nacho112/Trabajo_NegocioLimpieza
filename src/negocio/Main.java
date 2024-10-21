package negocio;

import java.util.ArrayList;
import java.util.List;
import negocio.services.*;
import negocio.models.Cliente;
import java.util.Scanner;
import negocio.models.Producto;

public class Main {
    public static void main(String[] args) {
        
        LoginService loginService = new LoginService();
        ClienteService clienteService = new ClienteService();
        ProductoService productoService = new ProductoService();
        FacturaService facturaService = new FacturaService();
        Scanner scanner = new Scanner(System.in);

        // Autenticación
        System.out.println("Ingrese usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese contraseña:");
        String password = scanner.nextLine();

        if (loginService.login(usuario, password)) {
            String rol = loginService.getRol(usuario); // Obtenemos el rol del usuario

            System.out.println("Bienvenido al sistema, rol: " + rol);

            boolean salir = false;
            while (!salir) {
                // Si el usuario es admin, mostramos solo las opciones de agregar clientes o productos
                if (rol.equals("admin")) {
                    System.out.println("\nSeleccione una opción:");
                    System.out.println("1. Agregar Cliente");
                    System.out.println("2. Agregar Producto");
                    System.out.println("3. Salir");

                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch (opcion) {
                        case 1:
                            clienteService.agregarCliente(scanner); // Admin puede agregar clientes
                            break;
                        case 2:
                            productoService.agregarProducto(scanner); // Admin puede agregar productos
                            break;
                        case 3:
                            salir = true;
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                } 
                // Si el usuario es empleado, mostramos solo las opciones de atención, facturación, etc.
                else if (rol.equals("empleado")) {
                    System.out.println("\nSeleccione una opción:");
                    System.out.println("1. Atender Clientes");
                    System.out.println("2. Mostrar Productos");
                    System.out.println("3. Salir");

                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch (opcion) {
                        case 1:
                            Cliente cliente = clienteService.seleccionarCliente(scanner); // Seleccionar cliente
                            if (cliente != null) {
                                atenderCliente(cliente, productoService, facturaService, scanner);
                            }
                            break;
                        case 2:
                            productoService.mostrarProductos(); // Mostrar productos
                            break;
                        case 3:
                            salir = true;
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    // Método para atender al cliente
    private static void atenderCliente(Cliente cliente, ProductoService productoService, FacturaService facturaService, Scanner scanner) {
        System.out.println("Atendiendo al cliente: " + cliente.getNombre());
        boolean salir = false;
        List<Producto> productosSeleccionados = new ArrayList<>();

        while (!salir) {
            // Mostrar submenú para atender cliente
            System.out.println("\nOpciones para el cliente " + cliente.getNombre() + ":");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Finalizar Atención y Generar Factura");
            System.out.println("3. Cancelar");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Mostrar productos y agregar a la lista de selección
                    List<Producto> productos = productoService.getProductos();
                    System.out.println("Seleccione un producto para agregar:");
                    for (int i = 0; i < productos.size(); i++) {
                        System.out.println((i + 1) + ". " + productos.get(i).getNombre() + " - Precio: " + productos.get(i).getPrecio());
                    }
                    int seleccionProducto = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (seleccionProducto > 0 && seleccionProducto <= productos.size()) {
                        Producto productoSeleccionado = productos.get(seleccionProducto - 1);
                        productosSeleccionados.add(productoSeleccionado);
                        System.out.println("Producto agregado: " + productoSeleccionado.getNombre());
                    } else {
                        System.out.println("Selección no válida.");
                    }
                    break;
                case 2:
                    // Generar factura con los productos seleccionados
                    if (!productosSeleccionados.isEmpty()) {
                        facturaService.generarFactura(cliente, productosSeleccionados);
                        salir = true;
                    } else {
                        System.out.println("No ha agregado ningún producto.");
                    }
                    break;
                case 3:
                    salir = true;
                    System.out.println("Atención al cliente cancelada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
