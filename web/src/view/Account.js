import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'
import { AccountAction, ModalAction } from '../action'
import { Button } from 'react-bootstrap'
import AccountModal from './AccountModal'

class Account extends React.Component {

    componentDidMount() {
        this.props.getAccounts();
    }

    render() {
        return (
            <div>
                <h1>계정과목</h1>
                <div>
                    <h2>자산</h2>
                    <h2>비용</h2> 
                </div>
                <div>
                    <h2>자본</h2>
                    <h2>부채</h2>
                    <h2>수익</h2>
                </div>
                <Button variant="primary" onClick={() => {
                    this.props.showModal(true);
                    this.props.RegisterAccountHandler(this.props.createAccount);
                }}>계정 만들기</Button>

                <AccountModal></AccountModal>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    accounts: state.account.list,
})
  
const mapDispatchToProps = dispatch => ({
    getAccounts: () => {
        dispatch(AccountAction.getAccountsAsync())
    },
    showModal: (show) => {
        dispatch(ModalAction.showModal(show))
    },
    RegisterAccountHandler: (handler) => {
        dispatch(ModalAction.RegisterAccountHandler(handler))
    },
    createAccount: (account) => {
        dispatch(AccountAction.createAccountAsync(account));
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Account))