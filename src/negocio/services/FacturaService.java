package negocio.services;

import negocio.models.Cliente;
import negocio.models.Factura;
import negocio.models.Producto;

import java.util.List;

public class FacturaService {

    // Generar una factura automáticamente con todos los productos disponibles
    public void generarFactura(Cliente cliente, List<Producto> productosDisponibles) {
        if (productosDisponibles.isEmpty()) {
            System.out.println("No hay productos disponibles para generar una factura.");
            return;
        }

        // Crear una nueva factura para el cliente
        Factura factura = new Factura(cliente);

        System.out.println("\nGenerando factura para el cliente: " + cliente.getNombre());

        // Agregar todos los productos disponibles a la factura
        for (Producto producto : productosDisponibles) {
            factura.agregarProducto(producto); // Agrega el producto a la factura
            System.out.println("Producto: " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }

        // Mostrar el total de la factura
        System.out.println("Total a pagar: $" + factura.getTotal());
        System.out.println("Factura generada con éxito.");
    }
}
