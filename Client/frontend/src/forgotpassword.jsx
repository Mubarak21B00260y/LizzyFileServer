import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import logo from './assets/images/logo.jpg';
import './index.css';
import { SyncLoader } from 'react-spinners';
const BASE_URL='https://courageous-balance-production.up.railway.app'
const ForgotPasswordPage = () => {
  const [email, setEmail] = useState('');
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const handleEmailChange = (e) => {
    setEmail(e.target.value);                         
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading( true)
    try {
      await axios.post(  `${BASE_URL}/api/account/forgotPassword` , null, { params: { email } });
      toast.success('Password recovery link sent to your email.');
      
      setTimeout(() => navigate('/accountrecovery'), 2000);
    } catch (error) {
    
      toast.error('Failed to send recovery code. Please try again.');
    }
    finally{
      setLoading(false)
    }
  };

  return (
    <section className="bg-gray-50 dark:bg-gray-900">
      <ToastContainer />
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <div className="w-full bg-white rounded-lg shadow dark:border sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
              Forgot Your Password?
            </h1>
            <p className="text-gray-600 dark:text-gray-400">
              Enter your email address and we'll send you a code to reset your password.
            </p>
            <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit}>
              <div>
                <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  value={email}
                  onChange={handleEmailChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="name@company.com"
                  required
                />
              </div>
              <div className="flex justify-between">
                <button
                  type="button"
                  onClick={() => window.history.back()}
                  className="w-1/2 text-gray-900 bg-gray-200 hover:bg-gray-300 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-gray-700 dark:hover:bg-gray-600 dark:focus:ring-gray-700"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="w-1/2 text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                >
                  {loading ? <SyncLoader size={8} color={"#ffffff"} /> : 'Send code'}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
};

export default ForgotPasswordPage;
