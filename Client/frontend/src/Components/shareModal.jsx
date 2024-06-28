import React from 'react';

const ShareModal = ({ openModal, setOpenModal, email, handleEmailChange, handleShare }) => {
  return (
    
    openModal && (
      <div className="fixed inset-0 z-10 flex items-center justify-center bg-black bg-opacity-30">
        <div className="bg-white p-6 rounded-lg shadow-lg">
          <h3 className="text-lg font-medium leading-6 text-gray-900">
            Share via Email
          </h3>
          <div className="mt-4">
            <input
              type="email"
              className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
              placeholder="Enter recipient's email"
              value={email}
              onChange={handleEmailChange}
            />
          </div>

          <div className="mt-4 flex justify-end">
            <button
              type="button"
              className="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
              onClick={handleShare}
            >
              Share
            </button>
            <button
              type="button"
              className="ml-4 inline-flex justify-center px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 border border-transparent rounded-md shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
              onClick={() => setOpenModal(false)}
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    )
  );
};

export default ShareModal;
