import React from 'react'
import { Redirect } from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import { UrlConstant } from '../constant'
import { connect } from 'react-redux'
import { withRouter } from "react-router"

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
        // const { user } = this.props
        // if(user) {
        //         return <Redirect to={{
        //             pathname: "/profile",
        //             state: {from: this.props.location }
        //     }}/>
        // }

        return (
            <div>
                <h1>Login</h1>
                <Button variant="primary" href={UrlConstant.GOOGLE_AUTH_URL}>Log in with Google</Button>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    user: state.user
})

const mapDispatchToProps = dispatch => ({

})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Login))