import React from 'react'
import { Redirect, Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import { UrlConstant } from '../constant'

class Login extends React.Component {

    componentDidMount() {
        if(this.props.location.state && this.props.location.state.error) {
            console.error(this.props.location.state.error);
            this.props.history.replace({
                pathname: this.props.location.pathname,
                state: {}
            });
        }
    }

    render() {
        if(this.props.authenticated) {
                return <Redirect to={{
                    pathname: "/",
                    state: {from: this.props.location }
            }}/>
        }

        return (
            <div>
                <Button variant="primary" href={UrlConstant.GOOGLE_AUTH_URL}>Log in with Google</Button>
                <Link to="/signup">Sign Up</Link>
            </div>
        )
    }
}

export default Login