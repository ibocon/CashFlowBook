import { connect } from 'react-redux'
import UserList from '../component/UserList'
import { testAction } from '../action'
import { withRouter } from "react-router";

const mapStateToProps = state => ({
    users: state.users
})

const mapDispatchToProps = dispatch => ({
    onClick: e => {
        e.preventDefault();
        dispatch(testAction.getUsersAsync());
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(UserList))