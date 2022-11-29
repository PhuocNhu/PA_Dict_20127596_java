import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataInteract {
    public static void addSlang(String slang, String definition) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        ArrayList<String> t = new ArrayList<>();
        t.add(definition);
        a.put(slang,t);
        FileInteract.writeFile("data/slang.txt",a);
    }

    public static void overwriteSlang(String slang, String definition) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        ArrayList<String> t = new ArrayList<>();
        t.add(definition);
        a.replace(slang,t);
        FileInteract.writeFile("data/slang.txt",a);
    }

    public static void duplicateSlang(String slang, String definition) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        for(Map.Entry<String,ArrayList<String>> i : a.entrySet()){
            if(i.getKey().equals(slang)){
                i.getValue().add(definition);
            }
        }
        FileInteract.writeFile("data/slang.txt",a);
    }
    public static boolean isExistSlang(String slang) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        for(Map.Entry<String,ArrayList<String>> i : a.entrySet()){
            if(i.getKey().equals(slang)) return true;
        }
        return false;
    }

    public static void editSlang(String o, String n) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        ArrayList<String> t = a.get(o);
        a.remove(o);
        a.put(n,t);
        FileInteract.writeFile("data/slang.txt",a);
    }

    public static void removeSlang(String slang) throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        a.remove(slang);
        FileInteract.writeFile("data/slang.txt",a);
    }

    public static String randomSlang() throws IOException {
        HashMap<String,ArrayList<String>> a = FileInteract.readFile("data/slang.txt");
        Object[] crunchifyKeys = a.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        return key.toString();
    }

    public static void sortHistory(HashMap<LocalDateTime, ArrayList<String>> history,ArrayList<String> slang, ArrayList<String> datetime){
        ArrayList<LocalDateTime> sortedKey = new ArrayList<LocalDateTime>(history.keySet());
        Collections.sort(sortedKey);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");;
        for(LocalDateTime i : sortedKey){
//            slang.add(i.format(formatter));
            ArrayList<String> value = history.get(i);
            ArrayList<String> tmp = new ArrayList<>(Collections.nCopies(value.size(), i.format(formatter)));
            slang.addAll(value);
            datetime.addAll(tmp);
        }
    }


}


