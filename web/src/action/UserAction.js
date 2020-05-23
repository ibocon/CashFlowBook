import { ActionConstant, UrlConstant } from '../constant'
import { userService } from '../service'

export const UserAction = {
    login() {
        return {type: ActionConstant.LOGIN }
    },
    
    logout() {
        localStorage.removeItem(UrlConstant.ACCESS_TOKEN);
        const user = {};
        return {type: ActionConstant.LOGOUT, user }
    },
    
    getCurrentUserAsync() {
        function getCurrentUser(user) {return {type: ActionConstant.GET_CURRENT_USER, user} }
    
        return async dispatch => {
            const user = await userService.getCurrentUser();
            dispatch(getCurrentUser(user));
        }
    }   
}
