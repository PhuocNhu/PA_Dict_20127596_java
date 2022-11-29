import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResetUI {
    ResetUI() throws IOException {
        HashMap<String, ArrayList<String>> a = FileInteract.readFile("data/original.txt");
        FileInteract.writeFile("data/slang.txt",a);
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,"Reset Successfully");
    }
}
