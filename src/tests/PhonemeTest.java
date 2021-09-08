package tests;

import java.util.List;

import model.Phoneme;
import services.resources.ResourceService;

public class PhonemeTest {

	public static void main(String[] args) throws Exception{
		ResourceService resourceService = new ResourceService();
		List<Phoneme> phonemeList = resourceService.getPhonemeList();
		for(Phoneme phoneme:phonemeList){
			System.out.println(phoneme);
		}
	}
}