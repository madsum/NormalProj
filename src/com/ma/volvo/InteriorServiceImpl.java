package com.ma.volvo;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class InteriorServiceImpl implements InteriorService{

	private XmlUnmarshaller xmlUnmarshaller = new XmlUnmarshaller();
	private InteriorResponse interiorResponse = new InteriorResponse();
	private DatabaseManager databaseManager = new DatabaseManager();
	private long uniqeIndexErrorCode = 23000l; 	
    static String xmlContent = "<Features_res><StartWeek>202017</StartWeek><EndWeek>202035</EndWeek><Pno12>ABCDEEX</Pno12><FeatureList><Feature><Code>10</Code></Feature><Feature><Code>10</Code></Feature></FeatureList><OptionList><Option>1048</Option><Option>50</Option><Option>790</Option></OptionList><CUList><CU><Col>100</Col><Uph>RA0X</Uph><FeatureList><Feature><Code>20</Code></Feature><Feature><Code>30</Code></Feature><Feature><Code>40</Code></Feature><Feature><Code>50</Code></Feature></FeatureList><OptionList><Option><Code>1048</Code><state>optional</state></Option><Option><Code>50</Code><state>available</state></Option><Option><Code>000790</Code><state>optional</state></Option></OptionList></CU></CUList></Features_res>";

	
	@WebMethod
	public ArrayList<String> getDataByPno12(String pno12){
		ArrayList<String> data = null;
		try {
            data = databaseManager.getDataByPno12(pno12);
    	}catch (Exception e) {
    		System.out.println("Error when insert data check it. Handle error");
        }
		return data;
	}
	
	@WebMethod
	public void UnmarshallXml() {
		xmlUnmarshaller.UnmarshalXml(xmlContent);
		interiorResponse = xmlUnmarshaller.getInteriorResponse();
		insertIntoDB();
	}
	
    private void insertIntoDB() {
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
