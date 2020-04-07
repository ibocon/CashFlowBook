'use strict';

// React
import React from 'react';
import { render as _render } from 'react-dom';
import Sidebar from './js/Sidebar';
import Main from './js/Main';
import './style.sass';

class App extends React.Component {

	constructor(props) {
		super(props);
	}

	componentDidMount() {
		
	}

	render() {
		return (
			<div class="wrapper">
				<Sidebar></Sidebar>
				<Main></Main>
			</div>
		)
	}
}

_render(
	<App />,
	document.getElementById('react')
)
