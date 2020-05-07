import { ActionConstant } from '../constant'
import { userService } from '../service'

export const testAction = {
    getUsersAsync,
}

function getUsersAsync() {
    function getUsers(users) {return { type: ActionConstant.GET_USERS, users }}

    return async dispatch => {
        const json = await userService.getUsers()
        dispatch(getUsers(json))
    }
}