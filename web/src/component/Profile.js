import React from 'react'
import { connect } from 'react-redux'
import { withRouter, Redirect } from "react-router"
import { isEmpty } from '../util';

class Profile extends React.Component {

    render() {
        const { user } = this.props;

        if(isEmpty(user)) {
            return (            
                <Redirect
                    to={{
                    pathname: '/login',
                    state: { from: this.props.location }
                    }}
                />
            )
        }

        return (
            <div>
                <h1>Profile</h1>
                { user.imageUrl && <img width="256" src={user.imageUrl} alt={user.name}/> }
                <h1>{user.name}</h1>
                <h1>{user.email}</h1>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    user: state.user
  })
  
  const mapDispatchToProps = dispatch => ({

  })
  
  export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Profile))