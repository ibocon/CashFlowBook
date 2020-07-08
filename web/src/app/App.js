import { hot } from 'react-hot-loader/root';
import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { Route, Switch } from 'react-router-dom';
import './App.sass';
import { UserAction } from '../action';
import { UrlConstant } from '../constant';

import { 
  PrivateRoute, OAuth2RedirectHandler,
} from '../component'

import {
  Home, MyNavbar, Dashboard, Profile, AccountList
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
          <PrivateRoute path="/account" component={AccountList}></PrivateRoute>
          <PrivateRoute path="/dashboard" component={Dashboard}></PrivateRoute>
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>
        </Switch>
      </div>
    );
  }
}

const mapStateToProps = state => ({

})

const mapDispatchToProps = dispatch => ({
    loadCurrentUser: () => {
      if(localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        dispatch(UserAction.getCurrentUserAsync());
      } 
    }
})

export default hot(withRouter(connect(mapStateToProps, mapDispatchToProps)(App)))
