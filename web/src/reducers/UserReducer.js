import { GET_USERS } from '../actions'

const UserReducer = (state = [], action) => {
    switch(action.type) {
        case GET_USERS:
            return action.users;
        default:
            return state;
    }
}

export default UserReducer