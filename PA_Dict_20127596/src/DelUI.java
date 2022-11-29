import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class DelUI extends JFrame implements ActionListener {
    JTextField SlangInput;
    JButton Add;
    JButton cancel;
    JDialog d;
    DelUI(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel content = new JLabel("Delete Slang Word");
        content.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Heading.add(content);
        JPanel Body = new JPanel();
        Body.setLayout(new BoxLayout(Body, BoxLayout.Y_AXIS));
        JPanel Line1 = new JPanel();
        JLabel Text1 = new JLabel("Enter Slang");
        SlangInput = new JTextField(50);
        Line1.add(Text1);
        Line1.add(SlangInput);
        Body.add(Line1);

        JPanel Footer = new JPanel();
        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);
        Add = new JButton("Delete");
        Add.addActionListener(this);
        Footer.add(Add);
        Footer.add(cancel);

        panel.add(Heading);
        panel.add(Body);
        panel.add(Footer);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getActionCommand().equals("Delete") && (DataInteract.isExistSlang(SlangInput.getText()) != true || SlangInput.getText().equals("")) ){
                JFrame msg = new JFrame();
                JOptionPane.showMessageDialog(msg,"slang does not exist");
            } else if (e.getActionCommand().equals("Delete")) {
                Object[] options = { "Yes", "No" };

                int choice = JOptionPane.showOptionDialog(null,
                        "Confirm delete", null,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null,
                        options, options[0]);
                if(choice == 0){
                    JFrame msg = new JFrame();
                    DataInteract.removeSlang(SlangInput.getText());
                    JOptionPane.showMessageDialog(msg,"delete successfully");
                }
                else if(choice == 1){

                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if(e.getActionCommand().equals("Back to menu")){
            this.dispose();
            new UI();
        }
    }
}
