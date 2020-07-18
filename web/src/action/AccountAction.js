import { ActionConstant } from '../constant'

export const AccountAction = {
    getAccount(accounts) {
        return {type: ActionConstant.GET_ACCOUNTS, accounts};
    },
    createAccount(account) {
        return {type: ActionConstant.CREATE_ACCOUNT, account};
    }
}
