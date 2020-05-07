import React from 'react'

const User = ({ id, email, provider }) => (
    <li>
        {id} : {email} : {provider}
    </li>
)

export default User