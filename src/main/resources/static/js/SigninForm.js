import React, { Component } from 'react'

class SigninForm extends Component {
    constructor(props) {
        super(props);
    }

    render() {
		return (
            <div id="signin">
                <form className="form-signin">
                    <img className="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"></img>
                    <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>
                    <label htmlFor="inputId" className="sr-only">Id</label>
                    <input type="text" id="inputId" className="form-control" placeholder="Id" required="" autoFocus=""></input>
                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password" id="inputPassword" className="form-control" placeholder="Password" required=""></input>
                    <button className="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
		)
	}
}

export default SigninForm 