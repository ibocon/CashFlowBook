import { ActionConstant } from '../constant'

export const UserReducer = (state = {}, action) => {
    switch(action.type) {
        case ActionConstant.SET_CURRENT_USER:
            return action.user;
        case ActionConstant.LOGIN:
            return state;
        case ActionConstant.LOGOUT:
            return {};
        default:
            return state;
    }
}