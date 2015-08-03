package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-03T14:25:06.202+0200")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile ListAttribute<Country, Language> languages;
	public static volatile ListAttribute<Country, Drink> drinks;
}
