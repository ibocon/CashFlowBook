import React from 'react';
import SidebarToggleButton from './SidebarToggleButton';


class Navbar extends React.Component {
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