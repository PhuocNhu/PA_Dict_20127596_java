import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search {
    static HashMap<String, ArrayList<String>> a;

    static {
        try {
            a = FileInteract.readFile("data/slang.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static HashMap<LocalDateTime, ArrayList<String>> h;

    static {
        try {
            h = FileInteract.readHistory("data/history.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static HashMap<String,ArrayList<String>> SearchBySlang( String slang) throws IOException {
        a = FileInteract.readFile("data/slang.txt");
        HashMap<String,ArrayList<String>> result = new HashMap<>();
        LocalDateTime time = null;
        for(Map.Entry<String, ArrayList<String>> i : a.entrySet()){
            if(i.getKey().toLowerCase().indexOf(slang.toLowerCase()) != -1){
                result.put(i.getKey(),i.getValue());
                time = LocalDateTime.now();
            }
        }
        ArrayList<String> slangResult = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> i : result.entrySet()){
            slangResult.add(i.getKey());
        }
        if(time != null) {
            h.put(time, slangResult);
            FileInteract.writeHistory("data/history.txt", h);
        }
        return result;
    }

//    public static void SearchBySlang( ArrayList<String> history, String slang){
//        for(Map.Entry<String, ArrayList<String>> i : a.entrySet()){
//            if(i.getKey().toLowerCase().indexOf(slang.toLowerCase()) != -1){
//                System.out.println(i.getValue());
//                LocalDateTime now = LocalDateTime.now();
//                history.add(i.getKey() + " " + now.format(dtf).toString());
//            }
//        }
//    }
    public static HashMap<String,ArrayList<String>> SearchByDef(String definition) throws IOException {
        a = FileInteract.readFile("data/slang.txt");
        HashMap<String,ArrayList<String>> result = new HashMap<>();
        LocalDateTime time = null;
        for(Map.Entry<String, ArrayList<String>> i : a.entrySet()){
            for(String j : i.getValue()){
                if(j.toLowerCase().indexOf(definition.toLowerCase()) != -1){
                    result.put(i.getKey(),i.getValue());
                    time = LocalDateTime.now();
                    break;
                }
            }
        }
        ArrayList<String> slangResult = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> i : result.entrySet()){
            slangResult.add(i.getKey());
        }
        if(time != null) {
            h.put(time, slangResult);
            FileInteract.writeHistory("data/history.txt", h);
        }
        return result;
    }

//    public static void SearchByDef(ArrayList<String> history, String definition){
//        for(Map.Entry<String, ArrayList<String>> i : a.entrySet()){
//            for(String j : i.getValue()){
//                if(j.toLowerCase().indexOf(definition.toLowerCase()) != -1){
//                    System.out.println(i.getKey());
//                    LocalDateTime now = LocalDateTime.now();
//                    history.add(i.getKey() + " " + now);
//                    break;
//                }
//            }
//        }
//    }


}
