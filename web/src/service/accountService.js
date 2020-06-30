import { UrlConstant } from '../constant'
import { request } from '../util'

export const AccountService = {
    getAccounts() {
        return request({
            url: UrlConstant.API_BASE_URL + "/account",
            method: 'GET'
        });
    },
    
    async createAccount(account) {
        return await request({
            url: UrlConstant.API_BASE_URL + "/account",
            method: 'POST',
            body: JSON.stringify(account)
        })
    }
}