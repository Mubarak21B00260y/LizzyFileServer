import React, { useState } from 'react';
import logo from './assets/images/logo.jpg';
import {BrowserRouter, Routes, Route} from "react-router-dom"
import './index.css'; 
import Login from './login';
import ConfirmRegistration from './confirmtoken';
import Signup from './Signup';
import UserHome from './UserHome';
import ResetPasswordPage from './changepassword';
import AdminHome from './AdminHome';
import ForgotPasswordPage from './forgotpassword';
import AccountRecoveryToken from './accountRecoverytoken';
import NewPasswordPage from './Newpassword';

const App = () => {
  const [passwordVisible, setPasswordVisible] = useState(false);

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  return (
    

<BrowserRouter>
    
    <Routes>
   <Route  exact path='/' element= { < Login/>}/>
   <Route    exact path='/confirmRegistration' element= {<ConfirmRegistration />} />
   <Route    exact path='/signup'   element= {<Signup/>} /> 

   <Route   exact path='/userHome'   element= {<UserHome/>} /> 
   
   <Route exact path='/resetPassword' element= {<ResetPasswordPage />}/>
   < Route  exact path='/AdminHome' element={<AdminHome/>}/>
   <Route   exact path='/forgotpassword'  element={<ForgotPasswordPage/>}/> 
   <Route   exact path='/accountrecovery'  element={<AccountRecoveryToken/>}/> 
   <Route   exact path='/newpassword'  element={<NewPasswordPage/>}/> 
    </Routes>
    
    
    </BrowserRouter>




    

  )
}

export default App;
