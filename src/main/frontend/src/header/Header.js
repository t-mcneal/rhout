import React from 'react';
import './Header.css';

function Header() {
    return (
        <header>
            <nav><a id="rhoutLink" href="http://localhost:3000">Rhout</a></nav>

            <div id="rhoutBox">
                <h1>Rhout</h1>
                
                <p>The destination for two.</p>
            </div>
        </header>

    )
}

export default Header;