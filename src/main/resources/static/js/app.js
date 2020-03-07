'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {customers: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/customers'}).done(response => {
			this.setState({customers: response.entity._embedded.customers});
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
						<th>ID</th>
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

ReactDOM.render(
	<App />,
	document.getElementById('react')
)

