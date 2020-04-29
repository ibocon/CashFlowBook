import { combineReducers } from 'redux'
import users from './users'
import { GET_USERS } from '../actions'

const initialState = {
    users: [
        {
            id: 1,
            name: "fuck"
        }
    ]
}

function Reducer(state = initialState, action) {
    switch(action.type) {
        case GET_USERS:
            return {
                ...state,
                users: action.users
            }
        default:
            return state;
    }
}
export default Reducer

// export default combineReducers ({
//     users
// })