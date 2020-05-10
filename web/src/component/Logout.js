import React from 'react'
import Button from 'react-bootstrap/Button'
import { UrlConstant } from '../constant'

class Logout extends React.Component {

    render() {
        return (
            <div>
                <h1>Logout</h1>
                <Button variant="primary" href={UrlConstant.GOOGLE_AUTH_URL}>Logout</Button>
            </div>
        )
    }
}

export default Logout