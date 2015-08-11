package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-11T10:53:31.883+0200")
@StaticMetamodel(Taste.class)
public class Taste_ {
	public static volatile SingularAttribute<Taste, Integer> id;
	public static volatile SingularAttribute<Taste, String> tastetype;
	public static volatile SingularAttribute<Taste, Drink> drink;
}
