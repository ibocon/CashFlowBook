import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import './App.sass'
import { Route, Switch } from 'react-router-dom'
import Home from '../component/Home'
import MyNavbar from '../component/MyNavbar'
import OAuth2RedirectHandler from '../component/OAuth2RedirectHandler'
import Login from '../component/Login'
import Dashboard from '../component/Dashboard'
import Profile from '../component/Profile'
import { userAction } from '../action'

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
          <Route path="/profile" component={Profile}></Route>
          <Route path="/dashboard" component={Dashboard}></Route>
          <Route path="/login" component={Login}></Route>
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
        dispatch(userAction.getCurrentUserAsync());
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))
