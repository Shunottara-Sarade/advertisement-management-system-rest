import React from 'react';
import './Cards.css';
import CardItem from './CardItem';

function Hotel() {
  return (
    <div className='cards'>
      <h1>Hotel Advertisements!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
            <CardItem
              src='images/img-3.jpg'
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label='Pune'
            />
            <CardItem
              src='images/img-4.jpg'
              text='Travel through the Islands of Bali in a Private Cruise'
              label='Mumbai'
            />
          </ul>
          <ul className='cards__items'>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Hotel;
