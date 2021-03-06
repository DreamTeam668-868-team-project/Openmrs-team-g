/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.UpdateRecords;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the PersonService Class in the OpenMRS system provides reporting on when methods
 * are invoked from the PersonService interface Focuses on methods which retrieve, create, and
 * delete Person records
 * 
 * @author Travis
 */
public class PersonAdvice implements AfterReturningAdvice {
	
	/**
	 * @param returnObject the object returned from the invoking method
	 * @param method the invoking method
	 * @param args the list of arguments provided to the invoking method when it was called
	 * @param target
	 */
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		//            if((method.getDeclaringClass()).equals(Context.getPersonService().getClass())){
		//                if(returnObject.getClass().equals(Person.class)){
		//                    
		//                }
		//            }
		String recordType = "PERSON";
		String actionType = "";
		
		// getters
		// returns type List<Person>
		if (method.getName().equals("getPeople")) {
			actionType = "RETRIEVAL";
			Date date = new Date();
			
			List<Person> returnList = (List<Person>) returnObject;
			for (Iterator<Person> i = returnList.iterator(); i.hasNext();) {
				UpdateRecords.add(Context.getAuthenticatedUser(), i.next().getId(), recordType, actionType, date);
			}
			return;
		}
		
		// getters
		// returns type Person
		if (method.getName().startsWith("getPerson") && returnObject.getClass().equals(Person.class)) {
			actionType = "RETRIEVAL";
			Person person = (Person) returnObject;
			UpdateRecords.add(Context.getAuthenticatedUser(), person.getId(), recordType, actionType, new Date());
			return;
		}
		
		// deletion methods
		if (method.getName().equals("voidPerson") || method.getName().equals("purgePerson")) {
			actionType = "DELETE";
			Person person = (Person) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), person.getId(), recordType, actionType, new Date());
			return;
		}
		
		if (method.getName().equals("unvoidPerson")) {
			actionType = "UNVOID";
			
			Person person = (Person) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), person.getId(), recordType, actionType, new Date());
			return;
		}
		
		if (method.getName().equals("savePerson")) {
			
			actionType = "CREATE";
			
			Person person = (Person) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), person.getId(), recordType, actionType, new Date());
		}
	}
}
