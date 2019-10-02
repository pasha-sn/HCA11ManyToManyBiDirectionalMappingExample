package com.hibernateinfo.client;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.service.EmployeeService;
import com.hibernateinfo.service.impl.EmployeeServiceImpl;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest2FetchDataUsingEmployeeId {

	public static void main(String[] args) 
	{
		getEmployeeAndAddressByEmployeeId();
	}	
	
	
	
	private static void getEmployeeAndAddressByEmployeeId() 
	{	
		EmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = employeeService.getEmployeeById(1);
		System.out.println(employee);
		System.out.println("**********************************");
		if (employee != null) {
			employee.getAddressList().forEach(System.out::println);
		}
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
fetch=FetchType.EAGER Hibernate will hit database just once!
Hibernate: 
    select
        employee0_.employee_id as employee_id1_2_0_,
        employee0_.date_of_join as date_of_join2_2_0_,
        employee0_.email as email3_2_0_,
        employee0_.employee_name as employee_name4_2_0_,
        employee0_.salary as salary5_2_0_,
        addresslis1_.EMPLOYEE_ID as EMPLOYEE_ID1_1_1_,
        address2_.address_id as ADDRESS_ID2_1_1_,
        address2_.address_id as address_id1_0_2_,
        address2_.city_name as city_name2_0_2_,
        address2_.postal_code as postal_code3_0_2_,
        address2_.state_name as state_name4_0_2_,
        address2_.street_name as street_name5_0_2_ 
    from
        employee_table employee0_ 
    left outer join
        EMPLOYEE_ADDRESS_TABLE addresslis1_ 
            on employee0_.employee_id=addresslis1_.EMPLOYEE_ID 
    left outer join
        address_table address2_ 
            on addresslis1_.ADDRESS_ID=address2_.address_id 
    where
        employee0_.employee_id=?
Employee [employeeId=1, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2019-01-02 00:00:00.000, addressList=[Address [addressId=2, street=Guy St, city=Montreal, state=Quebec, postalcode=19317], Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318], Address [addressId=4, street=University St, city=Montreal, state=Quebec, postalcode=19319]], salary=65001.0]
**********************************
Address [addressId=2, street=Guy St, city=Montreal, state=Quebec, postalcode=19317]
Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318]
Address [addressId=4, street=University St, city=Montreal, state=Quebec, postalcode=19319]


*/