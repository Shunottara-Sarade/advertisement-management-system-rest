import React from 'react';
import './Cards.css';
import CardItem from './CardItem';

function Electronic() {
  return (
    <div className='cards'>
      <h1>Electronics Advertisements!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
            <CardItem
              src='images/img-7.png'
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label='Pune'
            />
            <CardItem
              src='images/img-8.jpg'
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

export default Electronic;
