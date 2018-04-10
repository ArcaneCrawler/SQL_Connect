import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CheekyBreeky {

    // Ввод расположения базы данных (В конце пишется её имя)
    private static final String url = "jdbc:mysql://localhost:3306/isat";
    // Ввод логина и пароля
    private static final String user = "root";
    private static final String password = "root";

    // JDBC переменные для создания соединения
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static int id;

    //Создания массива переменных
    public static void main(String args[]) {
        String[] values = new String[10];
        //Создание первого запроса - получение ID материала (название должно браться из ComboBox)
        String materialName = "'Material 1'";
        String getID = "Select Material_ID from material where Material_name = " + materialName;

        int i = 0;
        //Основная часть

        try {
            //Создание подключения
            con = DriverManager.getConnection(url, user, password);

            //Создание выражений для выполнения запросов
            stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            rs = stmt.executeQuery(getID);


            while (rs.next()) {
                id = rs.getInt(1);
            }

            //Второй запрос - получение значений переменных
            String getvalue = "Select Parameter_value from parameter_value WHERE Material_id = '" + Integer.toString(id) + "'";
            ResultSet ps = stmt2.executeQuery(getvalue);
            while (ps.next()) {


                String Density = ps.getString(1);
                values[i] = Density;
                i = i + 1;

            }

            System.out.printf("Density: " + values[0] + "\n");
            System.out.printf("Capacity: " + values[1] + "\n");
            System.out.printf("Melting: " + values[2] + "\n");
            System.out.printf("Cover_speed: " + values[3] + "\n");
            System.out.printf("Cover_temp: " + values[4] + "\n");
            System.out.printf("Consis: " + values[5] + "\n");
            System.out.printf("Viscosity: " + values[6] + "\n");
            System.out.printf("Temp: " + values[7] + "\n");
            System.out.printf("Flow: " + values[8] + "\n");
            System.out.printf("Heat_trans: " + values[9] + "\n");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

}