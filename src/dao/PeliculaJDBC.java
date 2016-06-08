
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pelicula;

/**
 *
 * @author usu21
 */
public class PeliculaJDBC {
    
    private Connection conexion;
    
    
    public boolean insertarPelicula(Pelicula pelicula) {
      conectar();
      if (conexion != null) {
          boolean ok = false;
          try {
              String insert = "insert into pelicula values(?,?,?,?,?,?)";
              PreparedStatement ps = conexion.prepareStatement(insert);
              ps.setString(1, pelicula.getCodigo());
              ps.setString(2, pelicula.getTitulo());
              ps.setInt(3, pelicula.getDuracion());
              ps.setString(4, pelicula.getGenero());
              ps.setInt(5, pelicula.getValoracion());
              ps.setBoolean(6, pelicula.isVisto());
              ps.executeUpdate();
              ps.close();
              ok = true;
              return true;
          } catch (SQLException ex) {
              System.out.println("Error al insertar: " + ex.getMessage());
              return false;
          } finally {
              desconectar();
              return ok;
          }
//          return ok;
      } else {
          return false;
      }
    }
    
    private void conectar() {
        try {
            String url = "jdbc.mysql: //localhost:3306/videoclub";
            String usr = "root";
            String password = "root";
            conexion = DriverManager.getConnection(url, usr, password);
        } catch (SQLException ex) {
            System.out.println("Error al conectar" + ex.getMessage());
        }
    }
    
    private void desconectar() {
    try {
        conexion.close();
} catch (SQLException ex) {
        System.out.println("Error al desconectar" + ex.getMessage());
        conexion = null;
}
}
}