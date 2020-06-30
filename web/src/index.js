import React from 'react'
import ReactDOM from 'react-dom'
import './index.sass'
import App from './app/App'
import * as serviceWorker from './serviceWorker'
import { createStore, compose, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import rootReducer from './reducer'
import thunkMiddleware from 'redux-thunk'
import { BrowserRouter as Router } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(
    rootReducer,
    // applyMiddleware(thunkMiddleware)
    composeEnhancers(
      applyMiddleware(thunkMiddleware)
    )
  );

ReactDOM.render(
  // <React.StrictMode>
    <Router>
      <Provider store={store}>
        <App />
      </Provider>
    </Router>
  // </React.StrictMode>
  ,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
