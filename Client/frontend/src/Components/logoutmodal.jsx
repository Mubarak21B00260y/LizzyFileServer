import React from 'react';

const LogoutModal = ({ openLogoutModal, confirmLogout, cancelLogout }) => {
  return (
    openLogoutModal && (
      <div className="fixed inset-0 z-10 flex items-center justify-center bg-black bg-opacity-30">
        <div className="bg-white p-6 rounded-lg shadow-lg">
          <h3 className="text-lg font-medium leading-6 text-gray-900">
            Logout Confirmation
          </h3>
          <p className="mt-2 text-sm text-gray-500">Are you sure you want to logout?</p>
          <div className="mt-4 flex justify-end">
            <button
              onClick={confirmLogout}
              className="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
            >
              Logout
            </button>
            <button
              onClick={cancelLogout}
              className="ml-4 inline-flex justify-center px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 border border-transparent rounded-md shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    )
  );
};

export default LogoutModal;
