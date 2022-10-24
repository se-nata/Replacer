package replacer;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Zamens {

	private ArrayList<Zamena> list = new ArrayList<Zamena>();
	private String oldstr;
	private String newstr;

	public Zamens(String dir) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(dir + "\\replacer_txt.xml"));

			Element element = document.getDocumentElement();
			NodeList nodlist = element.getChildNodes();

			for (int i = 0; i < nodlist.getLength(); i++) {

				if (nodlist.item(i) instanceof Element) {

					for (int j = 0; j < nodlist.item(i).getChildNodes().getLength(); j++) {

						if (nodlist.item(i).getChildNodes().item(j) instanceof Element) {

							if ((((Element) nodlist.item(i).getChildNodes().item(j)).getTagName()).equals("old")) {

								oldstr = (((Element) nodlist.item(i).getChildNodes().item(j)).getTextContent());
							}

							if ((((Element) nodlist.item(i).getChildNodes().item(j)).getTagName()).equals("new")) {

								newstr = (((Element) nodlist.item(i).getChildNodes().item(j)).getTextContent());
							}

						}
					}
					list.add(new Zamena(Pattern.quote(oldstr), newstr));

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Zamena> getList() {
		return list;
	}

}