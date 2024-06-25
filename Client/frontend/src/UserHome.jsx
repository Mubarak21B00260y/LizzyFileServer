import React, { useState } from 'react';
import Navbar from './Components/verticalNavbar';
import SearchBar from './Components/searchbar';
import Documents from './Components/DocumentsGrid';
import Pagination from './Components/pagination';
import ShareModal from './Components/shareModal';
import LogoutModal from './Components/logoutmodal';
import avatar from './assets/images/avatar.png'; 

const UserHome = () => {  
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
  const [openModal, setOpenModal] = useState(false);
  const [email, setEmail] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 4;
  const [openLogoutModal, setOpenLogoutModal] = useState(false);

  const handleEmailChange = (e) => setEmail(e.target.value);
  const handleShare = () => {
    console.log(`Sharing document with email: ${email}`);
    setOpenModal(false);
  };

  const indexOfLastDocument = currentPage * itemsPerPage;
  const indexOfFirstDocument = indexOfLastDocument - itemsPerPage;
  const currentDocuments = documents.slice(indexOfFirstDocument, indexOfLastDocument);

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

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleLogout = () => setOpenLogoutModal(true);
  const confirmLogout = () => {
    console.log('Logging out user');
    setOpenLogoutModal(false);
  };
  const cancelLogout = () => setOpenLogoutModal(false);

  const user = {
    name: 'John Doe',
    avatarUrl: avatar,
  };

  return (
    <div className="flex h-screen bg-gray-100">
      <Navbar user={user} handleLogout={handleLogout} />
      <div className="flex-1 p-8 overflow-y-auto">
        <SearchBar searchTerm={searchTerm} handleSearch={handleSearch} clearSearch={clearSearch} />
        <Documents documents={currentDocuments} openModal={setOpenModal} />
        <Pagination currentPage={currentPage} paginate={paginate} totalDocuments={documents.length} itemsPerPage={itemsPerPage} />
        <ShareModal openModal={openModal} setOpenModal={setOpenModal} email={email} handleEmailChange={handleEmailChange} handleShare={handleShare} />
        <LogoutModal openLogoutModal={openLogoutModal} confirmLogout={confirmLogout} cancelLogout={cancelLogout} />
      </div>
    </div>
  );
};

export default UserHome;
