import React, { useState } from 'react';

const ConfirmRegistration = () => {
  const [code, setCode] = useState(Array(6).fill(''));

  const handleChange = (e, index) => {
    const value = e.target.value;
    if (/^\d$/.test(value) || value === '') {
      const newCode = [...code];
      newCode[index] = value;
      setCode(newCode);

      // Move to next input if the current input is filled
      if (value !== '' && index < 5) {
        document.getElementById(`code-input-${index + 1}`).focus();
      }
    }
  };

  const handleConfirm = () => {
    const codeString = code.join('');
    // Implement confirmation logic (e.g., verify the code with the server)
    console.log('Confirming registration with code:', codeString);
    // Redirect to the login page or show a success message
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-semibold mb-6 text-center">Confirm Registration</h2>
        <p className="text-gray-600 mb-4 text-center">Enter the 6-digit code sent to your email to confirm your registration.</p>
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
          >
            Confirm
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmRegistration;
