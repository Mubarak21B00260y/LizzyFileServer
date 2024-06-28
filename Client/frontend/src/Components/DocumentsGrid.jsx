import React from 'react';
import axios from 'axios';
import { TbMailShare } from "react-icons/tb";

const Documents = ({ documents, openModal, setSelectedDocument }) => {
  const handleDownload = async (document) => {
    const token = localStorage.getItem('token');

    try {
      const response = await axios.get('http://localhost:8080/api/download/file', {
        params: { title: document.title },
        headers: {
          Authorization: `Bearer ${token}`,
        },
        responseType: 'blob',
      });

      if (response.status === 200) {
        // Create a new Blob object using the response data of the onload object
        const blob = new Blob([response.data], { type: 'application/pdf' });

        // Create a link element, hide it, direct it towards the blob, and then 'click' it programmatically
        const url = window.URL.createObjectURL(blob);
        const link = window.document.createElement('a'); // Ensure using window.document
        link.href = url;
        link.setAttribute('download', document.filename);

        // Append to html link element page
        window.document.body.appendChild(link);

        // Start download
        link.click();

        // Clean up and remove the link
        link.parentNode.removeChild(link);
      } else {
        console.error('Error in response:', response);
      }
    } catch (error) {
      console.error('Error downloading document:', error);
    }
  };

  return (
    <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      {documents.map((document) => (
        <div key={document.id} className="bg-white rounded-lg shadow-md p-4">
          <h3 className="text-lg font-semibold mb-2">{document.title}</h3>
          <p className="text-sm text-gray-500 mb-2">{document.filename}</p>
          <p className="text-xs text-gray-400 mb-4">Uploaded: {new Date(document.uploadedAt).toLocaleString()}</p>
          <div className="flex justify-between items-center">
            <button
              onClick={() => handleDownload(document)}
              className="px-3 py-1 text-sm font-medium text-white bg-blue-600 rounded-md shadow-sm hover:bg-blue-700"
            >
              Download
            </button>
            <button
              onClick={() => {
                setSelectedDocument(document);
                openModal(true);
              }}
              className="text-blue-600 hover:opacity-100 flex items-center"
            >
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
