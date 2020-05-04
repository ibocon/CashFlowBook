import React from 'react'
import User from './User'

const UserList = ({ users, onClick }) => (
    <div>
        <button onClick={onClick}>GetUsers</button>
        <ul>
            {
                users !== undefined && users.length > 0 && users.map(user => 
                    <User  key={user.id} {...user} />
                )
            }
        </ul>
    </div>
)

export default UserList