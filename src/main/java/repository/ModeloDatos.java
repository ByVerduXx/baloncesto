package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import model.Jugador;

@Slf4j
public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    private static final String ERROR_LOG = "El error es:";

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado
            log.error("No se ha podido conectar");
            log.error(ERROR_LOG + e.getMessage());
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            // No lee de la tabla
            log.error("No lee de la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
        return (existe);
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            log.error("No modifica la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No inserta en la tabla
            log.error("No inserta en la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void resetVotos() {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=0");
            rs.close();
            set.close();
        } catch (Exception e) {
            log.error("No modifica la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
    }

    public Integer getVotos(String nombre) {
        Integer votos = 0;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT votos FROM Jugadores WHERE nombre " + " LIKE '%" + nombre + "%'");
            while (rs.next()) {
                votos = rs.getInt("votos");
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            log.error("No lee de la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
        return votos;
    }

    public List<Jugador> getJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                jugadores.add(Jugador.builder().id(rs.getInt("id")).nombre(rs.getString("nombre"))
                        .votos(rs.getInt("votos")).build());
            }
            rs.close();
            set.close();

        } catch (Exception e) {
            log.error("No lee de la tabla");
            log.error(ERROR_LOG + e.getMessage());
        }
        return jugadores;
    }

}
