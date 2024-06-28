import React from 'react';
import { useNavigate } from 'react-router-dom';
import logo from '../assets/images/logo.jpg'; 
import avatar from '../assets/images/avatar.png';

const Navbar = ({ user, handleLogout }) => {
  const navigate = useNavigate();
  const handleResetPassword = () => {
    navigate('/resetPassword');
  };
  return (
    <div className="w-1/5 bg-gray-800 text-gray-100 p-4 flex flex-col items-center">
      <div className="flex items-center mb-8">
        <img className='w-20 h-20 mr-2' src={logo} alt="logo" />
        <span className="text-xl font-semibold">Lizz FileServer</span>
      </div>
      <div className="flex flex-col items-center space-y-4">
        <img src={user.avatarUrl} alt="User Avatar" className="w-16 h-16 rounded-full" />
        <span className="text-lg font-medium">{user.name}</span>
        <div className="flex items-center space-x-2 cursor-pointer" onClick={handleLogout}>
          <div className="text-gray-300">&gt;</div>
          <span>Logout</span>
        </div>
        <div className="flex items-center space-x-2 cursor-pointer" onClick={handleResetPassword}>
          <div className="text-gray-300">&gt;</div>
          <span>Reset Password</span>
        </div>
      </div>


      <hr className="mt-10"/>
      <span  className="font-bold text-base mt-10" >
  &copy; All rights reserved 2024
  
</span>
<span className="font-small  text-xs mt 8"> In loving memory of my late grandmother</span>
    </div>

    
  );
};

export default Navbar;
