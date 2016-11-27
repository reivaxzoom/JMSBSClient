package elte.client.operations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import elte.client.model.Item;


public class PredifinedDataStore {
	static Supplier<Integer> rndIt = PredifinedDataStore::rndItem;
	static Supplier<String> supplyEuContries = PredifinedDataStore::getRandomCountry;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	static Supplier<Item> supplyClientRequest = PredifinedDataStore::createClientRequest;
    
	
	public static List<Item> itemsSample;
	
	 public static Item createClientRequest() {
	        Collections.shuffle(itemsSample);
	        return itemsSample.get(0);

	    }
	
	
	 static{
        itemsSample = new LinkedList<>();
        itemsSample.add(new Item("Cider", rndIt.get(), "beverages"));
        itemsSample.add(new Item("sangria", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Yoghurt", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Mango", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Pineapple", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Oranges", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Apples", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Bananas", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Lettuce", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Tomatoes", rndIt.get(), "fruit"));
        itemsSample.add(new Item("Squash", rndIt.get(), "other"));
        itemsSample.add(new Item("Celery", rndIt.get(), "other"));
        itemsSample.add(new Item("Cucumber", rndIt.get(), "other"));
        itemsSample.add(new Item("Mushrooms", rndIt.get(), "other"));
        itemsSample.add(new Item("Milk", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Cheese", rndIt.get(), "other"));
        itemsSample.add(new Item("Eggs", rndIt.get(), "other"));
        itemsSample.add(new Item("Cottagecheese", rndIt.get(), "other"));
        itemsSample.add(new Item("Sourcream", rndIt.get(), "other"));
        itemsSample.add(new Item("Beef", rndIt.get(), "meat"));
        itemsSample.add(new Item("Poultry", rndIt.get(), "meat"));
        itemsSample.add(new Item("Ham", rndIt.get(), "meat"));
        itemsSample.add(new Item("Seafood", rndIt.get(), "meat"));
        itemsSample.add(new Item("Lunchmeat", rndIt.get(), "meat"));
        itemsSample.add(new Item("Soda", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Juice", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Coffee", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Tea", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Water", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Beer", rndIt.get(), "beverages"));
        itemsSample.add(new Item("Noodles", rndIt.get(), "other"));
        itemsSample.add(new Item("Rice", rndIt.get(), "grains"));
        itemsSample.add(new Item("Canned", rndIt.get(), "other"));
        itemsSample.add(new Item("Drymix", rndIt.get(), "other"));
        itemsSample.add(new Item("Bread", rndIt.get(), "pastery"));
        itemsSample.add(new Item("Bagels", rndIt.get(), "pastery"));
        itemsSample.add(new Item("Muffins", rndIt.get(), "pastery"));
        itemsSample.add(new Item("Cake", rndIt.get(), "pastery"));
        itemsSample.add(new Item("Potatochips", rndIt.get(), "snacks"));
        itemsSample.add(new Item("Pretzels", rndIt.get(), "snacks"));
        itemsSample.add(new Item("Icecream", rndIt.get(), "snacks"));
        itemsSample.add(new Item("Cookies", rndIt.get(), "snacks"));
        itemsSample.add(new Item("Paperplates", rndIt.get(), "cleaners"));
        itemsSample.add(new Item("Napkins", rndIt.get(), "cleaners"));
        itemsSample.add(new Item("Garbagebags", rndIt.get(), "cleaners"));
        itemsSample.add(new Item("Detergent", rndIt.get(), "cleaners"));
        itemsSample.add(new Item("hikingboots", rndIt.get(), "shoes"));
        itemsSample.add(new Item("casual", rndIt.get(), "shoes"));
        
        itemsSample.add(new Item("backpack", rndIt.get(), "sport"));
        itemsSample.add(new Item("tent", rndIt.get(), "sport"));
        itemsSample.add(new Item("soccershoes", rndIt.get(), "shoes"));
        itemsSample.add(new Item("soccerball", rndIt.get(), "sport"));
        itemsSample.add(new Item("basketballshoes", rndIt.get(), "shoes"));
        itemsSample.add(new Item("highhills", rndIt.get(), "shoes"));
        itemsSample.add(new Item("raquet", rndIt.get(), "sport"));
        itemsSample.add(new Item("socks", rndIt.get(), "sport"));
        itemsSample.add(new Item("shinguards", rndIt.get(), "sport"));
        itemsSample.add(new Item("archeryequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("balls", rndIt.get(), "sport"));
        itemsSample.add(new Item("bicycles", rndIt.get(), "sport"));
        itemsSample.add(new Item("blood/urinetestapparatus", rndIt.get(), "sport"));
        itemsSample.add(new Item("bobsleds", rndIt.get(), "sport"));
        itemsSample.add(new Item("boomerangs", rndIt.get(), "sport"));
        itemsSample.add(new Item("braces", rndIt.get(), "sport"));
        itemsSample.add(new Item("calibratingdevices", rndIt.get(), "sport"));
        itemsSample.add(new Item("canoes", rndIt.get(), "sport"));
        itemsSample.add(new Item("climbingequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("clubs", rndIt.get(), "sport"));
        itemsSample.add(new Item("compasses", rndIt.get(), "sport"));
        itemsSample.add(new Item("curlingbrooms", rndIt.get(), "sport"));
        itemsSample.add(new Item("discuses", rndIt.get(), "sport"));
        itemsSample.add(new Item("downhillskis", rndIt.get(), "sport"));
        itemsSample.add(new Item("epees", rndIt.get(), "sport"));
        itemsSample.add(new Item("ergometers", rndIt.get(), "sport"));
        itemsSample.add(new Item("exercisebenches", rndIt.get(), "sport"));
        itemsSample.add(new Item("eyewear", rndIt.get(), "sport"));
        itemsSample.add(new Item("fieldhockeysticks", rndIt.get(), "sport"));
        
        itemsSample.add(new Item("nonslip floors", rndIt.get(), "gym"));
        itemsSample.add(new Item("mirror", rndIt.get(), "gym"));
        itemsSample.add(new Item("static bike", rndIt.get(), "gym"));
        itemsSample.add(new Item("ketleball", rndIt.get(), "gym"));
        itemsSample.add(new Item("weights", rndIt.get(), "gym"));
        itemsSample.add(new Item("suspension trainer", rndIt.get(), "gym"));
        itemsSample.add(new Item("storage racks", rndIt.get(), "gym"));
        
        itemsSample.add(new Item("handgrip", rndIt.get(), "fitness"));
        itemsSample.add(new Item("strength bands", rndIt.get(), "fitness"));
        itemsSample.add(new Item("Dumbbells 9 lbs", rndIt.get(), "fitness"));
        itemsSample.add(new Item("jump rope", rndIt.get(), "fitness"));
        
        itemsSample.add(new Item("gloves", rndIt.get(), "sport"));
        itemsSample.add(new Item("goalsposts", rndIt.get(), "sport"));
        itemsSample.add(new Item("goals", rndIt.get(), "sport"));
        itemsSample.add(new Item("grips", rndIt.get(), "sport"));
        itemsSample.add(new Item("gymnasticsequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("handballs", rndIt.get(), "sport"));
        itemsSample.add(new Item("hang-gliders", rndIt.get(), "sport"));
        itemsSample.add(new Item("headgear", rndIt.get(), "sport"));
        itemsSample.add(new Item("helmets", rndIt.get(), "sport"));
        itemsSample.add(new Item("hockeypucks", rndIt.get(), "sport"));
        itemsSample.add(new Item("hockeysticks", rndIt.get(), "sport"));
        itemsSample.add(new Item("horsesfordressage", rndIt.get(), "sport"));
        itemsSample.add(new Item("hurdles", rndIt.get(), "sport"));
        itemsSample.add(new Item("iceskates", rndIt.get(), "sport"));
        itemsSample.add(new Item("javelins", rndIt.get(), "sport"));
        itemsSample.add(new Item("kayaks", rndIt.get(), "sport"));
        itemsSample.add(new Item("luges", rndIt.get(), "sport"));
        itemsSample.add(new Item("mallets", rndIt.get(), "sport"));
        itemsSample.add(new Item("markers", rndIt.get(), "sport"));
        itemsSample.add(new Item("mats", rndIt.get(), "sport"));
        itemsSample.add(new Item("nets", rndIt.get(), "sport"));
        itemsSample.add(new Item("Nordicskis", rndIt.get(), "sport"));
        itemsSample.add(new Item("oars", rndIt.get(), "sport"));
        itemsSample.add(new Item("padding", rndIt.get(), "sport"));
        itemsSample.add(new Item("paddles", rndIt.get(), "sport"));
        itemsSample.add(new Item("poles(vaulting)", rndIt.get(), "sport"));
        itemsSample.add(new Item("protectiveclothing", rndIt.get(), "sport"));
        itemsSample.add(new Item("protectivegear", rndIt.get(), "sport"));
        itemsSample.add(new Item("pucks", rndIt.get(), "sport"));
        itemsSample.add(new Item("racehorses", rndIt.get(), "sport"));
        itemsSample.add(new Item("raceskis", rndIt.get(), "sport"));
        itemsSample.add(new Item("racingshells", rndIt.get(), "sport"));
        itemsSample.add(new Item("racquetballs", rndIt.get(), "sport"));
        itemsSample.add(new Item("racquets", rndIt.get(), "sport"));
        itemsSample.add(new Item("ridingequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("rowboats", rndIt.get(), "sport"));
        itemsSample.add(new Item("rifles", rndIt.get(), "sport"));
        itemsSample.add(new Item("runningshoes", rndIt.get(), "sport"));
        itemsSample.add(new Item("sailboats", rndIt.get(), "sport"));
        itemsSample.add(new Item("sails", rndIt.get(), "sport"));
        itemsSample.add(new Item("scoreboard", rndIt.get(), "sport"));
        itemsSample.add(new Item("scoringequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("sculls", rndIt.get(), "sport"));
        itemsSample.add(new Item("shoes", rndIt.get(), "sport"));
        itemsSample.add(new Item("shot(forshotput)", rndIt.get(), "sport"));
        itemsSample.add(new Item("showhorses", rndIt.get(), "sport"));
        itemsSample.add(new Item("skis(forjumping)", rndIt.get(), "sport"));
        itemsSample.add(new Item("sleddogs", rndIt.get(), "sport"));
        itemsSample.add(new Item("sleds", rndIt.get(), "sport"));
        itemsSample.add(new Item("sportarms", rndIt.get(), "sport"));
        itemsSample.add(new Item("squashballs", rndIt.get(), "sport"));
        itemsSample.add(new Item("stopwatches", rndIt.get(), "sport"));
        itemsSample.add(new Item("strength&conditioningequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("sulkies", rndIt.get(), "sport"));
        itemsSample.add(new Item("surfboards", rndIt.get(), "sport"));
        itemsSample.add(new Item("supports/braces", rndIt.get(), "sport"));
        itemsSample.add(new Item("tapemeasures", rndIt.get(), "sport"));
        itemsSample.add(new Item("testingdevices", rndIt.get(), "sport"));
        itemsSample.add(new Item("timingequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("toolsforrepairs&adjustments", rndIt.get(), "sport"));
        itemsSample.add(new Item("uniforms", rndIt.get(), "sport"));
        itemsSample.add(new Item("watercraft", rndIt.get(), "sport"));
        itemsSample.add(new Item("weightliftingequipment", rndIt.get(), "sport"));
        itemsSample.add(new Item("wheelchairsforcompetition", rndIt.get(), "sport"));
        itemsSample.add(new Item("windsurfers", rndIt.get(), "sport"));
        itemsSample.add(new Item("wrestlingmats&tatamis", rndIt.get(), "sport"));
        
        itemsSample.add(new Item("soccer balls", rndIt.get(), "futbol"));
        itemsSample.add(new Item("shin guards", rndIt.get(), "futbol"));
        itemsSample.add(new Item("goolkeeper gear", rndIt.get(), "futbol"));
        itemsSample.add(new Item("portable soccer goal", rndIt.get(), "futbol"));
        itemsSample.add(new Item("anckle support", rndIt.get(), "futbol"));
        itemsSample.add(new Item("Cleats", rndIt.get(), "futbol"));
        
        
        
        itemsSample.add(new Item("laptop", rndIt.get(), "hardware"));
        itemsSample.add(new Item("network cable", rndIt.get(), "hardware"));
        itemsSample.add(new Item("mouse", rndIt.get(), "hardware"));
        itemsSample.add(new Item("printer", rndIt.get(), "hardware"));
        itemsSample.add(new Item("server", rndIt.get(), "hardware"));
        itemsSample.add(new Item("Word processor", rndIt.get(), "software"));
        itemsSample.add(new Item("antivirus", rndIt.get(), "software"));
        itemsSample.add(new Item("videogames", rndIt.get(), "software"));
        itemsSample.add(new Item("translator", rndIt.get(), "software"));
        itemsSample.add(new Item("billProcessor", rndIt.get(), "software"));
        itemsSample.add(new Item("laptop l1", rndIt.get(), "computer"));
        itemsSample.add(new Item("student computer", rndIt.get(), "computer"));
        itemsSample.add(new Item("high performance computer", rndIt.get(), "computer"));
        itemsSample.add(new Item("basic computer", rndIt.get(), "computer"));
        itemsSample.add(new Item("light laptop", rndIt.get(), "computer"));
    }

    public static Map<String, Object> getRandomSampleRequestData() {
        Calendar expCal = Calendar.getInstance();
        String expDate = dateFormat.format(expCal.getTime());
        expCal.add(Calendar.DAY_OF_YEAR, rndIt.get());
        
        List<Map<String, Object>> listProperties = new ArrayList<>();
        Map<String, Object> prop1 = new HashMap<>();
        prop1.put("name", "Xavier");
        prop1.put("address", "street 1");
        prop1.put("phone", "5936656565656");
        prop1.put("country", supplyEuContries.get());
        prop1.put("expDelivery", expDate);
        prop1.put("comments", "It is needed for a conmemorative party, traditional food server, customes party");
        prop1.put("deliverAddress", "street 1");
        prop1.put("frecuent", Boolean.TRUE);
        

        Map<String, Object> prop2 = new HashMap<>();
        prop2.put("name", "Nicolas");
        prop2.put("address", "Heroes street");
        prop2.put("phone", "3687898545648");
        prop2.put("country", supplyEuContries.get());
        prop2.put("expDelivery", expDate);
        prop2.put("comments", "it is for school assigment");
        prop2.put("deliverAddress", "devoi utca");
        prop2.put("frecuent", Boolean.FALSE);
        
        Map<String, Object> prop6 = new HashMap<>();
        prop6.put("name", "Gabriela");
        prop6.put("address", "Huba ut");
        prop6.put("phone", "3689938383882");
        prop6.put("country", supplyEuContries.get());
        prop6.put("expDelivery", expDate);
        prop6.put("comments", "outdoor activities, hiking to the hill, next month");
        prop6.put("deliverAddress", "Huba ut");
        prop6.put("frecuent", Boolean.TRUE);

        Map<String, Object> prop3 = new HashMap<>();
        prop3.put("name", "Eva");
        prop3.put("address", "nemet utca");
        prop3.put("phone", "361122334455");
        prop3.put("country", supplyEuContries.get());
        prop3.put("expDelivery", expDate);
        prop3.put("comments", "There will be an outdoor party");
        prop3.put("deliverAddress", "nemet utca");
        prop3.put("frecuent", Boolean.TRUE);

        Map<String, Object> prop4 = new HashMap<>();
        prop4.put("name", "Laszlo");
        prop4.put("address", "street 4");
        prop4.put("phone", "3644444444");
        prop4.put("country", supplyEuContries.get());
        prop4.put("expDelivery", expDate);
        prop4.put("comments", "Tentative party association  ");
        prop4.put("deliverAddress", "street 1");
        prop4.put("frecuent", Boolean.TRUE);

        Map<String, Object> prop5 = new HashMap<>();
        prop5.put("name", "Sally");
        prop5.put("address", "marinara utca");
        prop5.put("phone", "364444444444");
        prop5.put("country", supplyEuContries.get());
        prop5.put("expDelivery", expDate);
        prop5.put("comments", "student project computer science, cables, adaptor and computer keyboard, may be a cellphone");
        prop5.put("deliverAddress", "marinara utca");
        prop5.put("frecuent", Boolean.TRUE);

        listProperties.add(prop1);
        listProperties.add(prop2);
        listProperties.add(prop3);
        listProperties.add(prop4);
        listProperties.add(prop5);
        listProperties.add(prop6);

        Collections.shuffle(listProperties);
        return listProperties.get(0);
    }

    private static String getRandomCountry() {
        List<String> countries = Arrays.asList("Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden", "United Kingdom");
        Collections.shuffle(countries);
        return countries.get(0);
    }
    
    

    
    private static int rndItem() {
        int minItNum = 1, maxItNum = 10 ;
        return rndNumber(minItNum,maxItNum);
    }
    
    private static int rndNumber(int min, int max ){
        return new Random().nextInt(max - min + 1) + min;
    }

}
