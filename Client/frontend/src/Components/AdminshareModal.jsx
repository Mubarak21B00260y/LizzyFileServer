const ShareModal = ({ openShareModal, email, handleEmailChange, handleShare, setOpenShareModal }) => {
  return openShareModal && (
    <div className="fixed inset-0 z-50 overflow-auto bg-smoke-light flex">
      <div className="relative p-8 bg-white w-full max-w-md m-auto flex-col flex rounded-lg">
        <span className="absolute top-0 right-0 p-4">
          <button onClick={() => setOpenShareModal(false)}>X</button>
        </span>
        <h2 className="text-xl font-bold mb-4">Share Document</h2>
        <div className="mb-4">
          <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={handleEmailChange}
            className="mt-1 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
          />
        </div>
        <div className="flex justify-end">
          <button
            onClick={() => setOpenShareModal(false)}
            className="mr-2 px-4 py-2 bg-gray-300 rounded-md shadow-sm hover:bg-gray-400"
          >
            Cancel
          </button>
          <button
            onClick={handleShare}
            className="px-4 py-2 bg-green-500 text-white rounded-md shadow-sm hover:bg-green-600"
          >
            Share
          </button>
        </div>
      </div>
    </div>
  );
};

export default ShareModal;