import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import { Route, Switch } from 'react-router-dom'
import './App.sass'
import { userAction } from '../action'
import { UrlConstant } from '../constant'

import { 
  PrivateRoute, OAuth2RedirectHandler,
} from '../component'

import {
  Home, MyNavbar, Dashboard, Profile, Account
} from '../view'

class App extends React.Component {

  componentDidMount() {
    this.props.loadCurrentUser();
  }

  render() {
    return (
      <div className="App">
        <MyNavbar></MyNavbar>
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <PrivateRoute path="/profile" component={Profile}></PrivateRoute>
          <PrivateRoute path="/account" component={Account}></PrivateRoute>
          <PrivateRoute path="/dashboard" component={Dashboard}></PrivateRoute>
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>
        </Switch>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  user: state.user
})

const mapDispatchToProps = dispatch => ({
    loadCurrentUser: () => {
      if(localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        dispatch(userAction.getCurrentUserAsync());
      } 
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))
