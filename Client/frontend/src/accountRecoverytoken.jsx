import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { css } from '@emotion/react';
import { SyncLoader } from 'react-spinners';

const override = css`
  display: block;
  margin: 0 auto;
  border-color: red;
`;

const AccountRecoveryToken = () => {
  const [code, setCode] = useState(Array(6).fill(''));
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e, index) => {
    const value = e.target.value;
    if (/^\d$/.test(value) || value === '') {
      const newCode = [...code];
      newCode[index] = value;
      setCode(newCode);

      if (value !== '' && index < 5) {
        document.getElementById(`code-input-${index + 1}`).focus();
      }
    }
  };

  const handleConfirm = async () => {
    const codeString = code.join('');
    setLoading(true);
    setError(null);

    try {
      const response = await axios.post(`http://localhost:8080/api/account/ConfirmAccountRecovery`, null, {
        params: { token: codeString },
      });

      if (response.status === 202) {
        
        navigate('/newpassword', { state: {token: codeString } });
      }
    } catch (err) {
      const errorMessage = err.response?.data?.message || 'An error occurred';
      setError(errorMessage);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-semibold mb-6 text-center">Account Recovery</h2>
        <p className="text-gray-600 mb-4 text-center">Enter the 6-digit code sent to your email to recover your account</p>
        {error && <p className="text-red-500 text-center">{error}</p>}
        <div className="flex justify-center space-x-2 mb-4">
          {code.map((digit, index) => (
            <input
              key={index}
              id={`code-input-${index}`}
              type="text"
              maxLength="1"
              className="w-12 h-12 text-center border border-gray-300 rounded-md shadow-sm focus:ring-primary-600 focus:border-primary-600 sm:text-sm"
              value={digit}
              onChange={(e) => handleChange(e, index)}
            />
          ))}
        </div>
        <div className="flex justify-center">
          <button
            onClick={handleConfirm}
            className="w-full px-4 py-2 text-sm font-medium text-white bg-primary-600 border border-transparent rounded-md shadow-sm hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
            disabled={loading}
          >
            {loading ? <SyncLoader color={'#ffffff'} loading={true} css={override} size={10} /> : 'Confirm'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AccountRecoveryToken;
