'use strict';

// React
import React, { Component } from 'react'
import { render as _render } from 'react-dom'
import Sidebar from './js/Sidebar'
import Main from './js/Main'
import SigninForm from './js/SigninForm'

import './style.sass'

class App extends Component {

	constructor(props) {
		super(props);
		this.state = {
			isLogin: false
		};
	}

	componentDidMount() {
		
	}

	render() {
		if(this.state.isLogin) {
			return (
				<div className="wrapper">
					<Sidebar></Sidebar>
					<Main></Main>
				</div>
			)
		}
		else {
			return (
				<SigninForm></SigninForm> 
			)
		}
	}
}

_render(
	<App />,
	document.getElementById('react')
)
