package com.ma.volvo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

public class DatabaseManagerTest {

	@Mock
	private static DatabaseManager databaseManager;
	static ArrayList<String> returnList = new ArrayList<String>();
	static String pno12 = "aaa";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		databaseManager = mock(DatabaseManager.class);
		returnList.add(pno12);
	}

	@Test
	public void testGetDataByPno12() {
		when(databaseManager.getDataByPno12(pno12)).thenReturn(returnList);
		DatabaseManager db = new DatabaseManager();
		ArrayList<String> expected = databaseManager.getDataByPno12(pno12);
		ArrayList<String> actual = new ArrayList<String>();
		actual.add(pno12);
		assertThat(actual, is(expected));
		System.out.println(expected.get(0));
	}

}
