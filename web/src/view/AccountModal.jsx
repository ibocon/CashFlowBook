import React from 'react'

export class AccountModal extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            base: null,
            name: ""
        }

        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleHide = this.handleHide.bind(this)
    }

    handleSubmit(event) {
        event.preventDefault()
        this.props.handleAccount({...this.state})
        this.props.handleModal(false)
    }

    handleHide() {
        this.props.handleModal(false)
    }

    render() {

        if(this.props.account) {
            this.setState({
                base: this.props.account.base,
                name: this.props.account.name
            })
        }

        return (
            // <Modal show={this.props.show} onHide={this.handleHide}>
            //     <Modal.Header>
            //         <Modal.Title>계정</Modal.Title>
            //     </Modal.Header>
            //     <Modal.Body>
            //         <Form id="account-form" onSubmit={this.handleSubmit}>
            //             <Form.Group controlId="baseAccount">
            //                 <Form.Label>카테고리</Form.Label>                  
            //                 <ToggleButtonGroup  type="radio" 
            //                                     name="base" 
            //                                     defaultValue={this.state.base} 
            //                                     onChange={(value) => {this.setState({base: value})}}>
            //                     <ToggleButton variant="info" value="Asset"> Asset</ToggleButton>
            //                     <ToggleButton variant="info" value="Capital"> Capital</ToggleButton>
            //                     <ToggleButton variant="info" value="Liability"> Liability</ToggleButton>
            //                     <ToggleButton variant="info" value="Revenue"> Revenue</ToggleButton>
            //                     <ToggleButton variant="info" value="Expense"> Expense</ToggleButton>
            //                 </ToggleButtonGroup>
            //             </Form.Group>
            //             <Form.Group>
            //                 <Form.Label>이름</Form.Label>
            //                 <Form.Control   type="text" 
            //                                 value={this.state.name}
            //                                 onChange={(event) => {this.setState({name: event.target.value})}}></Form.Control>
            //             </Form.Group>
            //             <Button variant="primary" type="submit">제출</Button>
            //         </Form>
            //     </Modal.Body>
            // </Modal>
            <div></div>
        )
    }
}

AccountModal.propTypes = {

}

AccountModal.defaultProps = {

}