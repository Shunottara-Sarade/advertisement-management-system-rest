package com.capgemini.advertisement.service;

 

import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.entity.StaffLogin;

 

public interface StaffLoginService {
    /**
     * sign in staff
     * @param staff
     * @return sign in successful
     * else throw invalid staff
     */
    public String signIn(StaffLogin staff);
    
    /**
     * sign out staff
     * @param staff
     * @return sign out successful
     * 
     */
    public String signOut(LogOutPayload staff);

 
    /**
     * change password
     * @param staff
     * @param new_password
     * @return changed password
     */
    public String changePassword(StaffLogin staff, String new_password);


}