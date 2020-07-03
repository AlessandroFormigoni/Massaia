import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class LeggiFile {

	static String filename = "Ricette.xml";
	static XMLStreamReader xmlr = null;
	private static Menu menu = new Menu();
	
	public static void initializeReader() {
		try {
			xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void extractRicette() {
		try {
			while(xmlr.hasNext()) {
				int count=0;
				switch(xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					switch(xmlr.getLocalName()) {
					case "ricetta":
						String nomeR = xmlr.getAttributeValue(0);
						Portata piatto = Portata.valueOf(xmlr.getAttributeValue(1));
						Ricetta r = new Ricetta(nomeR, piatto);
						menu.addRicetta(r);
						if(menu.getRicette().size()!=0)
							count++;
						break;
					case "ingrediente":
						String nomeI = xmlr.getAttributeValue(0);
						double peso = Double.parseDouble(xmlr.getAttributeValue(1));
						double calorie = Double.parseDouble(xmlr.getAttributeValue(2));
						menu.getRicette().get(count).addIngrediente(new Ingrediente(nomeI,peso,calorie));
						menu.addIngrediente(new Ingrediente(nomeI,peso,calorie));
						break;
					}
					break;
				 case XMLStreamConstants.END_ELEMENT:
					 break;
				 case XMLStreamConstants.COMMENT:
					 break; 
				 case XMLStreamConstants.CHARACTERS:
					 break;
				}
				xmlr.next();
			}			
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static Menu getMenu() {
		initializeReader();
		extractRicette();
		return menu;
	}
}
