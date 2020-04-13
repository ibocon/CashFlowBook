import React, { Component } from 'react'
import Navbar from './Navbar'

class Main extends Component {
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