package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-09T18:41:31.098-0400")
@StaticMetamodel(SecurityRole.class)
public class SecurityRole_ {
	public static volatile SingularAttribute<SecurityRole, Integer> id;
	public static volatile SetAttribute<SecurityRole, SecurityUser> users;
	public static volatile SingularAttribute<SecurityRole, String> roleName;
}
