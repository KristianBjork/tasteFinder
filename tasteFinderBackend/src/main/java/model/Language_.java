package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-07-21T16:36:05.130+0200")
@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, Integer> id;
	public static volatile SingularAttribute<Language, String> languagecode;
	public static volatile SingularAttribute<Language, String> type;
	public static volatile SingularAttribute<Language, String> text;
	public static volatile SingularAttribute<Language, Drinktype> drinktype;
	public static volatile SingularAttribute<Language, Drink> drink;
	public static volatile SingularAttribute<Language, Country> country;
}
