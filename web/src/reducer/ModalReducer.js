import { ActionConstant } from '../constant'

const initialState = {
    show: false,
    account: {
        base: null,
        name: null
    },
    handler: null
}

export const ModalReducer = (state = initialState, action) => {
    switch(action.type) {
        case ActionConstant.SHOW_MODAL:
            return Object.assign({}, state, {
                show: action.show
            })
        // TODO: 덮어 써지는 문제가 있음
        case ActionConstant.REGISTER_ACCOUNT_HANDLER:
            return Object.assign({}, state, {
                handler: action.handler
            })
        default:
            return state;
    }
}