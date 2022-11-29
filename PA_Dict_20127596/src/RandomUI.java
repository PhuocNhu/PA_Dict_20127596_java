import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RandomUI extends JFrame implements ActionListener {
    JTextField Slang;
    JButton Random;
    JButton cancel;
    JLabel content1;
    JLabel content2;
    JPanel Heading;
    JPanel Body;
    HashMap<String, ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
    RandomUI() throws IOException {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        Heading = new JPanel(new GridBagLayout());
        String key = DataInteract.randomSlang();
        content1 = new JLabel("Random Slang Word: " + key );
        content1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));

        Heading.add(content1);

        Body = new JPanel(new GridBagLayout());
        ArrayList<String> value = a.get(key);
        String def = new String();
        for (int i = 0; i < value.size(); i++) {
            def += value.get(i);
            if(i != value.size() - 1) def += " | ";
        }
        content2 = new JLabel("Definition:  " + def );
        content2.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Body.add(content2);

        JPanel Footer = new JPanel();
        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);
        Random = new JButton("Random");
        Random.addActionListener(this);
        Footer.add(Random);
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
        if (e.getActionCommand().equals("Random")) {
            try {
                String key = DataInteract.randomSlang();
                content1.setText("Random Slang Word: " + key );
                ArrayList<String> value = a.get(key);
                String def = new String();
                for (int i = 0; i < value.size(); i++) {
                    def += value.get(i);
                    if(i != value.size() - 1) def += " | ";
                }
                content2.setText("Definition:  " + def );
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
