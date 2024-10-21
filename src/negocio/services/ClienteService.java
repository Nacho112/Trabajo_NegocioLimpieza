package negocio.services;

import negocio.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService() {
        clientes = new ArrayList<>();
        // Clientes predeterminados
        clientes.add(new Cliente("Colegio Adventista"    , "0261 9309814"));
        clientes.add(new Cliente("Colegio Misericordia"  , "0261 7948777"));
        clientes.add(new Cliente("Colegio San Gabriel"   , "0261 4496619"));
        clientes.add(new Cliente("2 Espigas - Panaderia" , "0261 4356077"));
        clientes.add(new Cliente("Gimnasio One Fit"      , "0261 8836724"));
        clientes.add(new Cliente("Maldito Perro Bar"     , "0261 7212018"));
    }

    public Cliente seleccionarCliente(Scanner scanner) {
        System.out.println("Seleccione un cliente:");

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre() + " - Teléfono: " + clientes.get(i).getTelefono());
        }

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion > 0 && seleccion <= clientes.size()) {
            return clientes.get(seleccion - 1);
        } else {
            System.out.println("Selección no válida.");
            return null;
        }
    }

    // Método para que el admin pueda agregar un nuevo cliente
    public void agregarCliente(Scanner scanner) {
        System.out.println("Ingrese nombre del nuevo cliente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese teléfono del nuevo cliente:");
        String telefono = scanner.nextLine();
        
        Cliente nuevoCliente = new Cliente(nombre, telefono);
        clientes.add(nuevoCliente);

        System.out.println("Cliente agregado exitosamente: " + nombre);
    }
}

