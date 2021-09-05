import React, { useState, useEffect } from 'react';
import ProgressBar from './ProgressBar';

function ProgressBarContainer(props) {
    const [width, setWidth] = useState(0);
    
    // useEffect(() => {
    //     if (props.isActive) {
    //         // Add interval() code here for setting the width
    //     }

    // }, [props.isActive])


    return <ProgressBar style={{"--width": {width}}} />
}

export default ProgressBarContainer; 