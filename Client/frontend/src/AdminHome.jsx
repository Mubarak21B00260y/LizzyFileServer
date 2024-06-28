import React, { useState, useEffect } from 'react';
import Navbar from './Components/AdminNavbar';
import SearchBar from './Components/AdminSearchbar';
import DocumentGrid from './Components/AdminDocumentgrid';
import Pagination from './Components/Adminpagination';
import UploadModal from './Components/uploadModal';
import EditModal from './Components/EditModal';
import DeleteModal from './Components/Deletemodal';
import ShareModal from './Components/AdminshareModal';
import LogoutModal from './Components/AdminLogoutModal';
import logo from './assets/images/logo.jpg'; // Replace with your logo
import avatar from './assets/images/avatar.png'; // Replace with the user's avatar image
import './index.css';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';
import { SyncLoader } from 'react-spinners';

const AdminHome = () => {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [documents, setDocuments] = useState([]);
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



  const navigate = useNavigate();

  const Logout = () => {
    navigate('/');
  };

  useEffect(() => {
    fetchDocuments();
  }, [currentPage]);

  const fetchDocuments = async () => {
    try {
      const token = localStorage.getItem('token'); // Assuming token is stored in localStorage
      const response = await axios.get('http://localhost:8080/api/get/all', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params: {
          page: currentPage - 1,
          size: itemsPerPage,
        },
      });
      setDocuments(response.data);
    } catch (error) {
      console.error('Error fetching documents:', error);
      toast.error('Error fetching documents');
    }
  };

  const handleSearch = async (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchTerm(searchTerm);

    if (searchTerm) {
      setLoading(true);
      setError(null);
      const token = localStorage.getItem('token');

      try {
        const response = await axios.get('http://localhost:8080/api/search/file', {
          params: { title: searchTerm },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        const { data } = response;
        if (data.length) {
          setDocuments(data);
        } else {
          setDocuments([]);
        }
      } catch (error) {
        setDocuments([]);
      } finally {
        setLoading(false);
      }
    } else {
      // Reset to original documents list if search term is empty
      fetchDocuments();
    }
  };

  const clearSearch = () => {
    setSearchTerm('');
    setError(null);
    // Reset to original documents list
    fetchDocuments();
  };

  const handleEmailChange = (e) => setEmail(e.target.value);
  const handleShare = async () => {
    const token = localStorage.getItem('token');
    setLoading(true);
    try {
      await axios.post('http://localhost:8080/api/share/file', null, {
        params: {
          Recipient: email,
          title: currentDocument.title,
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setOpenShareModal(false)
      toast.success("Document shared successfully");
    } catch (error) {
      toast.error("Failed to share file, try again");
    } finally {
      setOpenShareModal(false);
      setLoading(false);
    }
  };

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
        const mimeType = response.headers['content-type'];
        const blob = new Blob([response.data], { type: mimeType });
        const url = window.URL.createObjectURL(blob);
        const link = window.document.createElement('a');
        link.href = url;
        link.setAttribute('download', document.filename);
        window.document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
      } else {
        console.error('Error in response:', response);
      }
    } catch (error) {
      console.error('Error downloading document:', error);
    }
  };

  const handleUpload = async () => {
    const formData = new FormData();
    formData.append('file', attachment);
    formData.append('title', title);
    formData.append('description', description);

    try {
      const token = localStorage.getItem('token');
      await axios.post('http://localhost:8080/api/upload/single', formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      setOpenUploadModal(false);
      fetchDocuments();
      toast.success('File uploaded successfully');
    } catch (error) {
      console.error('Error uploading document:', error);
      toast.error('Error uploading document');
    }
  };

  const handleEdit = async (document) => {
    setCurrentDocument(document);
    setTitle(document.title);
    setDescription(document.description);
    setOpenEditModal(true);
  };

  const handleSaveEdit = async () => {
    const updatedDocument = {
      newTitle: title,
      newDescription: description,
    };

    try {
      const token = localStorage.getItem('token');
      await axios.patch(`http://localhost:8080/api/edit/file/${currentDocument.id}`, updatedDocument, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setOpenEditModal(false);
      fetchDocuments();
      toast.success('Document updated successfully');
    } catch (error) {
      console.error('Error editing document:', error);
      toast.error('Error editing document');
    }
  };

  const handleDelete = async (document) => {
    setCurrentDocument(document);
    setOpenDeleteModal(true);
  };

  const confirmDelete = async () => {
    try {
      const token = localStorage.getItem('token');
      await axios.delete(`http://localhost:8080/api/delete/file/${currentDocument.id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setOpenDeleteModal(false);
      fetchDocuments();
      toast.success('Document deleted successfully');
    } catch (error) {
      console.error('Error deleting document:', error);
      toast.error('Error deleting document');
    }
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const user = {
    name: 'Admmin',
    avatarUrl: avatar,
  };

  return (
    <div className="flex h-screen bg-gray-100">
      <Navbar logo={logo} user={user} onLogout={() => setOpenLogoutModal(true)} />
      <div className="flex-1 p-8 overflow-y-auto">
        <SearchBar searchTerm={searchTerm} handleSearch={handleSearch} clearSearch={clearSearch} onUpload={() => setOpenUploadModal(true)} />
        {loading ? (
          <div className="flex justify-center items-center h-full">
            <SyncLoader color={'#000'} loading={true} size={15} />
          </div>
        ) : error ? (
          <p className="text-red-500">{error}</p>
        ) : (
          <DocumentGrid
            documents={documents.slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage)}
            onEdit={handleEdit}
            onDelete={handleDelete}
            onDownload={handleDownload}
            setCurrentDocument={setCurrentDocument}
            openShareModal={setOpenShareModal}
          />
        )}
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
        onDeleteConfirm={confirmDelete}
      />
      <ShareModal
        openShareModal={openShareModal}
        setOpenShareModal={setOpenShareModal}
        email={email}
        handleEmailChange={handleEmailChange}
        handleShare={handleShare}
      />
      <LogoutModal
        isOpen={openLogoutModal}
        onClose={() => setOpenLogoutModal(false)}
        onConfirmLogout={Logout}
      />
      <ToastContainer />
    </div>
  );
};

export default AdminHome;