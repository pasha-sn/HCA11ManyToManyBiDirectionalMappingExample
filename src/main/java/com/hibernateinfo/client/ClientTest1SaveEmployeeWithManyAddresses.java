package com.hibernateinfo.client;

import java.util.Date;

import org.hibernate.Session;

import com.hibernateinfo.entities.Address;
import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest1SaveEmployeeWithManyAddresses {

	public static void main(String[] args) 
	{
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			session.beginTransaction();
			
			Employee employee1= new Employee();
			employee1.setEmployeeName("Pasha Sadi");
			employee1.setEmail("pasha.sn@gmail.com");
			employee1.setSalary(65001.00);
			employee1.setDoj(new Date());
			
			Employee employee2= new Employee();
			employee2.setEmployeeName("Saba Divanpour");
			employee2.setEmail("test@gmail.com");
			employee2.setSalary(76001.00);
			employee2.setDoj(new Date());
			
			Address address1 = new Address();
			address1.setStreet("Guy St");
			address1.setCity("Montreal");
			address1.setState("Quebec");
			address1.setPostalcode(19317l);
			
			Address address2 = new Address();
			address2.setStreet("Peel St");
			address2.setCity("Montreal");
			address2.setState("Quebec");
			address2.setPostalcode(19318l);
			
			Address address3 = new Address();
			address3.setStreet("University St");
			address3.setCity("Montreal");
			address3.setState("Quebec");
			address3.setPostalcode(19319l);
			
			employee1.getAddressList().add(address1);
			employee1.getAddressList().add(address2);
			employee1.getAddressList().add(address3);
			
			address1.getEmployeeList().add(employee1);
			address2.getEmployeeList().add(employee1);
			address3.getEmployeeList().add(employee1);
			
			employee2.getAddressList().add(address2);
			employee2.getAddressList().add(address3);
			
			address2.getEmployeeList().add(employee2);
			address3.getEmployeeList().add(employee2);
			
			session.save(employee1);
			session.save(employee2);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}			
}
/*
<property name="hibernate.hbm2ddl.auto">create</property>
Hibernate: 
    
    drop table address_table cascade constraints
Hibernate: 
    
    drop table EMPLOYEE_ADDRESS_TABLE cascade constraints
Hibernate: 
    
    drop table employee_table cascade constraints
Hibernate: 
    
    drop sequence hibernate_sequence
Hibernate: create sequence hibernate_sequence start with 1 increment by  1
Hibernate: 
    
    create table address_table (
       address_id number(10,0) not null,
        city_name varchar2(50 char),
        postal_code number(19,0),
        state_name varchar2(255 char),
        street_name varchar2(50 char),
        primary key (address_id)
    )
Hibernate: 
    
    create table EMPLOYEE_ADDRESS_TABLE (
       EMPLOYEE_ID number(10,0) not null,
        ADDRESS_ID number(10,0) not null
    )
Hibernate: 
    
    create table employee_table (
       employee_id number(10,0) not null,
        date_of_join timestamp,
        email varchar2(255 char),
        employee_name varchar2(100 char) not null,
        salary double precision,
        primary key (employee_id)
    )
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    
    alter table EMPLOYEE_ADDRESS_TABLE 
       add constraint FKphgr2qpjqlk6xhi7px1t2b7rg 
       foreign key (ADDRESS_ID) 
       references address_table
Hibernate: 
    
    alter table EMPLOYEE_ADDRESS_TABLE 
       add constraint FKfpb7bjnuajgo5jr0bpj6s025f 
       foreign key (EMPLOYEE_ID) 
       references employee_table
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, postal_code, state_name, street_name, address_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, postal_code, state_name, street_name, address_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, postal_code, state_name, street_name, address_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        EMPLOYEE_ADDRESS_TABLE
        (EMPLOYEE_ID, ADDRESS_ID) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        EMPLOYEE_ADDRESS_TABLE
        (EMPLOYEE_ID, ADDRESS_ID) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        EMPLOYEE_ADDRESS_TABLE
        (EMPLOYEE_ID, ADDRESS_ID) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        EMPLOYEE_ADDRESS_TABLE
        (EMPLOYEE_ID, ADDRESS_ID) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        EMPLOYEE_ADDRESS_TABLE
        (EMPLOYEE_ID, ADDRESS_ID) 
    values
        (?, ?)
*/