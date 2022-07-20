package consultamonedas;

import java.sql.*;

public class ConsultaMonedas {

    public static void main(String[] args) {
        try {
            //Crear, declarar Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            //Definir las Credenciales
            String servidor = "localhost";
            String baseDatos = "Monedas";
            String usuario = "root";
            String clave = "Ph80164589*";

            //Definir la ruta de acceso a la BD
            String ruta = "jdbc:mysql://" + servidor + ":3306/" + baseDatos;

            //Conectar a la base de Datos
            Connection cn = DriverManager.getConnection(ruta, usuario, clave);

            System.out.println("Conectados a la Base de Datos");

            //Crear la consulta SQL
            Statement sSQL = cn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            String strSQL = "SELECT P.Pais"
                    + " FROM Pais P"
                    + " JOIN Moneda M"
                    + " ON P.IdMoneda = M.Id"
                    + " WHERE M.Sigla = 'EUR'";

            if (sSQL.execute(strSQL)) {
                ResultSet rs = sSQL.getResultSet();

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al consultar: \n[** " + ex.getMessage() + " **]");
        }
    }

}
