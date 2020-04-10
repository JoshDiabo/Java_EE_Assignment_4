package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-09T22:14:23.245-0400")
@StaticMetamodel(PhonePojo.class)
public class PhonePojo_ extends PojoBase_ {
	public static volatile SingularAttribute<PhonePojo, String> phoneType;
	public static volatile SingularAttribute<PhonePojo, EmployeePojo> owningEmployee;
	public static volatile SingularAttribute<PhonePojo, String> areacode;
	public static volatile SingularAttribute<PhonePojo, String> phoneNumber;
}
