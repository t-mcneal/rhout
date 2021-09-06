import React, { useState, useEffect } from 'react';
import ProgressBar from './ProgressBar';
import PropTypes from 'prop-types';

function ProgressBarContainer(props) {
    const [width, setWidth] = useState(0);
    
    useEffect(() => {
        if (props.isActive) {
            let interval = setInterval(() => {
                setWidth(prev => prev + .1);
            }, 0.1);
            return () => clearInterval(interval);
        }  else if (!props.isActive) {
            setWidth(0);
        }
    }, [props.isActive])


    return <ProgressBar style={{"--width": width}} />
}

ProgressBarContainer.propTypes = {
    isActive: PropTypes.bool
}

export default ProgressBarContainer; 