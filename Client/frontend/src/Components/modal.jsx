import React from 'react';

const Modal = ({ open, setOpen, children }) => {
  return open ? (
    <div className="fixed inset-0 z-50 overflow-auto bg-smoke-light flex">
      <div className="relative p-8 bg-white w-full max-w-md m-auto flex-col flex rounded-lg">
        <span className="absolute top-0 right-0 p-4">
          <button onClick={() => setOpen(false)}>X</button>
        </span>
        {children}
      </div>
    </div>
  ) : null;
};

export default Modal;
