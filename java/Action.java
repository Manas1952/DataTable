import java.sql.*;

public class Action {
  Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
  private String firstname;
  private String lastname;
  private int age;
  private String profiles = "{ \"data\": [";

  public String getProfiles() {
    return profiles;
  }

  public void setProfiles(String profiles) {
    this.profiles = profiles;
  }

  public Action() throws SQLException {
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String execute() {
    try {
      Statement statement = connection.createStatement();
      if (firstname != null && lastname != null && age != 0) {
//        System.out.println("inserted");
        statement.executeUpdate("insert into profiles values ('" + firstname + "', '" + lastname + "', " + age + ")");
      }
      ResultSet resultSet = statement.executeQuery("select * from profiles");
      int count = 0;
      while (resultSet.next()) {
//        System.out.println("*");

        profiles += ("[\"" + resultSet.getObject(1) + "\", \"" + resultSet.getObject(2) + "\", \"" + resultSet.getObject(3) + "\"],");
//        System.out.println(resultSet.getObject(1) + " " + resultSet.getObject(2) + " " + resultSet.getObject(3));
        count++;
      }
      if (count == 0) {
        profiles = "{\"data\": []}";
      }
      else {
        profiles = profiles.substring(0, profiles.length() - 1);
        profiles += "]}";
      }
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return "index";
  }
}
