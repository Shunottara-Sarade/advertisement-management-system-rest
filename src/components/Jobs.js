import React from 'react';
import './Cards.css';
import CardItem from './CardItem';

function Jobs() {
  return (
    <div className='cards'>
      <h1>Job Advertisements!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
            <CardItem
              src='images/img-1.jpg'
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label='Pune'
            />
            <CardItem
              src='images/img-2.jpg'
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

export default Jobs;
