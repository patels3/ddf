/**
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or any later version. 
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
package ddf.catalog.services.transformer.xml.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.Test;

import ddf.catalog.data.BinaryContent;
import ddf.catalog.data.MetacardImpl;
import ddf.catalog.services.transformer.xml.XmlMetacardTransformer;
import ddf.catalog.transform.CatalogTransformerException;

public class TestXmlTransformer {
	
	@Test
	public void test() throws CatalogTransformerException {

		MetacardImpl mc = new MetacardImpl();

		mc.setId("1234567890987654321");
		mc.setTitle("Title!");
		mc.setExpirationDate(new Date());
		mc.setLocation("POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),(20 30, 35 35, 30 20, 20 30))");
		mc.setMetadata("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?></xml>");
		byte[] bytes = { 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1,
				0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1 };
		mc.setThumbnail(bytes);

		XmlMetacardTransformer transformer = new XmlMetacardTransformer();

		BinaryContent bc = transformer.transform(mc, null);

		if (bc == null) {
			fail("Binary Content is null.");
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(bc.getInputStream()));
			String inputLine;
			try {
				System.out.println("\n* * * START XML METACARD REPRESENTATION * * * \n");
				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				}
				in.close();
				System.out.println("\n* * * END XML METACARD REPRESENTATION * * * \n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
