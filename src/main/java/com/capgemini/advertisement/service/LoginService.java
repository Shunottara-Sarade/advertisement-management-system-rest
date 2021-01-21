package com.capgemini.advertisement.service;
import com.capgemini.advertisement.entity.ForgotPassword;
import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.Login;

public interface LoginService {
   /**
    * Sign in customer
    * @param customerMaster
    * @return sign in successful
    * else throw invalid customer
    */
    public String signIn(Login customerMaster);

    /**
     * Sign out 
     * @param customerMaster
     * @return sign out successful
     */

    public String signOut(LogOutPayload customerMaster);
    
    /**
     * Change Password
     * @param customerMaster
     * @param new_password
     * @return changed password
     */
    public String changePassword(Login customerMaster, String new_password);

    public String forgotPassword(ForgotPassword customerMaster, String new_password);
}