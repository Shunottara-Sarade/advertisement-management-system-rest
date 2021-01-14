import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            ADs Management
          <i class='fab fa-typo3' />
          </Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                <i class="fa fa-home"></i>&nbsp;
                  HOME
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/contactUs'
                className='nav-links'
                onClick={closeMobileMenu}>
                {/* <i class="far fa-address-book"></i>&nbsp; */}
                <i class="fa fa-phone" aria-hidden="true"></i>&nbsp;

                  CONTACT US
              </Link>
            </li>


            {button && <Button buttonStyle='btn--outline'>LOGIN</Button>}

            <div className="btn btn-info btn-lg">
            <Link to='/Logout' >
            <span class="glyphicon glyphicon-off"></span> Log out

            </Link>
            </div>
          </ul>
        </div>
      </nav>
    </>
  );
}

export default Navbar;