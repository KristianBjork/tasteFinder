package se.tasteFinder.representation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import model.Language;

@XmlRootElement(name = "langugage")
public class LanguageRepresentation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String languageCode;
	
	private String text;
	
	private String type;
	
	public LanguageRepresentation(String languageCode, String text, String type){
		this.languageCode = languageCode;
		this.text = text;
		this.type = type;
	}
	
	@XmlTransient
	public Language getLanguage(){
		Language language = new Language();
		language.setLanguagecode(languageCode);
		language.setText(text);
		language.setType(type);
		return language;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
