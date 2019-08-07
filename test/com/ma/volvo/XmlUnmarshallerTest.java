package com.ma.volvo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class XmlUnmarshallerTest {

    static String xmlContent = "<Features_res><StartWeek>202017</StartWeek><EndWeek>202035</EndWeek><Pno12>ABCDEEX</Pno12>"
    		+ "<FeatureList><Feature><Code>10</Code></Feature><Feature><Code>10</Code></Feature></FeatureList><OptionList><Option>1048</Option><Option>50</Option><Option>790</Option></OptionList><CUList><CU><Col>100</Col><Uph>RA0X</Uph><FeatureList><Feature><Code>20</Code></Feature><Feature><Code>30</Code></Feature><Feature><Code>40</Code></Feature><Feature><Code>50</Code></Feature></FeatureList><OptionList><Option><Code>1048</Code><state>optional</state></Option><Option><Code>50</Code><state>available</state></Option><Option><Code>000790</Code><state>optional</state></Option></OptionList></CU></CUList></Features_res>";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testUnmarshalXml() {
		XmlUnmarshaller xmlUnmarshaller = new XmlUnmarshaller();
		InteriorResponse ir = xmlUnmarshaller.UnmarshalXml(xmlContent);
		
		String expectedColor = "100";
		String expectedUpholstery = "RA0X";
		String expectedPno12 = "ABCDEEX";
		int expectedStartWeek = 202017;
		int expectedEndWeek = 202035;
		
		System.out.println("stop");
		assertEquals(ir.getColor(), expectedColor);
		assertTrue( ir.getUpholstrey() == expectedUpholstery);
		assertTrue( ir.getPno12() == expectedPno12);
		assertTrue( ir.getStartWeek() == expectedStartWeek);
		assertTrue( ir.getStartWeek() == expectedEndWeek);
	}

}
