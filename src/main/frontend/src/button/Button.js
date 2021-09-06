import React from 'react';
import './Button.css';
import PropTypes from 'prop-types';

function Button(props) {
    return (
        <button type={props.type} onClick={props.handleClick}>{props.value}</button>
    );
}

Button.propTypes = {
    type: PropTypes.string,
    value: PropTypes.string,
    handleClick: PropTypes.func,
}

export default Button;