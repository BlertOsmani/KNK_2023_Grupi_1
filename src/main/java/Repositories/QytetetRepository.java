package Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QytetetRepository {
    public static List<String> getQytetet() throws SQLException {
        List<String> qytetetList = new ArrayList<>();

        // Create QytetetModel objects and add them to the list
        qytetetList.add(new String("Prishtine"));
        qytetetList.add(new String("Prizren"));
        qytetetList.add(new String("Ferizaj"));
        qytetetList.add(new String("Peje"));
        qytetetList.add(new String("Gjakove"));
        qytetetList.add(new String("Gjilan"));
        qytetetList.add(new String("Mitrovice"));
        qytetetList.add(new String("Podujeve"));
        qytetetList.add(new String("Vushtrri"));
        qytetetList.add(new String("Suhareke"));
        qytetetList.add(new String("Rahovec"));
        qytetetList.add(new String("Drenas"));
        qytetetList.add(new String("Lipjan"));
        qytetetList.add(new String("Malisheve"));
        qytetetList.add(new String("Viti"));
        qytetetList.add(new String("Deçan"));
        qytetetList.add(new String("Istog"));
        qytetetList.add(new String("Kline"));
        qytetetList.add(new String("Skenderaj"));
        qytetetList.add(new String("Dragash"));
        qytetetList.add(new String("Fushe Kosove"));
        qytetetList.add(new String("Kaçanik"));
        qytetetList.add(new String("Shtime"));
        qytetetList.add(new String("Obiliq"));
        qytetetList.add(new String("Noveberde"));
        return qytetetList;
    }

}
