import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewStudent {


    private JTable table;
    private JPanel viewStudent;
    private JButton editStudentButton;

    private StudentManagement studentManagement;

    public ViewStudent(){

        showTable();
        this.studentManagement = new StudentManagement();
        editStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Manage Students");
                frame.setContentPane(studentManagement.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }


    private void showTable(){
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "Lawrence2$";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * from studentinfo ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            String[] columns = {"Id","First Name", "Last Name", "Major"};
            DefaultTableModel tableModel = new DefaultTableModel(columns,0);
            while(resultSet.next()){
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(2);
                String id = resultSet.getString(1);
                String major = resultSet.getString(4);
                String data[] = {id, firstName, lastName, major};
                tableModel.addRow(data);
            }
            table.setModel(tableModel);

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public JPanel getViewStudent(){return viewStudent;}
}
