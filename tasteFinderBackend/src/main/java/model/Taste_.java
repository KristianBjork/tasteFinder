package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-09T13:34:30.361+0200")
@StaticMetamodel(Taste.class)
public class Taste_ {
	public static volatile SingularAttribute<Taste, Integer> id;
	public static volatile SingularAttribute<Taste, String> tastetype;
	public static volatile SingularAttribute<Taste, Drink> drink;
}
