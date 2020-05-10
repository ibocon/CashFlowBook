// import React from 'react'
// import User from './User'

// class UserList extends React.Component {

//     render() {
//         const { users, onClick } = this.props;
//         return (
//             <div>
//                 <button onClick={onClick}>GetUsers</button>
//                 <ul>
//                     {
//                         users !== undefined && users.length > 0 && users.map(user => 
//                             <User  key={user.id} {...user} />
//                         )
//                     }
//                 </ul>
//             </div>
//         )
//     }
// }

// export default UserList

// import React from 'react'

// const User = ({ id, email, provider }) => (
//     <li>
//         {id} : {email} : {provider}
//     </li>
// )

// export default User