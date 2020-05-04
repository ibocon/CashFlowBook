import React from 'react'

const User = ({ id, name, email }) => (
    <li>
        {id} - {name} - {email}
    </li>
)

export default User