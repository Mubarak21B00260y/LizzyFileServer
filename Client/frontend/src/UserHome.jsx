import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from './Components/verticalNavbar';
import SearchBar from './Components/searchbar';
import Documents from './Components/DocumentsGrid';
import Pagination from './Components/pagination';
import ShareModal from './Components/shareModal';
import LogoutModal from './Components/logoutmodal';
import avatar from './assets/images/avatar.png'; 
import { jwtDecode } from "jwt-decode";
import { SyncLoader } from 'react-spinners';
import { toast, ToastContainer } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
const UserHome = () => {
  const [documents, setDocuments] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [openModal, setOpenModal] = useState(false);
  const [email, setEmail] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedDocument, setSelectedDocument] = useState(null);
  const itemsPerPage = 4;
  const [openLogoutModal, setOpenLogoutModal] = useState(false);
  const [userFullName, setUserFullName] = useState('');
  const navigate = useNavigate();

  const Logout = () => {
    navigate('/');
  };


  useEffect(() => {
    const fetchDocuments = async () => {
      setLoading(true);
      const token = localStorage.getItem('token');

      try {
        const response = await axios.get('http://localhost:8080/api/get/all', {
          params: { page: 0, size: 10 },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        const { data } = response;
        setDocuments(data);
      } catch (error) {
        setError('Failed to fetch documents');
      } finally {
        setLoading(false);
      }
    };

    fetchDocuments();
  }, []);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const decodedToken = jwtDecode(token);
    setUserFullName(decodedToken['full name']);
  }, []);

  const handleEmailChange = (e) => setEmail(e.target.value);
  const handleShare = async () => {
    const token = localStorage.getItem('token');
    setLoading(true);
    try {
      await axios.post('http://localhost:8080/api/share/file', null, {
        params: {
          Recipient: email,
          title: selectedDocument.title,
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setOpenModal(false);
      toast.success(" Document shared sucessfully")
    } catch (error) {
    toast.error("failed to share file, try again")
    } finally {
      setOpenModal(false);
      setLoading(false);
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
        setError('No matching document found');
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

  const fetchDocuments = async () => {
    setLoading(true);
    const token = localStorage.getItem('token');

    try {
      const response = await axios.get('http://localhost:8080/api/get/all', {
        params: { page: 0, size: 10 },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const { data } = response;
      setDocuments(data);
    } catch (error) {
      setError('Failed to fetch documents');
    } finally {
      setLoading(false);
    }
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleLogout = () => setOpenLogoutModal(true);
  
  const cancelLogout = () => setOpenLogoutModal(false);

  const user = {
    name: userFullName,
    avatarUrl: avatar,
  };

  return (
    <div className="flex h-screen bg-gray-100">
      <Navbar user={user} handleLogout={handleLogout} />
      <div className="flex-1 p-8 overflow-y-auto">
        <SearchBar searchTerm={searchTerm} handleSearch={handleSearch} clearSearch={clearSearch} />
        {loading ? (
          <div className="flex justify-center items-center h-full">
            <SyncLoader color={'#000'} loading={true} size={15} />
          </div>
        ) : error ? (
          <p className="text-red-500">{error}</p>
        ) : (
          <Documents documents={documents} openModal={setOpenModal} setSelectedDocument={setSelectedDocument} />
        )}
        <Pagination currentPage={currentPage} paginate={paginate} totalDocuments={documents.length} itemsPerPage={itemsPerPage} />
        <ShareModal openModal={openModal} setOpenModal={setOpenModal} email={email} handleEmailChange={handleEmailChange} handleShare={handleShare} />
        <LogoutModal openLogoutModal={openLogoutModal} confirmLogout={Logout} cancelLogout={cancelLogout} />
        <ToastContainer />
      </div>
    </div>
  );
};

export default UserHome;
