package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-03T14:25:06.413+0200")
@StaticMetamodel(Drink.class)
public class Drink_ {
	public static volatile SingularAttribute<Drink, Integer> id;
	public static volatile SingularAttribute<Drink, Double> alcohol;
	public static volatile SingularAttribute<Drink, String> color;
	public static volatile SingularAttribute<Drink, String> drinkname;
	public static volatile SingularAttribute<Drink, String> producer;
	public static volatile SingularAttribute<Drink, Drinktype> drinktype;
	public static volatile SingularAttribute<Drink, Country> country;
	public static volatile ListAttribute<Drink, Smell> smells;
	public static volatile ListAttribute<Drink, Taste> tastes;
	public static volatile ListAttribute<Drink, Language> languages;
}
