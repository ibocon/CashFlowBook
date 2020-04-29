import { connect } from 'react-redux'
import UserList from '../components/UserList'
import { getUsers } from '../actions'

const mapStateToProps = state => ({
    users: state.users
})

const mapDispatchToProps = dispatch => ({
    onClick: e => {
        e.preventDefault()
        const users = [{"id":1,"name":"Aliko"},{"id":2,"name":"Bill"},{"id":3,"name":"Yegun"}]; //UserService.getUsers();
        dispatch(getUsers(users))
    }  
})

export default connect(mapStateToProps, mapDispatchToProps)(UserList)