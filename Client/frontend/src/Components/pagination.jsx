import React from 'react';

const Pagination = ({ currentPage, itemsPerPage, totalItems, onPageChange }) => {
  const totalPages = Math.ceil(totalItems / itemsPerPage);

  return (
    <div className="flex justify-center mt-4">
      <button
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
        className={`px-4 py-2 mx-1 rounded ${currentPage === 1 ? 'bg-gray-300 text-gray-500' : 'bg-gray-200 text-gray-700'}`}
      >
        Previous
      </button>
      {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
        <button
          key={page}
          onClick={() => onPageChange(page)}
          className={`px-4 py-2 mx-1 rounded ${page === currentPage ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'}`}
        >
          {page}
        </button>
      ))}
      <button
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
        className={`px-4 py-2 mx-1 rounded ${currentPage === totalPages ? 'bg-gray-300 text-gray-500' : 'bg-gray-200 text-gray-700'}`}
      >
        Next
      </button>
    </div>
  );
};

export default Pagination;
