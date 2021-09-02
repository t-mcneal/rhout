import React from 'react';
import Button from '../button/Button';
import PropTypes from 'prop-types';

function Form(props) {
    return (
        <form id="topRatedForm" onSubmit={props.handleSubmit}>
            <label htmlFor="txtAddress1">Address 1</label>
            <input 
                type="text"
                id="txtAddress1" 
                name="address1" 
                value={props.address1}
                onChange={props.handleChange} 
                 />
            <br />
            <label htmlFor="txtAddress2">Address 2</label>
            <input 
                type="text"
                id="txtAddress2" 
                name="address2" 
                value={props.address2}
                onChange={props.handleChange} 
                 />
            <br />
            <Button type="submit" value="Search Top Rated"/>
        </form>
    );
}

Form.propTypes = {
    address1: PropTypes.string,
    address2: PropTypes.string
}

export default Form;