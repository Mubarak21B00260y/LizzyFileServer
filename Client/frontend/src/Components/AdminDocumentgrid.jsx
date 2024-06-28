
const DocumentGrid = ({ documents, onEdit, openShareModal, onDelete, onDownload, setCurrentDocument }) => {
  return (
    <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      {documents.map((document) => (
        <div key={document.id} className="bg-white rounded-lg shadow-md p-4">
          <h3 className="text-lg font-semibold mb-2">{document.title}</h3>
          <p className="text-sm text-gray-500 mb-2">{document.filename}</p>
          <p className="text-xs text-gray-400 mb-2">Uploaded: {new Date(document.uploadedAt).toLocaleString()}</p>
          <p className="text-xs text-gray-400 mb-2">{document.description}</p>
          <p className="text-xs text-gray-400 mb-2">Mailed: {document.mailedFilesCount}</p>
          <p className="text-xs text-gray-400 mb-2">Downloads: {document.downloadsCount}</p>
          <div className="flex justify-between items-center">
            <button
              className="px-3 py-1 text-sm font-medium text-white bg-green-600 rounded-md shadow-sm hover:bg-green-700"
              onClick={() => { openShareModal(true); setCurrentDocument(document); }}
            >
              Share
            </button>
            <button
              className="px-3 py-1 text-sm font-medium text-white bg-yellow-600 rounded-md shadow-sm hover:bg-yellow-700"
              onClick={() => onEdit(document)}
            >
              Edit
            </button>
            <button
              className="px-3 py-1 text-sm font-medium text-white bg-red-600 rounded-md shadow-sm hover:bg-red-700"
              onClick={() => onDelete(document)}
            >
              Delete
            </button>
            <button
              className="ml-4 px-3 py-1 text-sm font-medium text-white bg-blue-600 rounded-md shadow-sm hover:bg-blue-700"
              onClick={() => onDownload(document)}
            >
              Download
            </button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default DocumentGrid;