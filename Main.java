package sample;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        /*pripojenie*/
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("db");
        DBCollection collection = database.getCollection("customers");
        /*pripojenie*/

        /*insertmany*/
        DBObject d1 = new BasicDBObject();
        d1.put("firstname", "Jozef");
        d1.put("lastname", "Holý");

        DBObject d2 = new BasicDBObject();
        d2.put("firstname", "Monika");
        d2.put("lastname", "Podhajecka");


        DBObject d4 = new BasicDBObject();
        d4.put("firstname", "František");
        d4.put("lastname", "Sus");


        DBObject d5 = new BasicDBObject();
        d5.put("firstname", "Františka");
        d5.put("lastname", "Susova");

        collection.insert(d1,d2,d4,d5);
        /*insertmany*/

        /*insertone*/
        DBObject d3 = new BasicDBObject();
        d3.put("firstname", "Veronika");
        d3.put("lastname", "Pravá");

        collection.insert(d3);
        /*insertone*/

        /*výpis*/
        System.out.println("Výpis pred update:");
        DBObject query = new BasicDBObject();
        DBCursor cursor = collection.find(query);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
        /*výpis*/

        /*update - zmena z Veronika na Veronika2*/
        BasicDBObject d3U = new BasicDBObject();
        d3U.put("firstname", "Veronika2");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", d3U);

        collection.update(d3, updateObject);
        /*update*/

        /*výpis po update*/
        System.out.println("Výpis po update:");
        query = new BasicDBObject();
        cursor = collection.find(query);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
        /*výpis po update*/

        /*delete*/
        collection.remove(d3U);
        /*delete*/

        /*výpis po vymazaní Veronika2*/
        System.out.println("Výpis po vymazaní:");
        query = new BasicDBObject();
        cursor = collection.find(query);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
        /*výpis*/

        /*nájdenie*/
        System.out.println("Nájdenie:");
        System.out.println(collection.findOne(d5));
        /*nájdenie*/

        /*vymazanie všetkáho*/
        collection.drop();
        /*vymazanie všetkého*/
    }
}
