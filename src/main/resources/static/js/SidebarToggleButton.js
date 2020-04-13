import React, { Component } from 'react'

class SidebarToggleButton extends Component {
    constructor(props) {
        super(props);
    }

    toggleSidebar() {
        $('#sidebar').toggleClass('active');
    }

    render() {
		return (
            <a class="sidebar-toggle d-flex mr-2" onClick={this.toggleSidebar}>
                <i class="fas fa-bars fa-2x"></i>
            </a>
		)
	}
}

export default SidebarToggleButton 