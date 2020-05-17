import React from "react";
import {
  Route,
  Redirect,
  withRouter
} from "react-router-dom";
import { connect } from 'react-redux';
import { isEmpty } from '../util';

class PrivateRoute extends React.Component {
    render() {
        const { children, user, ...rest } = this.props;

        if(isEmpty(user)) {
            return (            
                <Redirect
                    to={{
                    pathname: '/',
                    state: { from: this.props.location }
                    }}
                />
            )
        }
        else {
            return(
                <Route {...rest} render={children} />
            )
        }
    }
}

const mapStateToProps = state => ({
    user: state.user
})
  
const mapDispatchToProps = dispatch => ({

})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(PrivateRoute))