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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Profile))