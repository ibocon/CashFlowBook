'use strict';

import React from 'react';
import { render as _render } from 'react-dom';
const superagent = require('superagent');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {customers: []};
	}

	componentDidMount() {
		// this.setState({customers: response.entity._embedded.customers});
		superagent.get('/api/customers').set('Accept', 'application/hal+json').then( response => {
			this.setState({customers: response.body._embedded.customers});
		});
	}

	render() {
		return (
			<CustomerList customers={this.state.customers}/>
		)
	}
}
class CustomerList extends React.Component{
	render() {
		const customers = this.props.customers.map(customer =>
			<Customer key={customer._links.self.href} customer={customer}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
					</tr>
					{customers}
				</tbody>
			</table>
		)
	}
}
class Customer extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.customer.name}</td>
			</tr>
		)
	}
}
_render(
	<App />,
	document.getElementById('react')
)
