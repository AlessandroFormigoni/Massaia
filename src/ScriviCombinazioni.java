import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class ScriviCombinazioni {
	static String version = "1.0";
	static String encoding = "UTF-8";
	static String filename = "Combinazioni.xml";
	static XMLOutputFactory xmlof = null;
	static XMLStreamWriter xmlw = null;
	
	public static void initializeWriter() {
		try {
		xmlof = XMLOutputFactory.newInstance();
		xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename),encoding);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void writeCombinazioni(ArrayList<Ricetta[]> combinazioni, double maxCal) {
		try {
			xmlw.writeStartElement("combinazioni");
		for (Ricetta[] ricette : combinazioni) {
			xmlw.writeStartElement("combinazione");
			xmlw.writeAttribute("Calorie_massime", Double.toString(maxCal));
			xmlw.writeStartElement("primo");
			xmlw.writeAttribute("nome", ricette[0].getNomeRicetta());
			xmlw.writeEndElement();
			xmlw.writeStartElement("secondo");
			xmlw.writeAttribute("nome", ricette[1].getNomeRicetta());
			xmlw.writeEndElement();
			xmlw.writeEndElement();
			}
		xmlw.writeEndElement();
		xmlw.flush();
		xmlw.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void write(ArrayList<Ricetta[]> combinazioni, double maxCal) {
		initializeWriter();
		writeCombinazioni(combinazioni,maxCal);
	}
	
}
