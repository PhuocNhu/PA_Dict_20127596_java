import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddUI extends JFrame implements ActionListener {
    JTextField SlangInput;
    JTextField DefInput;
    JButton Add;
    JButton cancel;
    AddUI(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel content = new JLabel("Add Slang Word");
        content.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Heading.add(content);
        JPanel Body = new JPanel();
        Body.setLayout(new BoxLayout(Body, BoxLayout.Y_AXIS));
        JPanel Line1 = new JPanel();
        JLabel Text1 = new JLabel("New Slang      ");
        SlangInput = new JTextField(50);
        Line1.add(Text1);
        Line1.add(SlangInput);

        JPanel Line2 = new JPanel();
        JLabel Text2 = new JLabel("New Definition");
        DefInput = new JTextField(50);
        Line2.add(Text2);
        Line2.add(DefInput);

        Body.add(Line1);
        Body.add(Line2);

        JPanel Footer = new JPanel();
        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);
        Add = new JButton("Add");
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
        if(e.getActionCommand().equals("Add") && (SlangInput.getText().equals("") || DefInput.getText().equals(""))){
            JFrame message = new JFrame();
            JOptionPane.showMessageDialog(message,"Input Required");
        }
        else {
            try {
                if(e.getActionCommand().equals("Add") && DataInteract.isExistSlang(SlangInput.getText()) != true){
                    String s = SlangInput.getText();
                    String d = DefInput.getText();
                    try {
                        DataInteract.addSlang(s,d);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JFrame message = new JFrame();
                    JOptionPane.showMessageDialog(message,"Add Successfully");
                }
                else if(e.getActionCommand().equals("Add")){
                    String[] opt = {"Overwrite","Duplicate"};
                    var select = JOptionPane.showOptionDialog(null,"slang already exists, select option:",
                            "",0,2,null,opt,opt[0]);
                    if(select == 0){
                        DataInteract.overwriteSlang(SlangInput.getText(),DefInput.getText());
                        JFrame msg = new JFrame();
                        JOptionPane.showMessageDialog(msg,"Overwrite successfully");
                    }
                    if(select == 1){
                        DataInteract.duplicateSlang(SlangInput.getText(),DefInput.getText());
                        JFrame msg = new JFrame();
                        JOptionPane.showMessageDialog(msg,"Duplicate successfully");
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("Back to menu")){
            this.dispose();
            new UI();
        }
    }
}
