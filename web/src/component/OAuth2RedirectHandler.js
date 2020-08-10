import React from 'react'
import { Redirect } from 'react-router-dom'
import { UrlConstant } from '../constant'

class OAuth2RedirectHandler extends React.Component {
    getUrlParameter(name) {
        name = name.replace(/[[]/, '\\[').replace(/[\]]/, '\\]')
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)')

        var results = regex.exec(this.props.location.search)
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '))
    };

    render() {
        const error = this.getUrlParameter('error');
        if(error){
            return <Redirect to={{
                pathname: "/error",
                state: { 
                    from: this.props.location,
                    error: error 
                }
            }}/>
        }

        const token = this.getUrlParameter('token');
        if(token) {
            localStorage.setItem(UrlConstant.ACCESS_TOKEN, token)
            return <Redirect to={{
                pathname: "/profile",
                state: { from: this.props.location }
            }}/>
        } else {
            return <Redirect to={{
                pathname: "/",
                state: { from: this.props.location }
            }}/>
        }
    }
}

export default OAuth2RedirectHandler;