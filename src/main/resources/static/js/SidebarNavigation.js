import React, { Component } from 'react'

class SidebarNavigation extends Component {
    constructor(props) {
		super(props);
    }

    render() {
		return (
            <ul class="sidebar-nav list-unstyled">
                <li class="sidebar-header">Main</li>
                <li class="sidebar-item"><a href="#dashboards" class="sidebar-link">Dashboards</a></li>
                <li class="sidebar-item">
                    <a href="#test" data-toggle="collapse" class="sidebar-link dropdown-toggle">
                        <i class="align-middle mr-2 fas fa-fw fa-home"></i> <span class="align-middle">Menus</span>
                    </a>
                    <ul id="test" class="sidebar-dropdown list-unstyled collapse" data-parent="#sidebar">
                        <li class="sidebar-item"><a class="sidebar-link" href=".">menu A</a></li>
                        <li class="sidebar-item"><a class="sidebar-link" href=".">menu B</a></li>
                    </ul>
                </li>
            </ul>
		)
	}
}

export default SidebarNavigation 