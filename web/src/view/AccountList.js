import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { Button } from 'react-bootstrap';
import { AccountAction, ModalAction } from '../action';
import {AccountModal, Account} from '../view';

class AccountList extends React.Component {

    componentDidMount() {
        this.props.getAccounts();
    }

    filterBaseAccount(base) {
        if(this.props == null || this.props.account == null) {
            return;
        }
        else {
            return this.props.account.list
            .filter(account => account.base === base)
            .map(account => <Account key={account.base + account.name} account={account}/> );
        }
    }

    render() {
        
        const assets = this.filterBaseAccount("Asset");
        const capitals = this.filterBaseAccount("Capital");
        const liabilities = this.filterBaseAccount("Liability");
        const revenues = this.filterBaseAccount("Revenue");
        const expenses = this.filterBaseAccount("Expense");

        return (
            <div>
                <p>Test5</p>
                <h1>계정과목</h1>
                <div>
                    <h2>자산</h2>
                    {assets}
                    <h2>비용</h2> 
                    {expenses}
                </div>
                <div>
                    <h2>자본</h2>
                    {capitals}
                    <h2>부채</h2>
                    {liabilities}
                    <h2>수익</h2>
                    {revenues}
                </div>
                <Button variant="primary" onClick={() => {
                    this.props.showModal(true);
                    this.props.RegisterAccountHandler((account) => 
                    {
                        this.props.createAccount(account);
                        this.props.getAccounts();
                    });
                }}>계정 만들기</Button>

                <AccountModal></AccountModal>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    account: state.account
})
  
const mapDispatchToProps = dispatch => ({
    getAccounts: () => {
        dispatch(AccountAction.getAccountAsync())
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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AccountList))