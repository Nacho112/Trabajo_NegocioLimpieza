package negocio.services;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private Map<String, String> usuarios;
    private Map<String, String> roles; // Guardamos los roles de cada usuario

    public LoginService() {
        usuarios = new HashMap<>();
        roles = new HashMap<>();

        // Usuario admin con rol "admin"
        usuarios.put("admin", "admin123");
        roles.put("admin", "ADMIN");

        // Usuario empleado con rol "empleado"
        usuarios.put("empleado", "empleado123");
        roles.put("empleado", "EMPLEADO");
    }

    public boolean login(String username, String password) {
        return usuarios.containsKey(username) && usuarios.get(username).equals(password);
    }

    public String getRol(String username) {
        return roles.get(username); // Retorna el rol del usuario (admin o empleado)
    }
}

