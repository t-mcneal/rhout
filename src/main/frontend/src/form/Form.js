import React, { useState, useEffect } from 'react';
import { Button } from '../button/Button'
import axios from 'axios';

function Form() {
    const [values, setValues] = useState(null);

    const handleClick = () => {
        const addresses = {
            address1: "798 Brookwood Dr., Olympia Fields, IL, 60461", 
            address2: "78 E Washington St, Chicago, IL 60602"
            }
          axios.post('http://localhost:8080/api/v1/venues/halfway/top-rated', addresses)
          .then(response => console.log(response.data));

    }

    const handleChange = ({ target }) => {
        const { name, value } = target;
        setValues((prev) => ({...prev, [name]: value}));
    }

    return (
        <form id="topRatedForm">
            <label for="txtAddress1">Address 1</label>
            <input 
                type="text"
                id="txtAddress1" 
                name="address1" 
                onChange={handleChange} 
                value={values.address1} />
            <br />
            <label for="txtAddress2">Address 2</label>
            <input 
                type="text"
                id="txtAddress2" 
                name="address2" 
                onChange={handleChange} 
                value={values.address2} />
            <br />
            <Button type="submit" value="Find top rated" handleClick={handleClick}/>
        </form>
    )
}

export default Form;