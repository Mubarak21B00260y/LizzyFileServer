import React from 'react'
import ReactDOM from 'react-dom/client'

import App from './App.jsx'
import './index.css'
import Signup from './Signup.jsx'
import Login from './login.jsx'

import ConfirmRegistration from './confirmtoken.jsx'
import UserHome from './UserHome.jsx'
  import AdminHome from './AdminHome.jsx'
  import ResetPasswordPage from './changepassword.jsx'
  import ForgotPasswordPage from './forgotpassword.jsx'
  import NewPasswordPage from './Newpassword.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
   <App/>
  </React.StrictMode>,
)
