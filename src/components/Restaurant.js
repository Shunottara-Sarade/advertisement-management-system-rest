import React from 'react';
import './Cards.css';
import CardItem from './CardItem';

function Restaurant() {
  return (
    <div className='cards'>
      <h1>Restaurants Advertisements!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
            <CardItem
              src='images/img-5.jpg'
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label='Pune'
            />
            <CardItem
              src='images/img-6.jpg'
              text='Travel through the Islands of Bali in a Private Cruise'
              label='Mumbai'
            />
          </ul>
          <ul className='cards__items'>
            {/* <CardItem
              src='images/img-9.jpg'
              text='Set Sail in the Atlantic Ocean visiting Uncharted Waters'
              label='Restaurants and bars'
              path='/services'
            /> */}
            {/* <CardItem
              src='images/img-10.jpg'
              text='Experience Football on Top of the Himilayan Mountains'
              label='Jobs'
              path='/products'
            />
            <CardItem
              src='images/img-8.jpg'
              text='Ride through the Sahara Desert on a guided camel tour'
              label='Others'
              path='/sign-up'
            /> */}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Restaurant;
