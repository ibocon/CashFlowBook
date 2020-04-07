import React from 'react';
import Navbar from './Navbar';

class Main extends React.Component {
    constructor(props) {
        super(props);   
    }

    render() {
		return (
            <div id="main">
                <Navbar></Navbar>
            </div>
		)
	}
}

export default Main 