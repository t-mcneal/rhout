import React from 'react';
import './ProgressBar.css';
import PropTypes from 'prop-types';

function ProgressBar(props) {
    return <div className='progress-bar' style={props.style} data-label=""></div>
}

ProgressBar.propTypes = {
    style: PropTypes.object
}

export default ProgressBar;