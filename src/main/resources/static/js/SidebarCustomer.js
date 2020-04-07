import React from 'react';

class SidebarCustomer extends React.Component {
    constructor(props) {
		super(props);
    }

    render() {
		return (
            <div class="sidebar-customer">
                <img src="img/avatar.jpg" class="img-fluid rounded-circle mb-2" alt="Yegun Kim" />
                <div class="font-weight-bold">Yegun Kim</div>
                <small>Software Developer</small>
            </div>
		)
	}
}

export default SidebarCustomer 