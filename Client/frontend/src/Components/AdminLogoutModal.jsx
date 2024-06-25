import React from 'react';

const LogoutModal = ({ isOpen, onClose, onConfirmLogout }) => {
  return isOpen ? (
    <div className="fixed inset-0 z-50 overflow-auto bg-smoke-light flex">
      <div className="relative p-8 bg-white w-full max-w-md m-auto flex-col flex rounded-lg">
        <span className="absolute top-0 right-0 p-4">
          <button onClick={onClose}>X</button>
        </span>
        <h2 className="text-xl font-bold mb-4">Logout</h2>
        <p>Are you sure you want to logout?</p>
        <div className="flex justify-end mt-4">
          <button
            onClick={onClose}
            className="mr-2 px-4 py-2 bg-gray-300 rounded-md shadow-sm hover:bg-gray-400"
          >
            Cancel
          </button>
          <button
            onClick={onConfirmLogout}
            className="px-4 py-2 bg-red-500 text-white rounded-md shadow-sm hover:bg-red-600"
          >
            Logout
          </button>
        </div>
      </div>
    </div>
  ) : null;
};

export default LogoutModal;
