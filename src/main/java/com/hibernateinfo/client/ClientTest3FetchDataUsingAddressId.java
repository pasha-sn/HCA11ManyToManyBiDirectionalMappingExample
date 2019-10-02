package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.entities.Address;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest3FetchDataUsingAddressId {

	public static void main(String[] args) 
	{
		getEmployeeAndAddressByAddressId();
	}	
	
	
	
	private static void getEmployeeAndAddressByAddressId() 
	{		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			Address address = session.get(Address.class, 3);
			System.out.println("**********************************");
			System.out.println(address);
			System.out.println("**********************************");
			if (address != null) 
			{
				address.getEmployeeList().forEach(System.out::println);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    select
        address0_.address_id as address_id1_0_0_,
        address0_.city_name as city_name2_0_0_,
        address0_.postal_code as postal_code3_0_0_,
        address0_.state_name as state_name4_0_0_,
        address0_.street_name as street_name5_0_0_ 
    from
        address_table address0_ 
    where
        address0_.address_id=?
**********************************
Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318]
**********************************
Hibernate: 
    select
        employeeli0_.ADDRESS_ID as ADDRESS_ID2_1_0_,
        employeeli0_.EMPLOYEE_ID as EMPLOYEE_ID1_1_0_,
        employee1_.employee_id as employee_id1_2_1_,
        employee1_.date_of_join as date_of_join2_2_1_,
        employee1_.email as email3_2_1_,
        employee1_.employee_name as employee_name4_2_1_,
        employee1_.salary as salary5_2_1_ 
    from
        EMPLOYEE_ADDRESS_TABLE employeeli0_ 
    inner join
        employee_table employee1_ 
            on employeeli0_.EMPLOYEE_ID=employee1_.employee_id 
    where
        employeeli0_.ADDRESS_ID=?
Hibernate: 
    select
        addresslis0_.EMPLOYEE_ID as EMPLOYEE_ID1_1_0_,
        addresslis0_.ADDRESS_ID as ADDRESS_ID2_1_0_,
        address1_.address_id as address_id1_0_1_,
        address1_.city_name as city_name2_0_1_,
        address1_.postal_code as postal_code3_0_1_,
        address1_.state_name as state_name4_0_1_,
        address1_.street_name as street_name5_0_1_ 
    from
        EMPLOYEE_ADDRESS_TABLE addresslis0_ 
    inner join
        address_table address1_ 
            on addresslis0_.ADDRESS_ID=address1_.address_id 
    where
        addresslis0_.EMPLOYEE_ID=?
Hibernate: 
    select
        addresslis0_.EMPLOYEE_ID as EMPLOYEE_ID1_1_0_,
        addresslis0_.ADDRESS_ID as ADDRESS_ID2_1_0_,
        address1_.address_id as address_id1_0_1_,
        address1_.city_name as city_name2_0_1_,
        address1_.postal_code as postal_code3_0_1_,
        address1_.state_name as state_name4_0_1_,
        address1_.street_name as street_name5_0_1_ 
    from
        EMPLOYEE_ADDRESS_TABLE addresslis0_ 
    inner join
        address_table address1_ 
            on addresslis0_.ADDRESS_ID=address1_.address_id 
    where
        addresslis0_.EMPLOYEE_ID=?
Employee [employeeId=1, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2016-01-02 00:00:00.000, addressList=[Address [addressId=2, street=Guy St, city=Montreal, state=Quebec, postalcode=19317], Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318], Address [addressId=4, street=University St, city=Montreal, state=Quebec, postalcode=19319]], salary=65001.0]
Employee [employeeId=5, employeeName=Saba Divanpour, email=test@gmail.com, doj=2016-02-02 12:00:00.000, addressList=[Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318], Address [addressId=4, street=University St, city=Montreal, state=Quebec, postalcode=19319]], salary=76001.0]

*/