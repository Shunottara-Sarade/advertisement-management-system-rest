package com.capgemini.advertisement.advertisementmanagementsystemrest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.Role;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.StaffException;
import com.capgemini.advertisement.service.StaffServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StaffServiceSpringDataImplTest {
	@Mock
	private StaffSpringDataDAO staffSpringDataDao;
	
	@Mock
	private Staff staff;
	
	@InjectMocks
	private StaffServiceImpl staffService;
	

	@Test
	void testAddStaff() throws StaffException {
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");
			
		given(staffSpringDataDao.save(staff)).willReturn(staff);
		Integer savedStaff=staffService.addStaff(staff);
		Assertions.assertThat(savedStaff).isNotNull();
		verify(staffSpringDataDao).save(any(Staff.class));
	}

	@Test
	void testGetStaffById() throws StaffException {
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");
		given(staffSpringDataDao.findById(1)).willReturn(Optional.of(staff));
		Staff getStaffId= staffService.getStaffById(1);
		Assertions.assertThat(getStaffId).isNotNull();
		
	}

	@Test
	void testDeleteStaff() throws StaffException {
		Integer staffId=1;
		staffService.deleteStaff(staffId);
		staffService.deleteStaff(staffId);
		verify(staffSpringDataDao,times(2)).deleteById(staffId);
	}

	@Test
	void testGetAllStaff() throws StaffException {
		List<Staff>staffList=new ArrayList<Staff>();
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");
		
		 given(staffSpringDataDao.findAll()).willReturn(staffList);
		List<Staff>staffList1=staffService.getAllStaff();
		Assertions.assertThat(staffList1).isEqualTo(staffList);
	}

	@Test
	void testUpdateStaff() throws StaffException {
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");;
		given(staffSpringDataDao.save(staff)).willReturn(staff);
		Staff expectedStaff=staffService.updateStaff(staff);
		Assertions.assertThat(expectedStaff).isNotNull();
	    
	    verify(staffSpringDataDao).save(any(Staff.class));
	}

}