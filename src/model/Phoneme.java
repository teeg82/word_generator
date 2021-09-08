package model;

public class Phoneme {

	private String id;
	private String alternateId;
	private PhonemeType type;
	private Float frequency;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}
	public String getAlternateId() {
		return alternateId;
	}
	public PhonemeType getType() {
		return type;
	}
	public void setType(PhonemeType type) {
		this.type = type;
	}
	public Float getFrequency() {
		return frequency;
	}
	public void setFrequency(Float frequency) {
		this.frequency = frequency;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Phoneme for sound '");
		buffer.append(id);
		buffer.append("', Type = ");
		buffer.append(type);
		buffer.append(", Alternate spelling = ");
		buffer.append(alternateId);
		buffer.append(", sound frequency = ");
		buffer.append(frequency);
		return buffer.toString();
	}
}