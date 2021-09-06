import React from 'react';
import Button from '../../button/Button';
import PropTypes from 'prop-types';
import './Form.css';

function TopVenuesForm(props) {
    return (
        <form id="topRatedForm" onSubmit={props.handleSubmit}>
            <label htmlFor="txtAddress1">Address 1</label>
            <div className="input-wrapper">
                <input 
                    type="text"
                    id="txtAddress1" 
                    name="address1" 
                    value={props.address1}
                    onChange={props.handleChange} 
                    />
            </div>
            
            <br />
            <label htmlFor="txtAddress2">Address 2</label>
            <div className="input-wrapper">
                <input 
                    type="text"
                    id="txtAddress2" 
                    name="address2" 
                    value={props.address2}
                    onChange={props.handleChange} 
                    />
            </div> 
            <br />
            <Button type="submit" value="Search Top Rated"/>
        </form>
    );
}

TopVenuesForm.propTypes = {
    address1: PropTypes.string,
    address2: PropTypes.string,
    handleChange: PropTypes.func,
    handleSubmit: PropTypes.func
}

export default TopVenuesForm;