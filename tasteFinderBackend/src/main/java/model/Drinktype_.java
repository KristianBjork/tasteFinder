package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-03T14:25:06.470+0200")
@StaticMetamodel(Drinktype.class)
public class Drinktype_ {
	public static volatile SingularAttribute<Drinktype, Integer> id;
	public static volatile ListAttribute<Drinktype, Language> languages;
	public static volatile ListAttribute<Drinktype, Drink> drinks;
}
