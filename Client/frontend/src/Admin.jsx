 import React, { useState } from 'react';
import { RiSearchLine } from 'react-icons/ri'; // Importing search icon from react-icons library
import logo from './assets/images/logo.jpg'; // Replace with your logo
import avatar from './assets/images/avatar.png'; // Replace with the user's avatar image
import { TbMailShare } from "react-icons/tb";
import './index.css'; 

const AdminHomePage = () => {
  // Sample documents data (replace with your actual data)
  const initialDocuments = [
    { id: 1, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 2, name: 'Document 2', url: '/documents/doc2.pdf' },
    { id: 3, name: 'Document 3', url: '/documents/doc3.pdf' },
    { id: 4, name: 'Document 4', url: '/documents/doc4.pdf' },
    { id: 5, name: 'Document 5', url: '/documents/doc5.pdf' },
    { id: 6, name: 'Document 6', url: '/documents/doc6.pdf' },
    { id: 7, name: 'Document 7', url: '/documents/doc7.pdf' },
    { id: 8, name: 'Document 8', url: '/documents/doc8.pdf' },
    { id: 9, name: 'Document 9', url: '/documents/doc9.pdf' },
    { id: 10, name: 'Document 10', url: '/documents/doc10.pdf' },
  ];

  const [documents, setDocuments] = useState(initialDocuments);
  const [searchTerm, setSearchTerm] = useState('');



  


  // handle logout modal 

  const [openLogoutModal, setOpenLogoutModal] = useState(false);
  const handleLogout = () => {
    setOpenLogoutModal(true);
  };
  
  const confirmLogout = () => {
    // Implement logout functionality
    console.log('Logging out user');
    setOpenLogoutModal(false); // Close modal after logout
    // Add further logout logic here, e.g., redirecting to login page
  };
  
  const cancelLogout = () => {
    setOpenLogoutModal(false);
  };

// handle  delete 
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const handleDelete = () => {
    setOpenDeleteModal(true);
  };
  
  const ConfirmDelete = () => {
    setOpenDeleteModal(false); 
    
  };
  
  const cancelDelete = () => {
    setOpenDeleteModal(false);
  };


  
  // States for modal (upload, edit, and share document)
  const [openUploadModal, setOpenUploadModal] = useState(false);
  const [openEditModal, setOpenEditModal] = useState(false);
  const [currentDocument, setCurrentDocument] = useState(null);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [attachment, setAttachment] = useState(null);



  const handleTitleChange = (e) => setTitle(e.target.value);
  const handleDescriptionChange = (e) => setDescription(e.target.value);
  const handleAttachmentChange = (e) => setAttachment(e.target.files[0]);

// handle share


const [OpenshareModal, setOpenshareModal] = useState(false);
  const [email, setEmail] = useState('');

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };
const handleShare = () => {
    // Implement share functionality (e.g., send email with document link)
    console.log(`Sharing document with email: ${email}`);
    setOpenshareModal(false);
  };
  // Handle search functionality
  const handleSearch = (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchTerm(searchTerm);
    const filteredDocuments = initialDocuments.filter(document =>
      document.name.toLowerCase().includes(searchTerm)
    );
    setDocuments(filteredDocuments);
  };

  // Clear search and reset documents list
  const clearSearch = () => {
    setSearchTerm('');
    setDocuments(initialDocuments);
  };


  const handleUpload = () => {
    // Implement upload functionality
    console.log('Uploading document:', { title, description, attachment });
    setOpenUploadModal(false);
  };

  const handleEdit = (document) => {
    setCurrentDocument(document);
    setTitle(document.name);
    setDescription(document.description);
    setOpenEditModal(true);
  };

  const handleSaveEdit = () => {
    // Implement save edit functionality
    console.log('Saving edit for document:', currentDocument.id, { title, description });
    setOpenEditModal(false);
  };

//   const handleDelete = (documentId) => {
//     // Implement delete functionality
//     console.log('Deleting document:', documentId);
//   };

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 4; // Change as needed

  // Logic for pagination
  const indexOfLastDocument = currentPage * itemsPerPage;
  const indexOfFirstDocument = indexOfLastDocument - itemsPerPage;
  const currentDocuments = documents.slice(indexOfFirstDocument, indexOfLastDocument);

  // Change page
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  // Sample user data (replace with actual user data)
  const user = {
    name: 'Mubarak',
    avatarUrl: avatar, // Replace with the user's avatar URL
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Vertical Navbar */}
      <div className="w-1/5 bg-blue-800 text-gray-100 p-4 flex flex-col items-center">
        <div className="flex items-center mb-8">
          <img className='w-20 h-20 mr-2' src={logo} alt="logo" />
          <span className="text-xl font-semibold">Lizzy file server</span>
          
        </div>
        
        <div className="flex flex-col items-center space-y-4">
          <img src={user.avatarUrl} alt="User Avatar" className="w-16 h-16 rounded-full" />
          <span className="text-lg font-medium"> Admin</span>
          <span className="text-lg font-medium">{user.name}</span>
          <div className="flex items-center space-x-2 cursor-pointer">
            <div className="text-gray-300">&gt;</div>
            <span onClick={handleLogout}>Logout</span>

          </div>
          <div className="flex items-center space-x-2 cursor-pointer">
            <div className="text-gray-300">&gt;</div>
            <span>Reset Password</span>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 p-8 overflow-y-auto">
        {/* Search Bar */}
        <div className="flex items-center mb-8">
          <div className="relative">
            <span className="absolute inset-y-0 left-0 pl-3 flex items-center text-gray-400">
              <RiSearchLine className="h-5 w-5" />
            </span>
            <input
              type="text"
              value={searchTerm}
              onChange={handleSearch}
              className="block w-full rounded-lg pl-10 pr-4 py-2 border-gray-300 focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
              placeholder="Search documents..."
            />
          </div>
          <button
            onClick={clearSearch}
            className="ml-4 px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            Clear
          </button>

          <button
        className="ml-4 px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
        onClick={() => setOpenUploadModal(true)}
      >
        Upload Document
      </button>

        </div>

        {/* Document Grid */}
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {currentDocuments.map((document) => (
            <div key={document.id} className="bg-white rounded-lg shadow-md p-4">
              <h3 className="text-lg font-semibold mb-2">{document.name}</h3>
              <p className="text-sm text-gray-600 mb-4">{document.description}</p>
              <div className="flex justify-between mb-2">
                <a href={document.url} target="_blank" rel="noopener noreferrer" className="text-blue-600 hover:underline">Download</a>
                <button onClick={() => handleEdit(document)} className="text-green-600 hover:underline">Edit</button>
              </div>
              <div className="flex justify-between mb-2">
                <span className="text-sm">Downloads: {document.downloads}</span>
                <span className="text-sm">Shares: {document.shares}</span>
              </div>
              <hr />
              <button onClick={() => handleDelete(document.id)} className="text-red-600 hover:underline">Delete</button>
              <hr/>
              <button onClick={() => setOpenshareModal(true)} className="text-blue-600 hover:shadow-lg">
                
 <span className="mr-2">Share</span>
  <TbMailShare className="h-5 w-5 text-blue-600" />

                </button>

            </div>
          ))}
        </div>

        {/* Pagination */}
        <div className="flex justify-center mt-8">
          <nav className="inline-flex rounded-md shadow-sm -space-x-px" aria-label="Pagination">
            <button
              onClick={() => paginate(currentPage - 1)}
              disabled={currentPage === 1}
              className="bg-white border border-gray-300 rounded-l-md px-3 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50"
            >
              Previous
            </button>
            <button
              onClick={() => paginate(currentPage + 1)}
              disabled={currentDocuments.length < itemsPerPage}
              className="bg-white border border-gray-300 px-3 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50"
            >
              Next
            </button>
          </nav>
        </div>

        {/* Upload Modal */}
        {openUploadModal && (
          <div className="fixed inset-0 z-10 flex items-center justify-center bg-black bg-opacity-30">
            <div className="bg-white p-6 rounded-lg shadow-lg">
              <h3 className="text-lg font-medium leading-6 text-gray-900">Upload Document</h3>
              <div className="mt-4">
                <input
                  type="text"
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
                  placeholder="Title"
                  value={title}
                  onChange={handleTitleChange}
                />
              </div>
              <div className="mt-4">
                <textarea
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
                  placeholder="Description"
                  value={description}
                  onChange={handleDescriptionChange}
                />
              </div>
              <div className="mt-4">
                <input
                  type="file"
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
                  onChange={handleAttachmentChange}
                />
              </div>
              <div className="mt-4 flex justify-end">
                <button
                  type="button"
                  className="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
                  onClick={handleUpload}
                >
                  Upload
                </button>
                <button
                  type="button"
                  className="ml-4 inline-flex justify-center px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 border border-transparent rounded-md shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
                  onClick={() => setOpenUploadModal(false)}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}

        {/* Edit Modal */}
        {openEditModal && (
          <div className="fixed inset-0 z-10 flex items-center justify-center bg-black bg-opacity-30">
            <div className="bg-white p-6 rounded-lg shadow-lg">
              <h3 className="text-lg font-medium leading-6 text-gray-900">Edit Document</h3>
              <div className="mt-4">
                <input
                  type="text"
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
                  placeholder="Title"
                  value={title}
                  onChange={handleTitleChange}
              




                  
                />
              </div>
              <div className="mt-4">
                <textarea
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
                  placeholder="Description"
                  value={description}
                  onChange={handleDescriptionChange}
                />
              </div>
              <div className="mt-4 flex justify-end">
                <button
                  type="button"
                  className="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
                  onClick={handleSaveEdit}
                >
                  Save
                </button>
                <button
                  type="button"
                  className="ml-4 inline-flex justify-center px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 border border-transparent rounded-md shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
                  onClick={() => setOpenEditModal(false)}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}



{openLogoutModal && (
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
  )}


{OpenshareModal && (
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
                  onClick={() => setOpenshareModal(false)}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}




{/* Delete modal*/}
{openDeleteModal && (
    <div className="fixed inset-0 z-10 flex items-center justify-center bg-black bg-opacity-30">
      <div className="bg-white p-6 rounded-lg shadow-lg">
        <h3 className="text-lg font-medium leading-6 text-gray-900">
           Confirm Delete Operation
        </h3>
        <p className="mt-2 text-sm text-gray-500">Are you sure you want to delete this document?</p>
        <div className="mt-4 flex justify-end">
          <button
            onClick={ConfirmDelete}
            className="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-red-600 border border-transparent rounded-md shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            Delete
          </button>
          <button
            onClick={cancelDelete}
            className="ml-4 inline-flex justify-center px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 border border-transparent rounded-md shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  )}



        

      </div>
    </div>
  );
};

export default AdminHomePage;


//


//  