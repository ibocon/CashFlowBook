import React from 'react'
import './App.sass'
import UserListContainer from '../container/UserListContainer'
import { Route, Switch } from 'react-router-dom'
import Home from '../component/Home'
import MyNavbar from '../component/MyNavbar'
import OAuth2RedirectHandler from '../component/OAuth2RedirectHandler'
import Login from '../component/Login'

class App extends React.Component {
  
  // constructor(props) {
  //   super(props);
  // }

  componentDidMount() {

  }

  render() {
    return (
      <div className="App">
        <MyNavbar></MyNavbar>
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route path="/login" component={Login}></Route>
          <Route path="/users" component={UserListContainer}></Route>
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>
        </Switch>
      </div>
    );
  }
}

export default App;
