import React from 'react';
import './Cards.css';
import CardItem from './CardItem';

function Cards() {
  return (
    <div className='cards'>
      <h1>Check out these EPIC Advertisements!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
            <CardItem
              src='images/img-11.jpg'
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label='Hotels'
              path='/hotel'
            />
            <CardItem
              src='images/img-12.png'
              text='Travel through the Islands of Bali in a Private Cruise'
              label='Electronics'
              path='/electronic'
            />
          </ul>
          <ul className='cards__items'>
            <CardItem
              src='images/img-13.jpeg'
              text='Set Sail in the Atlantic Ocean visiting Uncharted Waters'
              label='Restaurants and bars'
              path='/restaurant'
            />
            <CardItem
              src='images/img-10.jpg'
              text='Experience Football on Top of the Himilayan Mountains'
              label='Jobs'
              path='/jobs'
            />
            <CardItem
              src='images/img-14.jpg'
              text='Ride through the Sahara Desert on a guided camel tour'
              label='Others'
              path='/sign-up'
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Cards;
