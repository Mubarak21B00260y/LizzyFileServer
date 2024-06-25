import React, { useState } from 'react';
import Navbar from './Components/AdminNavbar';
import SearchBar from './Components/AdminSearchbar'
import DocumentGrid from './Components/AdminDocumentgrid'
import Pagination from './Components/Adminpagination'
import UploadModal from './Components/uploadModal'
import EditModal from './Components/EditModal'
import DeleteModal from './Components/Deletemodal'
import ShareModal from './Components/AdminshareModal'
import LogoutModal from './Components/AdminLogoutModal'
import logo from './assets/images/logo.jpg'; // Replace with your logo
import avatar from './assets/images/avatar.png'; // Replace with the user's avatar image
import './index.css';

const AdminHome = () => {
  const initialDocuments = [
    { id: 1, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 2, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 3, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 4, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 5, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 6, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 7, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 8, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 9, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 10, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 11, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 12, name: 'Document 1', url: '/documents/doc1.pdf' },
    { id: 13, name: 'Document 1', url: '/documents/doc1.pdf' },
    // other documents...
  ];

  const [documents, setDocuments] = useState(initialDocuments);
  const [searchTerm, setSearchTerm] = useState('');
  const [openUploadModal, setOpenUploadModal] = useState(false);
  const [openEditModal, setOpenEditModal] = useState(false);
  const [currentDocument, setCurrentDocument] = useState(null);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [attachment, setAttachment] = useState(null);
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const [openShareModal, setOpenShareModal] = useState(false);
  const [email, setEmail] = useState('');
  const [openLogoutModal, setOpenLogoutModal] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10;

  const handleSearch = (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchTerm(searchTerm);
    const filteredDocuments = initialDocuments.filter(document =>
      document.name.toLowerCase().includes(searchTerm)
    );
    setDocuments(filteredDocuments);
  };

  const clearSearch = () => {
    setSearchTerm('');
    setDocuments(initialDocuments);
  };

  const handleUpload = () => {
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
    console.log('Saving edit for document:', currentDocument.id, { title, description });
    setOpenEditModal(false);
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const user = {
    name: 'Mubarak',
    avatarUrl: avatar,
  };

  return (
    <div className="flex h-screen bg-gray-100">
      <Navbar logo={logo} user={user} onLogout={() => setOpenLogoutModal(true)} />
      <div className="flex-1 p-8 overflow-y-auto">
        <SearchBar 
          searchTerm={searchTerm} 
          onSearch={handleSearch} 
          onClearSearch={clearSearch} 
          onUpload={() => setOpenUploadModal(true)} 
        />
        <DocumentGrid 
          documents={documents.slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage)} 
          onEdit={handleEdit} 
          onDelete={() => setOpenDeleteModal(true)} 
          onShare={() => setOpenShareModal(true)} 
        />
        <Pagination 
          currentPage={currentPage} 
          itemsPerPage={itemsPerPage} 
          totalItems={documents.length} 
          onPageChange={paginate} 
        />
      </div>
      <UploadModal 
        isOpen={openUploadModal} 
        onClose={() => setOpenUploadModal(false)} 
        title={title} 
        description={description} 
        onTitleChange={setTitle} 
        onDescriptionChange={setDescription} 
        onFileChange={(e) => setAttachment(e.target.files[0])} 
        onUpload={handleUpload} 
      />
      <EditModal 
        isOpen={openEditModal} 
        onClose={() => setOpenEditModal(false)} 
        title={title} 
        description={description} 
        onTitleChange={setTitle} 
        onDescriptionChange={setDescription} 
        onSave={handleSaveEdit} 
      />
      <DeleteModal 
        isOpen={openDeleteModal} 
        onClose={() => setOpenDeleteModal(false)} 
        onDeleteConfirm={() => console.log('Deleting document')}
      />
      <ShareModal 
        isOpen={openShareModal} 
        onClose={() => setOpenShareModal(false)} 
        email={email} 
        onEmailChange={(e) => setEmail(e.target.value)} 
        onShare={() => console.log(`Sharing document with email: ${email}`)}
      />
      <LogoutModal 
        isOpen={openLogoutModal} 
        onClose={() => setOpenLogoutModal(false)} 
        onConfirmLogout={() => console.log('Logging out user')} 
      />
    </div>
  );
};

export default AdminHome;
