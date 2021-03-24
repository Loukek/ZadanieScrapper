import com.mysql.cj.protocol.Resultset;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Document doc1 = null;
        try {
            doc1 = Jsoup.connect("https://megamitensei.fandom.com/wiki/List_of_Persona_5_Characters").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element character1 = doc1.getElementById("gallery-0");
        Elements characters1 = character1.getElementsByClass("lightbox-caption");

        final ArrayList<String> charList = new ArrayList<String>();

        for (int i = 0; i < characters1.size(); i++) {
            String name = characters1.get(i).select("a").text();
            charList.add(name);
        }

        /*System.out.println(charList);*/

        final ArrayList<String> ageList = new ArrayList<String>();

        for (int i = 0; i < charList.size(); i++) {
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

        final ArrayList<String> heightList = new ArrayList<String>();

        for (int i = 0; i < charList.size(); i++) {
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
                heightList.add("175cm");
            } else if (height.equals("Morgana")) {
                heightList.add("60cm");
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
                height1 = height1.replace("[5]", "");
                height1 = height1.replaceAll("\\(.*?\\)", "");
                height1 = height1.replaceAll("\\(.*?\\)", "");
                height1 = height1.replaceAll(" ", "");
                heightList.add(height1);
            }
        }

        final ArrayList<String> arcanaList = new ArrayList<String>();

        for (int i = 0; i < charList.size(); i++) {
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

        final ArrayList<String> weaponList = new ArrayList<String>();

        for (int i = 0; i < charList.size(); i++) {
            String weapon = charList.get(i);
            weapon = weapon.replaceAll(" ", "_");
            if (weapon.equals("Protagonist")) {
                Document doc5 = null;
                try {
                    doc5 = Jsoup.connect("https://megamitensei.fandom.com/wiki/Protagonist_(Persona_5)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements weaponData = doc5.getElementsByAttributeValue("data-source", "ranged");
                String weapon1 = weaponData.first().getElementsByIndexEquals(1).text();
                weaponList.add(weapon1);
            } else if (weapon.equals("Futaba_Sakura")) {
                weaponList.add("No weapons");
            } else {
                Document doc5 = null;
                try {
                    doc5 = Jsoup.connect("https://megamitensei.fandom.com/wiki/" + weapon).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements weaponData = doc5.getElementsByAttributeValue("data-source", "ranged");
                String weapon1 = weaponData.first().getElementsByIndexEquals(1).text();
                weapon1 = weapon1.replace("[3]", "");
                weaponList.add(weapon1);
            }
        }

        /*System.out.println(ageList);
        System.out.println(heightList);
        System.out.println(arcanaList);
        System.out.println(weaponList);*/

        /*class ExcelMain{
            private void createAndSaveExcel() throws IOException {
                Workbook xlsxWorkbook = new XSSFWorkbook();
                Sheet sheet1 = xlsxWorkbook.createSheet("Dane postaci");
                Row row1 = sheet1.createRow(0);
                for(int i = 0; i < charList.size(); i++){
                    String name = charList.get(i);
                    row1.createCell(i+1).setCellValue(name);
                    sheet1.autoSizeColumn(i+1);
                }
                Row row2 = sheet1.createRow(1);
                row2.createCell(0).setCellValue("Age");
                for(int i = 0; i < ageList.size(); i++){
                    String ages = ageList.get(i);
                    row2.createCell(i+1).setCellValue(ages);
                }

                Row row3 = sheet1.createRow(2);
                row3.createCell(0).setCellValue("Height");
                for(int i = 0; i < heightList.size(); i++){
                    String heights = heightList.get(i);
                    row3.createCell(i+1).setCellValue(heights);
                }

                Row row4 = sheet1.createRow(3);
                row4.createCell(0).setCellValue("Arcana");
                for(int i = 0; i < arcanaList.size(); i++){
                    String arcanas = arcanaList.get(i);
                    row4.createCell(i+1).setCellValue(arcanas);
                }

                Row row5 = sheet1.createRow(4);
                row5.createCell(0).setCellValue("Weapon");
                for(int i = 0; i < weaponList.size(); i++){
                    String weapons = weaponList.get(i);
                    row5.createCell(i+1).setCellValue(weapons);
                    sheet1.autoSizeColumn(i+1);
                }

                xlsxWorkbook.write(new FileOutputStream("Character_data.xlsx"));
            }
        }

        ExcelMain excelMain = new ExcelMain();
        excelMain.createAndSaveExcel();*/
        System.out.println("Czas pobrania wszystkich danych: " + LocalTime.now() + "\n");


        Connection conn = DBConnector.connect();


        Scanner userInput = new Scanner(System.in);
        PreparedStatement loginSystem = conn.prepareStatement("SELECT login, password FROM user");
        ResultSet logger = loginSystem.executeQuery();
        while (logger.next()) {
            String login = logger.getString("login");
            String password = logger.getString("password");
            System.out.println("Wpisz login: ");
            if (login.equals(userInput.next())) {
                System.out.println("Wpisz hasło: ");
                if(password.equals(userInput.next())) {
                    System.out.println("Wybierz co chcesz zrobić (Wpisz 1 lub 2): \n 1. Pobierz dane \n 2. Wyświetl dane z bazy");
                    int choice = userInput.nextInt();


                    if (choice == 1) {
                        for (int i = 0; i < charList.size(); i++) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                            LocalDateTime now1 = LocalDateTime.now();
                            String formatDateTime = now1.format(formatter);
                            PreparedStatement stmt = conn.prepareStatement(
                                    "INSERT IGNORE INTO characters " +
                                            "(id, name, age, height, arcana, weapon, time) " +
                                            "VALUES(" + (i+1) +", '" + charList.get(i) + "', '" + ageList.get(i) + "', '" + heightList.get(i) + "' , '" + arcanaList.get(i) + "', '" + weaponList.get(i) + "', '" + formatDateTime + "')");
                            stmt.executeUpdate();
                            LocalDateTime now2 = LocalDateTime.now();
                            String formatDateTime2 = now2.format(formatter);
                            PreparedStatement stmt2 = conn.prepareStatement("UPDATE characters SET time = '" + formatDateTime2 + "' WHERE characters.id = " + i + ";");
                            stmt2.executeUpdate();
                        }
                    } else if (choice == 2) {
                        PreparedStatement stmt = conn.prepareStatement("SELECT id, name, age, height, arcana, weapon, time FROM characters");
                        ResultSet RS = stmt.executeQuery();
                        while (RS.next()) {
                            String id = RS.getString("id");
                            String name = RS.getString("name");
                            String age = RS.getString("age");
                            String height = RS.getString("height");
                            String arcana = RS.getString("arcana");
                            String weapon = RS.getString("weapon");
                            String time = RS.getString("time");

                            System.out.println(id + ":");
                            System.out.println(name + ", " + age + ", " + height + ", " + arcana + ", " + weapon + ", " + time);
                        }
                    } else {
                        System.out.println("Podaj prawidłową liczbę.");
                        return;
                    }
                }else{
                    System.out.println("Błędne hasło!");
                    return;
                }
            }else{
                System.out.println("Błędny login!");
                return;
            }
        }
    }

}
