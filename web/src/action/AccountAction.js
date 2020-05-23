import { ActionConstant } from '../constant'
import { AccountService } from '../service'

export const AccountAction = {
    getAccountsAsync() {
        function getAccounts(accounts) {
            return {type: ActionConstant.GET_ACCOUNTS, accounts}
        }
        
        return async dispatch => {
            const accounts = await AccountService.getAccounts();
            dispatch(getAccounts(accounts));
        }
    },
    
    showModal(show) {
        return {type: ActionConstant.SHOW_MODAL, show}
    },

    handleAccount(handler) {
        return {type: ActionConstant.CHANGE_ACCOUNTMODAL_HANDLER, handler}
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