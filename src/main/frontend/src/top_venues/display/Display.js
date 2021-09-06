import React from 'react';
import './Display.css';
import PropTypes from 'prop-types';

const GMAP_URL = 'https://www.google.com/maps/search/?api=1&query=music+venues&query_place_id=';

function TopVenuesDisplay(props) {
    const makeStars = (rating) => {
        let nums = [1, 2, 3, 4, 5];
        let stars = nums.map(num => {
            if (rating >= num) {
                return <span className="star star-full">&#9733;</span>;
            } else if (rating < num && rating > num - 1) {
                return <span className="star star-half">&#9733;</span>;
            } else {
                return <span className="star star-empty">&#9733;</span>;
            }
        })
        return stars;
    }

    const venueCards = props.topVenues.map(venue => (
        <div key={venue.id} className="place-card">
            <h3 className="place-name">{venue.name}</h3>

            <div className="place-address">
                <b>Address:</b> {venue.address}
            </div>

            <div className="place-rating">
                <b>Rating:</b> {venue.rating.toFixed(1)} 
                <span className="five-stars"> {makeStars(venue.rating)} </span>
            </div>

            <a className="place-link" 
                href={GMAP_URL + venue.id} 
                rel="noreferrer" 
                target="_blank">
                    View on Google Maps
            </a>
        </div>
    ));

    return (
        <div id="topVenuesDisplay">
            <div>{venueCards}</div>
        </div>
    );
}

TopVenuesDisplay.propTypes = {
    topVenues: PropTypes.array
}

export default TopVenuesDisplay;