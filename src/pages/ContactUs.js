import React from 'react';
import '../css/Apps.css';
 
export default function ContactUs() {
  return (
      
    <div className='contactUs'>
      <h3 className='contact-tag'>Contact Us</h3>
      <p className='para1'>Need to get in touch with our Team? We’re all ears! Here are some ways to contact us.
      Feel free to reach us through our email.<br></br></p>

 
      <div>
          {/* <h1 className='visit-tag'>
            Visit our Support Centre</h1>
            <p className='para1'>Learn how to get started
              with AdsManagementTeam,<br></br> set up your Account or just take a look at our FAQs section.
               </p> */}
 
        <div>
          <h1 className='email-tag'>
            Email us</h1>
          <p className='para1'>Our Support Team is always happy
<br></br>to answer your questions.
 We will reply as soon as possible!
</p>
        </div>
 
      </div>
      {/* <br></br><br></br>
      <Footer></Footer> */}
    </div>
    
 
  );
}