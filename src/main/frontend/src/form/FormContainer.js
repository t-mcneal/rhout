import React, { useState } from 'react';
import Form from './Form';
import TopVenuesDisplay from '../display/TopVenuesDisplay';
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
    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('started');
        let response = await axios.post('http://localhost:8080/api/v1/venues/halfway/top-rated', addresses)
        .catch(error => console.log(error));
        setTopVenues(response.data);
        console.log(response);
        console.log("Complete");
    }


    // useEffect(() => {
        
    // }, [])

    return (
        <section>
            <Form address1={addresses.address1} address2={addresses.address2} handleChange={handleAddressChange} handleSubmit={handleSubmit} />
            <TopVenuesDisplay topVenues={topVenues} />
        </section>
    );
}

export default FormContainer;