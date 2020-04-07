import React from 'react';
import SidebarCustomer from './SidebarCustomer';
import SidebarNavigation from './SidebarNavigation';

class Sidebar extends React.Component {
    constructor(props) {
		super(props);
    }

    render() {
		return (
            <nav id="sidebar">
                <a class="sidebar-brand" href="."><h3>CashFlow Book</h3></a>
                <div class="sidebar-content">
                    <SidebarCustomer></SidebarCustomer>
                    <SidebarNavigation></SidebarNavigation>
                </div>
            </nav>
		)
	}
}

export default Sidebar 