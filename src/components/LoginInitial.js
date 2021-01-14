import React from 'react';
import './HeroSection.css';
import './Button.css';
import Button from './Button';
import Hotel from './Hotel';
import { Link } from 'react-router-dom';

export default function LoginInitial() {

  return (

    <div className='LoginAs'>
      <div className='logintag'>
        <h1>Login As</h1>
      </div>
      <div>

        <Link to='/login' className='btn-mobile'>

          <button class="buttonCust" >Customer</button>
        </Link>
        <Link to='/home' className='btn-mobile'>

          <button class="buttonStaf" variant="outline-primary">

            Staff</button>
        </Link>


      </div>

    </div>

  );



}