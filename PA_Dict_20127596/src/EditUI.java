import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class EditUI extends JFrame implements ActionListener {
    JTextField SlangInput;
    JButton Add;
    JButton cancel;
    JDialog d;
    EditUI(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel content = new JLabel("Edit Slang Word");
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
        Add = new JButton("Edit");
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
            if(e.getActionCommand().equals("Edit") && (DataInteract.isExistSlang(SlangInput.getText()) != true || SlangInput.getText().equals("")) ){
                JFrame msg = new JFrame();
                JOptionPane.showMessageDialog(msg,"slang does not exist");
             } else if (e.getActionCommand().equals("Edit")) {
//                Object[] options = { "Slang", "Definition" };
//                JFrame frame = new JFrame();
//                JPanel panel = new JPanel();
                JTextField textField = new JTextField(20);
                JButton slang = new JButton("Slang");
                JButton Def = new JButton("Definition");
//                panel.add(textField);
//                frame.add(panel);
//                frame.setBounds(300, 300, 500, 350);
//                frame.setVisible(true);
                Object[] options = { textField,"Slang", "Definition" };

                int choice = JOptionPane.showOptionDialog(null,
                        "Enter content and then choose edit option", null,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null,
                        options, options[0]);
                if(choice == 1){
                    if(textField.getText().equals("")){
                        JFrame t = new JFrame();
                        JOptionPane.showMessageDialog(t,"Empty content!!!!");
                    }
                    else if(DataInteract.isExistSlang(textField.getText())){
                        JFrame t = new JFrame();
                        JOptionPane.showMessageDialog(t,"Slang existed, cannot overwrite");
                    }
                    else if(textField.getText().equals("") != true){
                        DataInteract.editSlang(SlangInput.getText(),textField.getText());
                        JFrame t = new JFrame();
                        JOptionPane.showMessageDialog(t,"Edit slang successfully");
                    }
                }
                if(choice == 2){
                    if(textField.getText().equals("")){
                        JFrame t = new JFrame();
                        JOptionPane.showMessageDialog(t,"Empty content!!!!");
                    }
                    else{
                        DataInteract.overwriteSlang(SlangInput.getText(),textField.getText());
                        JFrame t = new JFrame();
                        JOptionPane.showMessageDialog(t,"Edit slang successfully");
                    }
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
