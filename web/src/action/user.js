import { ActionConstant } from '../constant'
import { userService } from '../service'

export const userAction = {
    // getUsersAsync,
    getCurrentUserAsync
}

// function getUsersAsync() {
//     function getUsers(users) {return { type: ActionConstant.GET_USERS, users }}

//     return async dispatch => {
//         const json = await userService.getUsers();
//         dispatch(getUsers(json));
//     }
// }

function getCurrentUserAsync() {
    function getCurrentUser(user) {return {type: ActionConstant.GET_CURRENT_USER, user} }

    return async dispatch => {
        const user = await userService.getCurrentUser();
        dispatch(getCurrentUser(user));
    }
}