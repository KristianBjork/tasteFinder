package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-11T10:53:31.832+0200")
@StaticMetamodel(Smell.class)
public class Smell_ {
	public static volatile SingularAttribute<Smell, Integer> id;
	public static volatile SingularAttribute<Smell, String> smelltype;
	public static volatile SingularAttribute<Smell, Drink> drink;
}
