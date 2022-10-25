import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentManagement {
    private JTextField idTF;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField majorTF;

    private JButton deleteButton;
    private JButton saveButton;
    private JButton findButton;
    private JPanel StudentManagement;
    private JButton addButton;

    private String password = "Lawrence2$";

    private String root = "root";

    private String url = "jdbc:mysql://localhost:3306/students";

    public JPanel getPanel(){return StudentManagement;}

    public StudentManagement() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.valueOf(idTF.getText());
                    Connection connection = DriverManager.getConnection(url, root, password);
                    String sql = "Delete from studentinfo where Id = " + id;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    int result = preparedStatement.executeUpdate();
                    int k = preparedStatement.executeUpdate(sql);
                    System.out.println(k);
                    if (k == 0){
                        JOptionPane.showMessageDialog(null, "Record Deleted");
                        idTF.setText("");
                        firstNameTF.setText("");
                        lastNameTF.setText("");
                        majorTF.setText("");
                    }
                }catch (Throwable ex){
                    ex.printStackTrace();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.valueOf(idTF.getText());
                    Connection connection = DriverManager.getConnection(url, root, password);
                    PreparedStatement preparedStatement = connection.prepareStatement("Select * from studentinfo where Id = ?");
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        preparedStatement = connection.prepareStatement("UPDATE studentinfo set lastName = ?, firstName = ?, major = ? WHERE Id = ?");
                        preparedStatement.setString(1, lastNameTF.getText());
                        preparedStatement.setString(2, firstNameTF.getText());
                        preparedStatement.setString(3, majorTF.getText());
                        preparedStatement.setString(4, idTF.getText());
                    }else{
                        preparedStatement = connection.prepareStatement("insert into studentinfo values (?,?,?,?)");
                        preparedStatement.setString(1, idTF.getText());
                        preparedStatement.setString(2, lastNameTF.getText());
                        preparedStatement.setString(3, firstNameTF.getText());
                        preparedStatement.setString(4, majorTF.getText());

                    }
                        preparedStatement.execute();
                    int k = preparedStatement.executeUpdate();
                    if (k == 1){
                        JOptionPane.showMessageDialog(null, "Record Updated");
                        idTF.setText("");
                        firstNameTF.setText("");
                        lastNameTF.setText("");
                        majorTF.setText("");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                }catch (Throwable ex){
                    ex.printStackTrace();
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.valueOf(idTF.getText());
                    Connection connection = DriverManager.getConnection(url, root, password);
                    String sql = "SELECT * from studentinfo where Id = " + id;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet result = preparedStatement.executeQuery();
                    if(result.next()){
                        firstNameTF.setText(result.getString(3));
                        lastNameTF.setText(result.getString(2));
                        majorTF.setText(result.getString(4));
                    }else{
                        JOptionPane.showMessageDialog(null, "No Record Found");
                    }
                    result.close();
                    preparedStatement.close();
                    connection.close();
                }catch (Throwable ex){
                    ex.printStackTrace();
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection(url, root, password);
                    String id = idTF.getText();
                    String firstName = firstNameTF.getText();
                    String lastName = lastNameTF.getText();
                    String major = majorTF.getText();
                    if (firstNameTF.getText().isEmpty() || lastNameTF.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, " Fields cannot be empty");
                    } else {
                        PreparedStatement pst;
                        pst = connection.prepareStatement("INSERT INTO studentinfo VALUES (?,?,?,?)");
                        pst.setString(1, id);
                        pst.setString(2, lastName);
                        pst.setString(3, firstName);
                        pst.setString(4, major);
                        int k = pst.executeUpdate();
                        if (k == 1) {
                            JOptionPane.showMessageDialog(null, "Success");
                            idTF.setText("");
                            firstNameTF.setText("");
                            lastNameTF.setText("");
                            majorTF.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Save Failed");
                        }
                        pst.close();
                        connection.close();
                    }
                }catch(Exception error){
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, "There is already a record");
                }

            }
        });
    }
}
