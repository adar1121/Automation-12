import java.sql.*;

public class DBTest {
    private static final String USER_NAME = "sql12596771";
    private static final String DATABASE_NAME = "sql12596771";
    private static final String PASSWORD = "cBizhJ8lqM";
    private static final String PORT = "3306";
    private static final String SERVER = "sql12.freemysqlhosting.net";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

        createTable(con);
        insertDog(con,"koko",10,"pudel");
        insertDog(con, "dodo",11,"Hamstaff");
        insertDog(con,"popo",12,"chiwawa");
        setDogAge(con,30,11);
        deleteDogByName(con,"popo");
        getTableContentDog(con);
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dog`(`name` VARCHAR(40) NOT NULL,`age` INT NOT NULL, `breed` VARCHAR(30));"; // WORKS
        con.createStatement().execute(statementToExecute);
    }
    private static void getTableContentDog(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dog;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){
            //Retrieve by column name
            String name = rs.getString("name");
            int age  = rs.getInt("age");
            String breed = rs.getString("breed");

            //Display values
            System.out.print("Name:" + name);
            System.out.print(", age:" + age);
            System.out.println(", breed:" + breed);
        }
        rs.close();
    }

    private static void insertDog(Connection con, String name, int age,String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dog (`name`,`age`,`breed`) VALUES ('" + name + "', '" + age + "', '" + breed +"');";
        con.createStatement().execute(statementToExecute);
    }

    private static void deleteDogByName(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dog` WHERE `name`='"+name+"';";
        con.createStatement().execute(statementToExecute);
    }
    private static void setDogAge(Connection con,int newAge, int preAge)throws SQLException{
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dog` SET `age`='"+newAge+"' WHERE `age`='"+preAge+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}
