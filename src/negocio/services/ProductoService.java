package negocio.services;

import negocio.models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private List<Producto> productos;

    public ProductoService() {
        productos = new ArrayList<>();
        // Productos predeterminados
        productos.add(new Producto("Lavandina" , 1300));
        productos.add(new Producto("Detergente", 1800));
        productos.add(new Producto("Jabon Ropa", 1850));
        productos.add(new Producto("Desodorante Piso", 500));
        productos.add(new Producto("Trapo Piso", 1100));
        productos.add(new Producto("Escoba", 4300));
        productos.add(new Producto("Brillapiso", 3300));
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void mostrarProductos() {
        System.out.println("Lista de productos disponibles:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }
    }

    // Método para que el admin pueda agregar un nuevo producto
    public void agregarProducto(Scanner scanner) {
        System.out.println("Ingrese nombre del nuevo producto:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese precio del nuevo producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        
        Producto nuevoProducto = new Producto(nombre, precio);
        productos.add(nuevoProducto);

        System.out.println("Producto agregado exitosamente: " + nombre);
    }
}
