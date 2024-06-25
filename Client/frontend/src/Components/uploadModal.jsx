import React from 'react';

const UploadModal = ({ isOpen, onClose, title, description, onTitleChange, onDescriptionChange, onFileChange, onUpload }) => {
  return isOpen ? (
    <div className="fixed inset-0 z-50 overflow-auto bg-smoke-light flex">
      <div className="relative p-8 bg-white w-full max-w-md m-auto flex-col flex rounded-lg">
        <span className="absolute top-0 right-0 p-4">
          <button onClick={onClose}>X</button>
        </span>
        <h2 className="text-xl font-bold mb-4">Upload Document</h2>
        <div className="mb-4">
          <label htmlFor="title" className="block text-sm font-medium text-gray-700">Title</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => onTitleChange(e.target.value)}
            className="mt-1 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description</label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => onDescriptionChange(e.target.value)}
            className="mt-1 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="attachment" className="block text-sm font-medium text-gray-700">Attachment</label>
          <input
            type="file"
            id="attachment"
            onChange={onFileChange}
            className="mt-1 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
          />
        </div>
        <div className="flex justify-end">
          <button
            onClick={onClose}
            className="mr-2 px-4 py-2 bg-gray-300 rounded-md shadow-sm hover:bg-gray-400"
          >
            Cancel
          </button>
          <button
            onClick={onUpload}
            className="px-4 py-2 bg-blue-500 text-white rounded-md shadow-sm hover:bg-blue-600"
          >
            Upload
          </button>
        </div>
      </div>
    </div>
  ) : null;
};

export default UploadModal;
