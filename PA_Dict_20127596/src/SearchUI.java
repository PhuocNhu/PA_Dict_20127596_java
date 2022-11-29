import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class SearchUI extends JFrame implements ActionListener {
    static JTextField input;
    JButton click;
    JComboBox<String> choice;
    JTable table;
    DefaultTableModel tableModel;
    JButton cancel;
    SearchUI(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel content = new JLabel("SEARCH");
        content.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Heading.add(content);
        JPanel Body = new JPanel();
        input = new JTextField("Input here",50);
        String[] option = {"Search by Slang", "Search by Definition"};
        choice = new JComboBox<String>(option);
        click = new JButton("Search");
        click.addActionListener(this);
        String[] col = {"Slang Word","Definition"};
        tableModel = new DefaultTableModel(col,0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);

        Body.add(choice);
        Body.add(input);
        Body.add(click);

        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);

        panel.add(Heading);
        panel.add(Body);
        panel.add(scroll);
        panel.add(cancel);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Search") && choice.getSelectedIndex() == 0){
            ArrayList<String> slang = new ArrayList<>();
            ArrayList<String> def = new ArrayList<>();
            String keyword = input.getText();
            HashMap<String,ArrayList<String>> a = null;
            try {
                a = Search.SearchBySlang(keyword);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(a.isEmpty()){
                JFrame t = new JFrame();
                JOptionPane.showMessageDialog(t,"Slang does not exists");
            }
            else {
                for (Map.Entry<String, ArrayList<String>> i : a.entrySet()) {
                    ArrayList<String> value = i.getValue();
                    ArrayList<String> tmp = new ArrayList<>(Collections.nCopies(value.size(), i.getKey()));
                    slang.addAll(tmp);
                    def.addAll(value);
//                Vector<String> row = new Vector<>();
//                row.add(keyword);
//                row.add(i);
//                tableModel.addRow(row);
                }
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                tableModel.addColumn("Slang Word", slang.toArray());
                tableModel.addColumn("Definition", def.toArray());
            }
        }
        if(e.getActionCommand().equals("Search") && choice.getSelectedIndex() == 1){
            String keyword = input.getText();
            HashMap<String,ArrayList<String>> a = null;
            try {
                a = Search.SearchByDef(keyword);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(a.isEmpty()){
                JFrame t = new JFrame();
                JOptionPane.showMessageDialog(t,"Definition does not exists");
            }
            else {
                ArrayList<String> slang = new ArrayList<>();
                ArrayList<String> def = new ArrayList<>();
                for (Map.Entry<String, ArrayList<String>> i : a.entrySet()) {
                    ArrayList<String> value = i.getValue();
                    ArrayList<String> tmp = new ArrayList<>(Collections.nCopies(value.size(), i.getKey()));
                    slang.addAll(tmp);
                    def.addAll(value);
//                Vector<String> row = new Vector<>();
//                row.add(keyword);
//                row.add(i);
//                tableModel.addRow(row);
                }
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                tableModel.addColumn("Slang Word", slang.toArray());
                tableModel.addColumn("Definition", def.toArray());
            }
        }
        if(e.getActionCommand().equals("Back to menu")){
            this.dispose();
            new UI();
        }
    }
}