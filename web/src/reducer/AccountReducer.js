import { ActionConstant } from '../constant'

const initialState = {
    list: [],
    modal: {
        show: false,
        account: {
            base: null,
            name: null
        },
        handler: null
    }
}

const AccountReducer = (state = initialState, action) => {
    switch(action.type) {
        case ActionConstant.GET_ACCOUNTS:
            return Object.assign({}, state, {
                list: action.accounts
            });
        case ActionConstant.SHOW_MODAL:
            return Object.assign({}, state, {
                modal: {
                    show: action.show
                }
            })
        case ActionConstant.CHANGE_ACCOUNTMODAL_HANDLER:
            return Object.assign({}, state, {
                modal: {
                    handler: action.handler
                }
            })
        case ActionConstant.CREATE_ACCOUNT:
            return state;
        default:
            return state;
    }
}

export default AccountReducer