import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Persona {
    public static void main(String[] args) {
        Document doc1 = null;
        try{
            doc1 = Jsoup.connect("https://megamitensei.fandom.com/wiki/List_of_Persona_5_Characters").get();
        }catch (IOException e){
            e.printStackTrace();
        }
        Element character1 = doc1.getElementById("gallery-0");
        Elements characters1 = character1.getElementsByClass("lightbox-caption");

        ArrayList<String> charList = new ArrayList<String>();

        for(int i = 0; i < characters1.size(); i++){
            String name = characters1.get(i).select("a").text();
            charList.add(name);
        }

        System.out.println(charList);

    }
}
