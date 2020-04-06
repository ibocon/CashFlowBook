'use strict';

// React
import React from 'react';
import { render as _render } from 'react-dom';
import Sidebar from './js/Sidebar';
import Content from './js/Content';
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
				<Content></Content>
			</div>
		)
	}
}

_render(
	<App />,
	document.getElementById('react')
)
