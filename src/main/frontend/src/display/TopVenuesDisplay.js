import React from 'react';

function TopVenuesDisplay(props) {
    const venueCards = props.topVenues.map(venue => (
        <div key={venue.id} className="place-card">
            <h3 className="place-name">{venue.name}</h3>

            <div className="place-address"><b>Address: </b>{venue.address}</div>

            <div className="place-rating"><b>Rating:</b> {venue.rating.toFixed(1)} stars</div>
        </div>
    ));

    return (
        <div>
            <h2>Top 5 Venues</h2>

            <div>{venueCards}</div>
        </div>
    );
}

export default TopVenuesDisplay;