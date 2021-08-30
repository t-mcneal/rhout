import React, { useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

const TopRatedVenues = () => {
  // const [topVenues, setTopVenues] = useState(null);

  const handleClick = () => {
    const addresses = {
      address1: "798 Brookwood Dr., Olympia Fields, IL, 60461", 
      address2: "78 E Washington St, Chicago, IL 60602"
      }
    axios.post('http://localhost:8080/api/v1/venues/halfway/top-rated', addresses)
    .then(response => console.log(response.data));
  }

  useEffect(() => {
    handleClick();
  }, [])

  return <h1>Hello</h1>
};

function App() {
  return (
  <div className="App">
    <TopRatedVenues />
  </div>
  )
}

export default App;
