import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'

class Account extends React.Component {

    render() {
        return (
         <div>
             <h3>{this.props.account.name}</h3>
         </div>   
        )
    }
}

const mapStateToProps = state => ({
    
})
  
const mapDispatchToProps = dispatch => ({

})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Account))