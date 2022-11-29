import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileInteract {
    public static HashMap<String, ArrayList<String>> readFile(String FileName) throws IOException {
        HashMap<String, ArrayList<String>> ans = new HashMap<String, ArrayList<String>>();
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            ArrayList<String> defResult = new ArrayList<String>();
            String temp = br.readLine();
            if (temp == null) break;
            if (temp.indexOf("`") == -1) temp = br.readLine();
            String[] result = temp.split("`");
            String[] definition = result[1].split("\\| ");
            for (String i : definition) {
                defResult.add(i);
            }
            ans.put(result[0], defResult);
        }
        fr.close();
        return ans;
    }

    public static void writeFile(String FileName, HashMap<String, ArrayList<String>> a) throws IOException {
        FileWriter fr = new FileWriter(FileName);
        BufferedWriter br = new BufferedWriter(fr);
        for (Map.Entry<String, ArrayList<String>> i : a.entrySet()) {
            String line = new String();
            line += i.getKey() + "`";
            for (int j = 0; j < i.getValue().size(); j++) {
                if (j < i.getValue().size() - 1)
                    line += i.getValue().get(j) + "| ";
                else line += i.getValue().get(j);
            }
            br.write(line);
            br.newLine();
        }
        br.flush();
        br.close();
    }

    public static HashMap<LocalDateTime, ArrayList<String>> readHistory(String FileName) throws IOException {
        HashMap<LocalDateTime, ArrayList<String>> ans = new HashMap<>();
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            ArrayList<String> slangResult = new ArrayList<>();
            String line = br.readLine();
            if (line == null) break;
            String[] result = line.split("`");
            String[] slang = result[1].split("\\| ");
            for (String i : slang) slangResult.add(i);
            LocalDateTime reverse = LocalDateTime.parse(result[0]);
            ans.put(reverse, slangResult);
        }
        fr.close();
        return ans;
    }

    public static void writeHistory(String FileName, HashMap<LocalDateTime, ArrayList<String>> a) throws IOException {
        FileWriter fr = new FileWriter(FileName);
        BufferedWriter br = new BufferedWriter(fr);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        for (Map.Entry<LocalDateTime, ArrayList<String>> i : a.entrySet()) {
            String line = new String();
            if (i.getKey() != null) {
                String datetime = i.getKey().format(formatter);
                line += datetime + "`";
                for (int j = 0; j < i.getValue().size(); j++) {
                    if (j < i.getValue().size() - 1)
                        line += i.getValue().get(j) + "| ";
                    else line += i.getValue().get(j);
                }
                br.write(line);
                br.newLine();
            }
        }
        br.flush();
        br.close();
    }
}
