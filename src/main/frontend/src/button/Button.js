import React from 'react';

function Button(props) {
    return (
        <button type={props.type} onClick={props.handleClick}>
            {props.value}
        </button>
    )
}

export default Button;