import { ActionConstant } from '../constant'
import { AccountService } from '../service'

export const AccountAction = {
    getAccountAsync(){
        function getAccount(accounts) {
            return {type: ActionConstant.GET_ACCOUNTS, accounts}
        }
        return async dispatch => {
            const accounts = await AccountService.getAccounts();
            dispatch(getAccount(accounts))
        }
    },
    createAccountAsync(account) {
        function createAccount() {
            return {type: ActionConstant.CREATE_ACCOUNT}
        }
        return async dispatch => {
            await AccountService.createAccount(account);
            dispatch(createAccount())
        }
    }
}
