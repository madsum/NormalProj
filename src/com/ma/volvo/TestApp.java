package com.ma.volvo;

import java.util.ArrayList;

public class TestApp {
	
    private static XmlUnmarshaller xmlUnmarshaller = new XmlUnmarshaller();
	private static InteriorResponse interiorResponse;
    private static DatabaseManager databaseManager = new DatabaseManager();
	static long uniqeIndexErrorCode = 23000l; 
    static ArrayList<String> data = null;;
    static String pno12 = "ABCDEEX";
    static long str_week_from = 202017;
    static long str_week_to = 202035;
    static String color = "100";
    static String upholstery = "RA0X";
    static String xmlContent = "<Features_res><StartWeek>202017</StartWeek><EndWeek>202035</EndWeek><Pno12>ABCDEEX</Pno12><FeatureList><Feature><Code>10</Code></Feature><Feature><Code>10</Code></Feature></FeatureList><OptionList><Option>1048</Option><Option>50</Option><Option>790</Option></OptionList><CUList><CU><Col>100</Col><Uph>RA0X</Uph><FeatureList><Feature><Code>20</Code></Feature><Feature><Code>30</Code></Feature><Feature><Code>40</Code></Feature><Feature><Code>50</Code></Feature></FeatureList><OptionList><Option><Code>1048</Code><state>optional</state></Option><Option><Code>50</Code><state>available</state></Option><Option><Code>000790</Code><state>optional</state></Option></OptionList></CU></CUList></Features_res>";

	public static void main(String[] args) {
        // xmlUnmarshaller = new XmlUnmarshaller();
		xmlUnmarshaller.UnmarshalXml(xmlContent);
        interiorResponse = xmlUnmarshaller.getInteriorResponse();
        insertIntoDB();
        data = getDataByPno12(pno12);
        // data = getDataByAll(pno12, str_week_from, str_week_to, color, upholstery);
	}
	
    static ArrayList<String> getDataByPno12(String pno12) {
        try {
            data = databaseManager.getDataByPno12(pno12);
    	}catch (Exception e) {
    		System.out.println("Error when insert data check it. Handle error");
        }
        return data;
	}
	
    static ArrayList<String> getDataByAll(String pno12, long str_week_from, long str_week_to, String color, String upholstery) {
        try {
            data = databaseManager.getDataByAll(pno12, str_week_from, str_week_to, color, upholstery);
        } catch (Exception e) {
            System.out.println("Error when insert data check it. Handle error");
        }
        return data;
    }

    static void insertIntoDB() {
    	try {
            long retVal = databaseManager.insertData(interiorResponse);
            if(retVal == uniqeIndexErrorCode || retVal == -1 ) {
            	System.out.println("This row already exit in the table. Handle error");
            	return;
            }
    	}catch (Exception e) {
    		System.out.println("Error when insert data check it. Handle error");
        }
    }

}
