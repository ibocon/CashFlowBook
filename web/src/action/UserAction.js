import { ActionConstant } from '../constant'
import { userService } from '../service'

export const UserAction = {
    login() {
        return {type: ActionConstant.LOGIN }
    },
    
    logout() {
        const user = {};
        return {type: ActionConstant.LOGOUT, user }
    },

    SetCurrentUser(user) {
        return {type: ActionConstant.SET_CURRENT_USER, user}
    },
}
