import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SignUp {
    private JPanel signUpPanel;
    private JTextField emailAddressTF;
    private JTextField userNameTF;
    private JButton submitButton;

    public SignUp() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public JPanel getSignUpPanel(){return signUpPanel;}

}
