package P1;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLVerbinding {
    public static void main(String[] args) {
        String Password = "Pass.123%25";
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=" + Password + "";
        String user = "postgres";


        try {
            Connection Mycon = DriverManager.getConnection(url, user, Password);
            Statement mystmt = Mycon.createStatement();
            ResultSet myRs = mystmt.executeQuery("select * from reiziger");
            while (myRs.next()){
                System.out.println(myRs.getString("voorletters") + " " +myRs.getString("achternaam"));
            }


        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
