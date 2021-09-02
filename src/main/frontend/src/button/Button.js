import React from 'react';

function Button(props) {
    return (
        <button type={props.type}>
            {props.value}
        </button>
    );
}

export default Button;