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

        ArrayList<String> ageList = new ArrayList<String>();

        for(int i = 0; i < charList.size(); i++) {
            String age = charList.get(i);
            age = age.replaceAll(" ", "_");
            if (age.equals("Protagonist")) {
                Document doc2 = null;
                try {
                    doc2 = Jsoup.connect("https://megamitensei.fandom.com/wiki/Protagonist_(Persona_5)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements ageData = doc2.getElementsByAttributeValue("data-source", "age");
                String age1 = ageData.first().getElementsByIndexEquals(1).text();
                ageList.add(age1);
            } else if (age.equals("Morgana")) {
                ageList.add("Unknown");
            } else {
                Document doc2 = null;
                try {
                    doc2 = Jsoup.connect("https://megamitensei.fandom.com/wiki/" + age).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements ageData = doc2.getElementsByAttributeValue("data-source", "age");
                String age1 = ageData.first().getElementsByIndexEquals(1).text();
                age1 = age1.replace(" (P5) 16 ", "");
                age1 = age1.replace(" (P5) 17 ", "");
                age1 = age1.replace(" (P5) 18 ", "");
                age1 = age1.replace(" (P5) 19 ", "");
                age1 = age1.replace("(P5S) ", "");
                ageList.add(age1);
            }
        }

        ArrayList<String> heightList = new ArrayList<String>();

        for(int i = 0; i < charList.size(); i++) {
            String height = charList.get(i);
            height = height.replaceAll(" ", "_");
            if (height.equals("Protagonist")) {
                Document doc3 = null;
                try {
                    doc3 = Jsoup.connect("https://megamitensei.fandom.com/wiki/Protagonist_(Persona_5)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements heightData = doc3.getElementsByAttributeValue("data-source", "height");
                String height1 = heightData.first().getElementsByIndexEquals(1).text();
                heightList.add(height1);
            } else if (height.equals("Morgana")) {
                heightList.add("60 cm(2')");
            } else {
                Document doc3 = null;
                try {
                    doc3 = Jsoup.connect("https://megamitensei.fandom.com/wiki/" + height).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements heightData = doc3.getElementsByAttributeValue("data-source", "height");
                String height1 = heightData.first().getElementsByIndexEquals(1).text();
                height1 = height1.replace("[1]", "");
                height1 = height1.replace("[4]", "");
                height1 = height1.replace("[5]", "");
                heightList.add(height1);
            }
        }

        ArrayList<String> arcanaList = new ArrayList<String>();

        for(int i = 0; i < charList.size(); i++) {
            String arcana = charList.get(i);
            arcana = arcana.replaceAll(" ", "_");
            if (arcana.equals("Protagonist")) {
                Document doc4 = null;
                try {
                    doc4 = Jsoup.connect("https://megamitensei.fandom.com/wiki/Protagonist_(Persona_5)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements arcanaData = doc4.getElementsByAttributeValue("data-source", "arcana");
                String arcana1 = arcanaData.first().getElementsByIndexEquals(1).text();
                arcana1 = arcana1.replace("(P5R) ", "");
                arcanaList.add(arcana1);
            } else if (arcana.equals("Morgana")) {
                arcanaList.add("Magician");
            } else {
                Document doc4 = null;
                try {
                    doc4 = Jsoup.connect("https://megamitensei.fandom.com/wiki/" + arcana).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements arcanaData = doc4.getElementsByAttributeValue("data-source", "arcana");
                String arcana1 = arcanaData.first().getElementsByIndexEquals(1).text();
                arcanaList.add(arcana1);
            }
        }

        System.out.println(ageList);
        System.out.println(heightList);
        System.out.println(arcanaList);


    }
}
