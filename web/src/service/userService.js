import { UrlConstant } from '../constant'
import { request } from '../util'

export const userService = {
    getCurrentUser,
}

async function getCurrentUser() {
    return await request({
        url: UrlConstant.API_BASE_URL + "/user/me",
        method: 'GET'
    });
}



