import React from 'react';

const DocumentGrid = ({ documents, onEdit, onShare, onDelete }) => {
  return (
    <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
      {documents.map((document) => (
        <div key={document.id} className="relative p-4 border border-gray-200 rounded-lg shadow-sm">
          <h3 className="text-lg font-medium text-gray-900">{document.name}</h3>
          <p className="mt-2 text-sm text-gray-500">{document.description}</p>
          <div className="mt-4 flex justify-between items-center">
            <div className="text-sm text-gray-500">
              Shares: {document.shares}
            </div>
            <div className="flex space-x-2">
              <button
                className="px-3 py-1 text-sm font-medium text-white bg-blue-600 rounded-md shadow-sm hover:bg-blue-700"
                onClick={() => onEdit(document)}
              >
                Edit
              </button>
              <button
                className="px-3 py-1 text-sm font-medium text-white bg-green-600 rounded-md shadow-sm hover:bg-green-700"
                onClick={() => onShare(document)}
              >
                Share
              </button>
              <button
                className="px-3 py-1 text-sm font-medium text-white bg-red-600 rounded-md shadow-sm hover:bg-red-700"
                onClick={() => onDelete(document)}
              >
                Delete
              </button>
              <div className="relative">
                <button
                  className="ml-4 px-3 py-1 text-sm font-medium text-white bg-blue-600 rounded-md shadow-sm hover:bg-blue-700"
                  onClick={() => {
                    // Handle download logic
                    window.open(document.url, '_blank', 'noopener,noreferrer');
                  }}
                >
                  Download
                </button>
              </div>
            </div>
          </div>
          <div className="mt-2 text-sm text-gray-500">
            Downloads: {document.downloads}
          </div>
        </div>
      ))}
    </div>
  );
};

export default DocumentGrid;
