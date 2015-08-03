package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-03T14:25:06.576+0200")
@StaticMetamodel(Smell.class)
public class Smell_ {
	public static volatile SingularAttribute<Smell, Integer> id;
	public static volatile SingularAttribute<Smell, String> smelltype;
	public static volatile SingularAttribute<Smell, Drink> drink;
}
