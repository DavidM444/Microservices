package kafka.producer.Entity;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String nombre;
    private String email;
    private String edad;

    public User(UUID id ,String nombre, String email, String edad){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}