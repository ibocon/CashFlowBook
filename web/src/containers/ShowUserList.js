import { connect } from 'react-redux'
import UserList from '../components/UserList'
import { getUsers } from '../actions'

const mapStateToProps = state => ({
    users: state.users
})

const mapDispatchToProps = dispatch => ({
    onClick: e => {
        e.preventDefault();
        fetch("http://localhost:8081/users")
        .then(
            response => response.json(),
            error => console.log(error)
        )
        .then(json => {
            dispatch(getUsers(json))
        })
    }  
})

export default connect(mapStateToProps, mapDispatchToProps)(UserList)