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
        // TODO: 덮어 써지는 문제가 있음
        case ActionConstant.REGISTER_ACCOUNT_HANDLER:
            return Object.assign({}, state, {
                modal: {
                    show: state.modal.show,
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