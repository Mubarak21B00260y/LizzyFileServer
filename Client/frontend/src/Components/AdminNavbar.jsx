import React from 'react';

const Navbar = ({ logo, user, onLogout }) => {
  return (
    <div className="w-1/5 bg-blue-800 text-gray-100 p-4 flex flex-col items-center">
      <div className="flex items-center mb-8">
        <img className='w-20 h-20 mr-2' src={logo} alt="logo" />
        <span className="text-xl font-semibold">Lizzy file server</span>
      </div>
      <div className="flex flex-col items-center space-y-4">
        <img src={user.avatarUrl} alt="User Avatar" className="w-16 h-16 rounded-full" />
        <span className="text-lg font-medium">Admin</span>
        <div className="flex items-center space-x-2 cursor-pointer" onClick={onLogout}>
          <div className="text-gray-300">&gt;</div>
          <span>Logout</span>
        </div>
        <div className="flex items-center space-x-2 cursor-pointer">
          {/* <div className="text-gray-300">&gt;</div> */}
          
          
        </div>
      </div>
      <hr />
      <span className="font-bold text-base">
  &copy; All rights reserved 2024
</span>

    </div>
  );
};

export default Navbar;
