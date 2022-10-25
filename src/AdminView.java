import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
    private JButton viewStudentButton;
    private JButton addStudentButton;
    private JPanel adminPanel;

    private ViewStudent viewStudent;



    public AdminView() {
        this.viewStudent = new ViewStudent();
        viewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JFrame frame =new JFrame("Students");
               frame.setContentPane(viewStudent.getViewStudent());
               frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               frame.pack();
               frame.setVisible(true);
            }
        });


    }

    public JPanel getAdminPanel(){ return adminPanel;}

}
