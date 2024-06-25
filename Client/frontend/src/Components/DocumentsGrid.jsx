import React from 'react';
import { TbMailShare } from "react-icons/tb";

const Documents = ({ documents, openModal }) => {
  return (
    <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      {documents.map((document) => (
        <div key={document.id} className="bg-white rounded-lg shadow-md p-4">
          <h3 className="text-lg font-semibold mb-2">{document.name}</h3>
          <div className="flex justify-between items-center">
            <button
              onClick={() => {
                // Handle download logic
                window.open(document.url, '_blank', 'noopener,noreferrer');
              }}
              className="px-3 py-1 text-sm font-medium text-white bg-blue-600 rounded-md shadow-sm hover:bg-blue-700"
            >
              Download
            </button>
            <button onClick={() => openModal(true)} className="text-blue-600 hover:opacity-100 flex items-center">
              <span className="mr-1">Share</span>
              <TbMailShare className="h-5 w-5 text-blue-600" />
            </button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Documents;
