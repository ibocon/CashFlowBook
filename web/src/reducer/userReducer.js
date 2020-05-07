import { ActionConstant } from '../constant'

const userReducer = (state = [], action) => {
    switch(action.type) {
        case ActionConstant.GET_USERS:
            return action.users;
        default:
            return state;
    }
}

export default userReducer