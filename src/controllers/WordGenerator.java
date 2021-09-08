package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Phoneme;
import model.PhonemeType;

import org.apache.commons.lang.WordUtils;

import services.resources.ResourceService;

public class WordGenerator {

	private Long generationTimeOfLastRun;
	private List<Phoneme> phonemeList;
	private ResourceService resourceService;
	
	public WordGenerator(){
		generationTimeOfLastRun = 0l;
		try {resourceService = new ResourceService();} catch (Exception e) {e.printStackTrace();}
		phonemeList = resourceService.getPhonemeList();
	}
	
	public String generateWord(Integer numberOfPhonemes){
		return generateWord(numberOfPhonemes, 1);
	}
	
	public String generateWord(Integer phonemeCount, Integer wordCount) {
		if(phonemeCount <= 0){
			throw new IllegalArgumentException("Phoneme count cannot be zero or negative");
		}
		if(wordCount <= 0){
			throw new IllegalArgumentException("Word count cannot be zero or negative");
		}
		
		Long startTime = System.nanoTime();
		
		String generatedResult = handleGenerateWord(phonemeCount, wordCount);
		
		Long stopTime = System.nanoTime();
		generationTimeOfLastRun = stopTime - startTime;
		
		return generatedResult;
	}
	
	private String handleGenerateWord(Integer phonemeCount, Integer wordCount){
		StringBuffer resultsBuffer = new StringBuffer();
		
		for(int wordCountIndex = 0; wordCountIndex < wordCount; wordCountIndex++){
			List<String> consonantList = createConsonantPhonemeList();
			List<String> vowelList = createVowelPhonemeList();
			
			StringBuffer buffer = new StringBuffer();
			Random random = new Random();
			boolean onType1 = random.nextBoolean();
			for(int index = 0; index < phonemeCount; index++){
				String phoneme;
				if(onType1){
					phoneme = consonantList.get(random.nextInt(consonantList.size()));
					onType1 = false;
				}else{
					phoneme = vowelList.get(random.nextInt(vowelList.size()));
					onType1 = true;
				}
				buffer.append(phoneme);
			}

			String generatedWord = WordUtils.capitalize(buffer.toString());
			resultsBuffer.append(generatedWord);
			resultsBuffer.append(" ");
		}
		
		return resultsBuffer.toString();
	}
	
	private List<String> createConsonantPhonemeList(){
		return createPhonemeListByType(PhonemeType.CONSONANT);
	}
	
	private List<String> createVowelPhonemeList(){
		return createPhonemeListByType(PhonemeType.VOWEL);
	}
	
	private List<String> createPhonemeListByType(PhonemeType type){
		Random random = new Random();
		List<String> phonemeListByType = new ArrayList<String>();
		for(Phoneme phoneme : phonemeList){
			if(phoneme.getType().equals(type)){
				float freq = phoneme.getFrequency();
				int wholeNumber = (int)freq;
				int remainder = (int)((freq - wholeNumber) * 10);
				int phonemeCount = (((random.nextInt(10)+1) > remainder) ? wholeNumber + 1 : wholeNumber);
				for(int index = 0; index < phonemeCount; index++){
					String name = phoneme.getId();
//					if(type.equals(PhonemeType.CONSONANT)){
//						if(random.nextBoolean()){
//							name = phoneme.getAlternateId();
//						}
//					}
					phonemeListByType.add(name);
				}
			}
		}
		return phonemeListByType;
	}

	public Long generationTimeOfLastRun() {
		return generationTimeOfLastRun;
	}

	public void handleClosingOperations() {
		generationTimeOfLastRun = null;
		phonemeList.clear();
		phonemeList = null;
		resourceService.handleClosingOperations();
		resourceService = null;		
	}
}