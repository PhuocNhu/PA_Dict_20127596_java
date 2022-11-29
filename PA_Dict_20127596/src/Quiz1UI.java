import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Quiz1UI extends JFrame implements ActionListener {
    JLabel head;
    JButton cancel;
    HashMap<String, ArrayList<String>> a = FileInteract.readFile("data/slang.txt");

    Quiz1UI() throws IOException {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel heading = new JPanel();
        String key = DataInteract.randomSlang();
        head = new JLabel("Choose right definition   " + key);
        heading.add(head);

        JPanel body = new JPanel(new GridLayout(2,2));
        ArrayList<String> def = new ArrayList();
        def.add(a.get(key).get(0));
        for (int i = 1; i < 4; i++) {
            def.add(a.get(DataInteract.randomSlang()).get(0));
        }
        JButton[] buttons = new JButton[4];
        Collections.shuffle(def);
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton(def.get(i));
            if(def.get(i).equals(a.get(key).get(0))) buttons[i].setActionCommand("true");
            body.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        JPanel footer = new JPanel();
        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);
        footer.add(cancel);

        panel.add(heading);
        panel.add(body);
        panel.add(footer);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back to menu")){
            this.dispose();
            new UI();
            return;
        }
        if(e.getActionCommand().equals("true")){
            JFrame msg = new JFrame();
            JOptionPane.showMessageDialog(msg,"Congratulations!!!");
            this.dispose();
            try {
                new Quiz1UI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else{
            JFrame msg = new JFrame();
            JOptionPane.showMessageDialog(msg,"Wrong Answer!!!");
            this.dispose();
            try {
                new Quiz1UI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
