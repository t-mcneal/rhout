import React, { useState } from 'react';
import Form from './Form';
import TopVenuesDisplay from '../display/TopVenuesDisplay';
import ProgressBarContainer from '../progress_bar/ProgressBarContainer';
import axios from 'axios';

function FormContainer() {
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
        console.log(response);
    }

    return (
        <div id="form-container">
            <section>
                <h2>Music Venues</h2>
                
                <p>Find the top 5 highest-rated music venues halfway between two addresses.</p>

                <Form 
                    address1={addresses.address1} 
                    address2={addresses.address2} 
                    handleChange={handleAddressChange} 
                    handleSubmit={handleSubmit} 
                    />
            </section>
            <section>
                <ProgressBarContainer isActive={isSearching} />

                <h2>Top 5 Venues</h2>

                <TopVenuesDisplay topVenues={topVenues} />
            </section>
        </div>
    );
}

export default FormContainer;