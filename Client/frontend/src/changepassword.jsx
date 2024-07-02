import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import logo from './assets/images/logo.jpg';
import './index.css';

const BASE_URL='https://courageous-balance-production.up.railway.app'
const ResetPasswordPage = () => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [newPasswordVisible, setNewPasswordVisible] = useState(false);
  const [confirmPasswordVisible, setConfirmPasswordVisible] = useState(false);
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  const toggleNewPasswordVisibility = () => {
    setNewPasswordVisible(!newPasswordVisible);
  };

  const toggleConfirmPasswordVisibility = () => {
    setConfirmPasswordVisible(!confirmPasswordVisible);
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    setError(null);

    if (newPassword !== confirmPassword) {
      setError('New passwords do not match');
      return;
    }

    try {
      const token = localStorage.getItem('token');
      await axios.patch(
        
        `${BASE_URL}/api/account/resetPassword`,
        { oldPassword, newPassword },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      toast.success('Password reset successfully! Redirecting to login page...');
      setTimeout(() => {
        navigate('/');
      }, 3000);
    } catch (error) {
      console.error('Error resetting password:', error);
      setError('Failed to reset password. Please try again.');
      toast.error('Failed to reset password. Please try again.');
    }
  };

  return (
    <section className="bg-gray-50 dark:bg-gray-900">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <div className="w-full bg-white rounded-lg shadow dark:border sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
              Reset Your Password
            </h1>
            {error && <div className="text-red-500">{error}</div>}
            <form className="space-y-4 md:space-y-6" onSubmit={handleResetPassword}>
              <div>
                <label htmlFor="old-password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                  Old Password
                </label>
                <div className="relative">
                  <input
                    type={passwordVisible ? 'text' : 'password'}
                    name="old-password"
                    id="old-password"
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    value={oldPassword}
                    onChange={(e) => setOldPassword(e.target.value)}
                    required
                  />
                  <button
                    type="button"
                    onClick={togglePasswordVisibility}
                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                  >
                    {passwordVisible ? 'Hide' : 'Show'}
                  </button>
                </div>
              </div>
              <div>
                <label htmlFor="new-password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                  New Password
                </label>
                <div className="relative">
                  <input
                    type={newPasswordVisible ? 'text' : 'password'}
                    name="new-password"
                    id="new-password"
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                    required
                  />
                  <button
                    type="button"
                    onClick={toggleNewPasswordVisibility}
                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                  >
                    {newPasswordVisible ? 'Hide' : 'Show'}
                  </button>
                </div>
              </div>
              <div>
                <label htmlFor="confirm-new-password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                  Confirm New Password
                </label>
                <div className="relative">
                  <input
                    type={confirmPasswordVisible ? 'text' : 'password'}
                    name="confirm-new-password"
                    id="confirm-new-password"
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                  />
                  <button
                    type="button"
                    onClick={toggleConfirmPasswordVisibility}
                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                  >
                    {confirmPasswordVisible ? 'Hide' : 'Show'}
                  </button>
                </div>
              </div>
              <div className="flex justify-between">
                <button
                  type="button"
                  className="w-1/2 text-gray-900 bg-gray-200 hover:bg-gray-300 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-gray-700 dark:hover:bg-gray-600 dark:focus:ring-gray-700"
                  onClick={() => navigate('/userHome')} 
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="w-1/2 text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                >
                  Reset Password
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <ToastContainer />
    </section>
  );
};

export default ResetPasswordPage;
