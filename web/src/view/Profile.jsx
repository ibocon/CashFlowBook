import React from 'react'
import { connect } from 'react-redux'
import { withRouter, Redirect } from "react-router"
import { isEmpty } from '../util'

class _Profile extends React.Component {

    render() {
        const { user } = this.props;

        console.log("[DEBUG] " + user);
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

        return (
            <div>
                <h1>Profile</h1>
                <p>사용자 정보나 설정을 표시하는 페이지</p>
                { user.imageUrl && <img width="128" src={user.imageUrl} alt={user.name}/> }
                <p>{user.name}</p>
                <p>{user.email}</p>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    user: state.user
  })
  
const mapDispatchToProps = dispatch => ({

})

export const Profile = withRouter(connect(mapStateToProps, mapDispatchToProps)(_Profile))