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

        for(int i = 0; i < charList.size(); i++) {
            String age = charList.get(i);
            age = age.replaceAll(" ", "_");
            if (age.equals("Protagonist")) {
                System.out.println(age);
                Document doc2 = null;
                try {
                    doc2 = Jsoup.connect("https://megamitensei.fandom.com/wiki/Protagonist_(Persona_5)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements ageData = doc2.getElementsByAttributeValue("data-source", "age");
                System.out.println(ageData.first().getElementsByIndexEquals(1).text());
            } else if (age.equals("Morgana")) {
                System.out.println(age);
                System.out.println("Unknown");
            } else {
                System.out.println(age);
                Document doc2 = null;
                try {
                    doc2 = Jsoup.connect("https://megamitensei.fandom.com/wiki/" + age).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements ageData = doc2.getElementsByAttributeValue("data-source", "age");
                System.out.println(ageData.first().getElementsByIndexEquals(1).text());
            }
        }
    }
}
