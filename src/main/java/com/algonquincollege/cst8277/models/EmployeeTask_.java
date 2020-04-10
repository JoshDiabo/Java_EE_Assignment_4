package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-09T20:15:28.749-0400")
@StaticMetamodel(EmployeeTask.class)
public class EmployeeTask_ {
	public static volatile SingularAttribute<EmployeeTask, String> description;
	public static volatile SingularAttribute<EmployeeTask, LocalDateTime> taskStart;
	public static volatile SingularAttribute<EmployeeTask, LocalDateTime> taskEndDate;
	public static volatile SingularAttribute<EmployeeTask, Boolean> done;
}

