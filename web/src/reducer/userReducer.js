import { ActionConstant } from '../constant'

const userReducer = (state = [], action) => {
    switch(action.type) {
        case ActionConstant.GET_CURRENT_USER:
            return action.user;
        default:
            return state;
    }
}

export default userReducer