package negocio.models;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Cliente cliente;
    private List<Producto> productos;  // Lista de productos en la factura
    private double total;

    // Constructor
    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    // Método para agregar un producto a la factura
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio(); // Actualiza el total con el precio del producto
    }

    // Método para obtener el cliente
    public Cliente getCliente() {
        return cliente;
    }

    // Método para obtener el total de la factura
    public double getTotal() {
        return total;
    }

    // Método para mostrar el detalle de la factura
    public void mostrarFactura() {
        System.out.println("Factura para el cliente: " + cliente.getNombre());
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }
        System.out.println("Total a pagar: $" + total);
    }
}
