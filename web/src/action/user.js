import { ActionConstant, UrlConstant } from '../constant'
import { userService } from '../service'

export const userAction = {
    // getUsersAsync,
    login,
    logout,
    getCurrentUserAsync,
}

// 샘플 코드
// function getUsersAsync() {
//     function getUsers(users) {return { type: ActionConstant.GET_USERS, users }}

//     return async dispatch => {
//         const json = await userService.getUsers();
//         dispatch(getUsers(json));
//     }
// }

function login() {
    return {type: ActionConstant.LOGIN }
}

function logout() {
    localStorage.removeItem(UrlConstant.ACCESS_TOKEN);
    const user = {};
    return {type: ActionConstant.LOGOUT, user }
}

function getCurrentUserAsync() {
    function getCurrentUser(user) {return {type: ActionConstant.GET_CURRENT_USER, user} }

    return async dispatch => {
        const user = await userService.getCurrentUser();
        dispatch(getCurrentUser(user));
    }
}
