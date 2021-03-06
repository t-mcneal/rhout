import React, { useState } from 'react';
import TopVenuesForm from './form/Form';
import TopVenuesDisplay from './display/Display';
import ProgressBarContainer from '../progress_bar/Container';
import musicFestivalImg from '../images/music_festival.jpg';
import axios from 'axios';
import './Container.css';

function TopVenuesContainer() {
    const [addresses, setAddresses] = useState({
        address1:'', 
        address2:''
    });
    
    const handleAddressChange = ({ target }) => {
        const { name, value } = target;
        setAddresses((prev) => ({...prev, [name]: value}));
    }

    const [topVenues, setTopVenues] = useState([]);
    const [isSearching, setIsSearching] = useState(false);
    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSearching(true);
        let response = await axios.post('http://localhost:8080/api/v1/venues/halfway/top-rated', addresses)
        .catch(error => console.log(error));
        setIsSearching(false);
        setTopVenues(response.data);
    }

    return (
        <div id="topVenuesContainer">
            <section>
                <div id="formContainer">
                    <h2>Music Venues</h2>
                    
                    <p>Find the top 5 highest-rated music venues halfway between two locations.</p>
    
                    <TopVenuesForm 
                        address1={addresses.address1} 
                        address2={addresses.address2} 
                        handleChange={handleAddressChange} 
                        handleSubmit={handleSubmit} />

                    {isSearching && <ProgressBarContainer isActive={isSearching} />}
                </div>

                <img id="imgFestival" src={musicFestivalImg} alt="Music Festival"/>   
            </section>
            <section>
                <TopVenuesDisplay topVenues={topVenues} />
            </section>
        </div>
    );
}

export default TopVenuesContainer;