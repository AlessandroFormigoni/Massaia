import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class ScriviFile {
	static String version = "1.0";
	static String encoding = "UTF-8";
	static String filename = "Ricette.xml";
	static XMLOutputFactory xmlof = null;
	static XMLStreamWriter xmlw = null;
	
	public static void initializeWriter() {
		try {
		xmlof = XMLOutputFactory.newInstance();
		xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename),encoding);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void writeRicette(Menu menu) {
		try {
				xmlw.writeStartElement("Ricette");
			for (Ricetta ricetta : menu.getRicette()) {
				xmlw.writeStartElement("ricetta");
				xmlw.writeAttribute("nome", ricetta.getNomeRicetta());
				xmlw.writeAttribute("portata", ricetta.getPiatto().toString());
				for (Ingrediente ingrediente : ricetta.getElenco()) {
					xmlw.writeStartElement("ingrediente");
					xmlw.writeAttribute("nome", ingrediente.getNome());
					xmlw.writeAttribute("peso", Double.toString(ingrediente.getPeso()));
					xmlw.writeAttribute("calorie", Double.toString(ingrediente.getCalorie()));
					xmlw.writeEndElement();
				}
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.flush();
			xmlw.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void write(Menu menu) {
		initializeWriter();
		writeRicette(menu);
	}
	
}
