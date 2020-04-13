import React, { Component } from 'react'
import SidebarToggleButton from './SidebarToggleButton'


class Navbar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
		return (
            <nav id="navbar" class="navbar navbar-expand">
                <SidebarToggleButton></SidebarToggleButton>
            </nav>
        )
	}
}

export default Navbar  