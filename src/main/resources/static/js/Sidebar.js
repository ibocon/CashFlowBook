import React from 'react';

class Sidebar extends React.Component {
    constructor(props) {
		super(props);
    }

    render() {
		return (
            <nav id="sidebar">
                <a class="sidbar-brand" href="."><h3>CashFlow Book</h3></a>
                <div class="sidebar-content">
                    <div class="sidebar-customer">
                        {/* <img src="img/avatar.jpg" class="img-fluid rounded-circle mb-2" alt="Yegun Kim" /> */}
                        <div class="font-weight-bold">Yegun Kim</div>
                        <small>Software Developer</small>
                    </div>
                    <ul class="sidebar-nav">
                        <li class="sidebar-header">Main</li>
                        <li class="sidebar-item active">
                            <a href="#dashboards" data-toggle="collapse" class="sidebar-link dropdown-toggle" aria-expanded="true">
                                <i class="align-middle mr-2 fas fa-fw fa-home"></i> <span class="align-middle">Dashboards</span>
                            </a>
                            <ul id="dashboards" class="sidebar-dropdown list-unstyled collapse show" data-parent="#sidebar">
                                <li class="sidebar-item active"><a class="sidebar-link" href=".">Default</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href=".">Analytics</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href=".">E-commerce</a></li>
                            </ul>
                        </li>
                        <li class="sidebar-item">
                            <a href="#pages" data-toggle="collapse" class="sidebar-link collapsed dropdown-toggle">
                                <i class="align-middle mr-2 fas fa-fw fa-file"></i> <span class="align-middle">Pages</span>
                            </a>
                            <ul id="pages" class="sidebar-dropdown list-unstyled collapse " data-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-settings.html">Settings</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-clients.html">Clients <span class="sidebar-badge badge badge-pill badge-primary">New</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-invoice.html">Invoice</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-pricing.html">Pricing</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-tasks.html">Tasks</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-blank.html">Blank Page</a></li>
                            </ul>
                        </li>
                        <li class="sidebar-header">Elements</li>
                        <li class="sidebar-item">
                            <a href="#charts" data-toggle="collapse" class="sidebar-link collapsed dropdown-toggle">
                                <i class="align-middle mr-2 fas fa-fw fa-chart-pie"></i> <span class="align-middle">Charts</span>
                                <span class="sidebar-badge badge badge-pill badge-primary">New</span>
                            </a>
                            <ul id="charts" class="sidebar-dropdown list-unstyled collapse " data-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="charts-chartjs.html">Chart.js</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="charts-apexcharts.html">ApexCharts</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
		)
	}
}

export default Sidebar 