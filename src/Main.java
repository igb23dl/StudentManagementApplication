import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    private JPanel Main;
    private JTextField usernameTF;
    private JTextField passwordTF;
    private JButton forgotButton;
    private JButton signInButton;
    private AdminView adminView;
    private SignUp signUp;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application onclick of x button
        frame.setSize(10, 10); // sets size with x and y dimensions
        frame.pack();
        frame.setVisible(true); // makes frame visible
    }

    public Main() {
       this.signUp = new SignUp();
        forgotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame("Forgot Password");
                newFrame.setContentPane(signUp.getSignUpPanel());
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
                newFrame.pack();
                newFrame.setVisible(true);
            }
        });
        this.adminView = new AdminView();
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/students";
                String username = "root";
                String password = "Lawrence2$";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url, username, password);

                    Statement statement = connection.createStatement();

           ResultSet resultSet = statement.executeQuery("select * from admin");
           while (resultSet.next()) {
               String usernameField = resultSet.getString(1);
               String passwordField = resultSet.getString(2);
               if (usernameTF.getText().equals(usernameField) && passwordTF.getText().equals(passwordField)){
                   usernameTF.setText("");
                   passwordTF.setText("");
                   JFrame newFrame = new JFrame("Admin View");
                   newFrame.setContentPane(adminView.getAdminPanel());
                   newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   newFrame.setSize(2000, 3000);
                   newFrame.pack();
                   newFrame.setVisible(true);
               }
                else {
                   JOptionPane.showMessageDialog(null, "Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }        statement.close();
                    connection.close();
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });

    }


}
