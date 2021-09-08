package services.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.Phoneme;
import model.PhonemeType;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.xml.sax.SAXException;

import services.resolver.IResourceReader;
import services.resolver.XMLResourceReaderImpl;

public class ResourceService {

	private IResourceReader resourceReader;
	
	public ResourceService() throws SAXException, DocumentException{
		InputStream resourceStream = ResourceService.class.getResourceAsStream("XMLResources/PhonemeFrequency.xml");
//		String resourceFilePath = resourceStream.getPath().replaceAll("%20", " ");
		resourceReader = new XMLResourceReaderImpl(resourceStream);
	}
	
	public List<Phoneme> getPhonemeList(){
		List<? extends Node> elementList = resourceReader.getElementFromTagName("phoneme");
		List<Phoneme> phonemeList = new ArrayList<Phoneme>();
		for(Node node : elementList){
			Element element = (Element) node;
			
			Attribute nameAttrib = element.attribute("name");
			String phonemeName = nameAttrib.getText();
		
			Element freqElement = element.element("frequency");
			Attribute freqAttrib = freqElement.attribute("frequency");
			String freqAmountString = freqAttrib.getText();
			Float freqAmount = Float.parseFloat(freqAmountString);
			
			Attribute typeAttrib = element.attribute("type");
			PhonemeType phonemeType = PhonemeType.valueOf(typeAttrib.getText().toUpperCase());
			
			Attribute alternateNameAttrib = element.attribute("alternate");
			String alternateName;
			if(phonemeType.equals(PhonemeType.CONSONANT)){
				alternateName = alternateNameAttrib.getText();
			}else{
				alternateName = "";
			}
			
			Phoneme phoneme = new Phoneme();
			phoneme.setId(phonemeName);
			phoneme.setType(phonemeType);
			phoneme.setFrequency(freqAmount);
			phoneme.setAlternateId(alternateName);
			phonemeList.add(phoneme);
		}
		
		return phonemeList;		
	}

	public void handleClosingOperations() {
		resourceReader.handleClosingOperations();
		resourceReader = null;
	}
}