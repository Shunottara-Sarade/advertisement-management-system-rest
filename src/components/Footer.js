import React from 'react';
import './Footer.css';
import { Button } from './Button';
import { Link } from 'react-router-dom';

function Footer() {
  return (
    <div className='footer-container'>
      {/* <section className='footer-subscription'> */}
        <p className='footer-subscription-heading'>
          All right reserved to add management teams
        </p>
    </div>
  );
}

export default Footer;
